package com.zhangds.scheduling.model;

import com.zhangds.common.util.CommonUtils;
import com.zhangds.scheduling.entities.SysJob;
import com.zhangds.scheduling.enums.SysJobStatus;
import com.zhangds.scheduling.repository.SysJobRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 项目启动初始化加载状态为正常的定时任务
 * @author: zhangds
 * @date: 2020/9/3 17:04
 */
@Component
public class SysJobRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(SysJobRunner.class);

    @Autowired
    private SysJobRepository sysJobRepository;

    @Autowired
    private ScheduledTaskRegistrar scheduledTaskRegistrar;

    @Override
    public void run(String... args) {
        // 初始加载数据库里状态为正常的定时任务
        List<SysJob> jobList = sysJobRepository.getSysJobListByStatus(SysJobStatus.NORMAL.ordinal());
        if (CommonUtils.notEmpty(jobList)) {
            for (SysJob job : jobList) {
                SchedulingRunnable task = new SchedulingRunnable(job.getBeanName(), job.getMethodName(), job.getMethodParams());
                scheduledTaskRegistrar.addCronTask(task, job.getCronExpression());
            }

            logger.info("定时任务已加载完毕...");
        }
    }
}
