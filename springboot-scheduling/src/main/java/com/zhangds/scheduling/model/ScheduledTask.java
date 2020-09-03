package com.zhangds.scheduling.model;

import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledFuture;
/**
 * ScheduledFuture是ScheduledExecutorService定时任务线程池的执行结果。
 * @author: zhangds
 * @date: 2020/8/26 8:54
 */
@Component
public final class ScheduledTask {

    volatile ScheduledFuture<?> future;

    /**
     * 取消定时任务
     */
    public void cancel() {
        ScheduledFuture<?> future = this.future;
        if (future != null) {
            future.cancel(true);
        }
    }
}
