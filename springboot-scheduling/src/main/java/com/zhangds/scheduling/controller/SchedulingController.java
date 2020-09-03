package com.zhangds.scheduling.controller;

import com.zhangds.common.handler.ResponseHandler;
import com.zhangds.common.util.CommonUtils;
import com.zhangds.scheduling.entities.SysJob;
import com.zhangds.scheduling.enums.SysJobStatus;
import com.zhangds.scheduling.model.ScheduledTaskRegistrar;
import com.zhangds.scheduling.model.SchedulingRunnable;
import com.zhangds.scheduling.repository.SysJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("scheduling")
public class SchedulingController {

    @Autowired
    private SysJobRepository sysJobRepository;

    @Autowired
    private ScheduledTaskRegistrar scheduledTaskRegistrar;

    /**
     * 添加任务
     * @param sysJob
     * @return
     */
    @PostMapping("/add")
    public ResponseHandler add(@RequestBody SysJob sysJob){
        SysJob entity = sysJobRepository.save(sysJob);
        if (CommonUtils.empty(entity)){
            return ResponseHandler.fail("新增失败");
        }else {
            if (sysJob.getJobStatus().equals(SysJobStatus.NORMAL.ordinal())) { //返回在枚举中的序号，0开始
                SchedulingRunnable task = new SchedulingRunnable(sysJob.getBeanName(), sysJob.getMethodName(), sysJob.getMethodParams());
                scheduledTaskRegistrar.addCronTask(task, sysJob.getCronExpression());
            }
        }
        return ResponseHandler.succeed();
    }
}
