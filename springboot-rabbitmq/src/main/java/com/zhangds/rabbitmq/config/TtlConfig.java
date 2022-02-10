package com.zhangds.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class TtlConfig {

    public static final String X_EXCHANGE = "X";
    public static final String Y_DEAD_EXCHANGE = "Y";
    public static final String QUEUE_A = "QA";
    public static final String QUEUE_B = "QB";
    public static final String QUEUE_D = "QD";
    public static final String QUEUE_C = "QC";

    //声明交换机
    @Bean("xExchange")
    public DirectExchange xExchange(){
        return new DirectExchange(X_EXCHANGE);
    }

    @Bean("yExchange")
    public DirectExchange yExchange(){
        return new DirectExchange(Y_DEAD_EXCHANGE);
    }

    // 声明队列
    @Bean("queueA")
    public Queue queueA(){
        Map<String, Object> args = new HashMap<>();
        //声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", Y_DEAD_EXCHANGE);
        //声明当前队列的死信路由key
        args.put("x-dead-letter-routing-key", "YD");
        //声明队列的TTL
        args.put("x-message-ttl", 10000);
        return QueueBuilder.durable(QUEUE_A).withArguments(args).build();
    }

    @Bean("queueB")
    public Queue queueB(){
        Map<String, Object> args = new HashMap<>();
        //声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", Y_DEAD_EXCHANGE);
        //声明当前队列的死信路由key
        args.put("x-dead-letter-routing-key", "YD");
        //声明队列的TTL
        args.put("x-message-ttl", 20000);
        return QueueBuilder.durable(QUEUE_B).withArguments(args).build();
    }

    // 不绑定过期时间
    @Bean("queueC")
    public Queue queueC(){
        Map<String, Object> args = new HashMap<>();
        //声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", Y_DEAD_EXCHANGE);
        //声明当前队列的死信路由key
        args.put("x-dead-letter-routing-key", "YD");
        return QueueBuilder.durable(QUEUE_C).withArguments(args).build();
    }

    @Bean("queueD")
    public Queue queueD(){
        return new Queue(QUEUE_D);
    }

    //队列绑定交换机
    @Bean
    public Binding queueABindingX(@Qualifier("queueA") Queue queueA, @Qualifier("xExchange") DirectExchange xExchange){
        return BindingBuilder.bind(queueA).to(xExchange).with("XA");
    }

    @Bean
    public Binding queueBBindingX(@Qualifier("queueB") Queue queueB, @Qualifier("xExchange") DirectExchange xExchange){
        return BindingBuilder.bind(queueB).to(xExchange).with("XB");
    }

    @Bean
    public Binding queueBindingDead(@Qualifier("queueD") Queue queueD, @Qualifier("yExchange") DirectExchange yExchange){
        return BindingBuilder.bind(queueD).to(yExchange).with("YD");
    }

    @Bean
    public Binding queueCBindingX(@Qualifier("queueC") Queue queueC, @Qualifier("xExchange") DirectExchange xExchange){
        return BindingBuilder.bind(queueC).to(xExchange).with("XC");
    }

}
