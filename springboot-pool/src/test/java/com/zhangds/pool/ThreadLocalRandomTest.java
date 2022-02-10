package com.zhangds.pool;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 多个线程共享同一个 Random 实例并进行争夺，避免任何对 Random 对象的并发访问
 *
 * JMH吞吐量测试：一秒钟调用完成的次数
 * Throughput	每段时间执行的次数，一般是秒
 * AverageTime	平均时间，每次操作的平均耗时
 * SampleTime	在测试中，随机进行采样执行的时间
 * SingleShotTime	在每次执行中计算耗时
 * All	顾名思义，所有模式，这个在内部测试中常用
 *
 * ThreadLocalRandomTest.getLocalRandomData  thrpt   30   23922605.530 ±  22695.794  ops/s
 * ThreadLocalRandomTest.getRandomData       thrpt   30  608252635.592 ± 391425.519  ops/s
 * @author: zhangds
 * @date: 2021/7/12 15:44
 */
@SpringBootTest
public class ThreadLocalRandomTest {

    @Test
    public void JmtTest() throws Exception{
        Options opt = new OptionsBuilder()
                .include("ThreadLocalRandomTest")
//                .exclude("Test")
                .warmupIterations(10) // 预热做10轮
                .measurementIterations(10) //正式计量测试做10轮
                .forks(3) //3轮测试
                .mode(Mode.SingleShotTime)
                .timeUnit(TimeUnit.MICROSECONDS)
                .build();

        new Runner(opt).run();
    }

    // Result "com.zhangds.pool.ThreadLocalRandomTest.getRandomData":
    //  608252635.592 ±(99.9%) 391425.519 ops/s [Average]
    //  (min, avg, max) = (607161169.323, 608252635.592, 609501111.282), stdev = 585867.334
    //  CI (99.9%): [607861210.073, 608644061.111] (assumes normal distribution)
//    @Benchmark
//    @Fork(value = 1, warmups = 2) // 配置策略：预热2轮，正式计量1轮
//    @BenchmarkMode({Mode.Throughput, Mode.SingleShotTime}) // 指定模式
//    public void getRandomData(){
//        ThreadLocalRandom random = ThreadLocalRandom.current();
//        random.nextInt();
//    }

    //Result "com.zhangds.pool.ThreadLocalRandomTest.getLocalRandomData":
    //  23922605.530 ±(99.9%) 22695.794 ops/s [Average]
    //  (min, avg, max) = (23843621.445, 23922605.530, 23976285.809), stdev = 33969.999
    //  CI (99.9%): [23899909.735, 23945301.324] (assumes normal distribution)
//    @Benchmark
//    public void getLocalRandomData(){
//        Random random = new Random();
//        random.nextInt();
//    }

    // Benchmark                                  Mode  Cnt      Score      Error  Units
    // ThreadLocalRandomTest.getLocalRandomData2    ss   30  34098.897 ± 1356.386  us/op
    // ThreadLocalRandomTest.getRandomData2         ss   30  34297.530 ± 1591.007  us/op
    @Benchmark
    public void getRandomData2() throws Exception{
        ExecutorService executor = Executors.newWorkStealingPool();
        List<Callable<Integer>> callables = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 100000; i++) {
            callables.add(() -> {
                return random.nextInt();
            });
        }
        executor.invokeAll(callables);
    }

    @Benchmark
    public void getLocalRandomData2() throws Exception{
        ExecutorService executor = Executors.newWorkStealingPool();
        List<Callable<Integer>> callables = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            callables.add(() -> {
                return ThreadLocalRandom.current().nextInt();
            });
        }
        executor.invokeAll(callables);
    }
}
