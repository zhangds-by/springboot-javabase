package com.zhangds.scheduling.controller;

import com.zhangds.common.handler.ResponseHandler;
import com.zhangds.common.util.CommonUtils;
import com.zhangds.scheduling.entities.SysJob;
import com.zhangds.scheduling.enums.SysJobStatus;
import com.zhangds.scheduling.model.ScheduledTaskRegistrar;
import com.zhangds.scheduling.model.SchedulingRunnable;
import com.zhangds.scheduling.repository.SysJobRepository;
import com.zhangds.scheduling.service.SysJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Date;

@RestController
@RequestMapping("scheduling")
public class SchedulingController {

    @Autowired
    private SysJobService sysJobService;

    @Autowired
    private ScheduledTaskRegistrar scheduledTaskRegistrar;

    /**
     * 添加任务
     * @param sysJob
     * @return
     */
    @PostMapping("/addJob")
    public ResponseHandler addJob(@RequestBody SysJob sysJob){
        sysJob.setCreateTime(new Date());
        sysJob.setUpdateTime(new Date());
        SysJob entity = sysJobService.saveJob(sysJob);
        if (CommonUtils.empty(entity)){
            return ResponseHandler.fail("新增失败");
        }else {
            if (entity.getStatus().equals(SysJobStatus.NORMAL.ordinal())) { //返回在枚举中的序号，0开始
                SchedulingRunnable task = new SchedulingRunnable(entity.getBeanName(), entity.getMethodName(), entity.getMethodParams());
                scheduledTaskRegistrar.addCronTask(task, entity.getCronExpression());
            }
        }
        return ResponseHandler.success();
    }

    /**
     * 修改任务：先删除后启动
     * @param sysJob
     * @return
     */
    @PostMapping("/updateJob")
    public ResponseHandler updateJob(@RequestBody SysJob sysJob){
        SysJob entity = sysJobService.updateJob(sysJob);
        if (CommonUtils.empty(entity)){
            return ResponseHandler.fail("编辑失败");
        }else {
            //先移除再添加
            if (entity.getStatus().equals(SysJobStatus.NORMAL.ordinal())) {
                SchedulingRunnable task = new SchedulingRunnable(entity.getBeanName(), entity.getMethodName(), entity.getMethodParams());
                scheduledTaskRegistrar.removeCronTask(task);
            }

            if (entity.getStatus().equals(SysJobStatus.NORMAL.ordinal())) {
                SchedulingRunnable task = new SchedulingRunnable(entity.getBeanName(), entity.getMethodName(), entity.getMethodParams());
                scheduledTaskRegistrar.addCronTask(task, entity.getCronExpression());
            }
        }

        return ResponseHandler.success();
    }

    /**
     * 删除定时任务
     * @param jobId
     * @return
     */
    @PostMapping("/deleteJob/{jobId}")
    public ResponseHandler deleteJob(@PathVariable Integer jobId){
        SysJob entity = sysJobService.findById(jobId);
        if (CommonUtils.empty(entity)){
            ResponseHandler.fail("不存在任务");
        }
        sysJobService.deleteById(jobId);
        if (entity.getStatus().equals(SysJobStatus.NORMAL.ordinal())) {
            SchedulingRunnable task = new SchedulingRunnable(entity.getBeanName(), entity.getMethodName(), entity.getMethodParams());
            scheduledTaskRegistrar.removeCronTask(task);
        }
        return ResponseHandler.success();
    }

    /**
     * 任务状态转换
     * @param jobId
     * @return
     */
    @PostMapping("/updateStatus/{jobId}")
    public ResponseHandler updateStatus(@PathVariable Integer jobId){
        SysJob entity = sysJobService.findById(jobId);
        if (CommonUtils.empty(entity)){
            ResponseHandler.fail("不存在任务");
        }
        if (entity.getStatus().equals(SysJobStatus.NORMAL.ordinal())) {
            SchedulingRunnable task = new SchedulingRunnable(entity.getBeanName(), entity.getMethodName(), entity.getMethodParams());
            scheduledTaskRegistrar.addCronTask(task, entity.getCronExpression());
        } else {
            SchedulingRunnable task = new SchedulingRunnable(entity.getBeanName(), entity.getMethodName(), entity.getMethodParams());
            scheduledTaskRegistrar.removeCronTask(task);
        }
        return ResponseHandler.success();
    }

}