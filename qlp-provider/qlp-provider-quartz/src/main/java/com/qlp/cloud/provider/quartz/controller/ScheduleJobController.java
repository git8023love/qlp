package com.qlp.cloud.provider.quartz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qlp.cloud.common.core.util.R;
import com.qlp.cloud.common.log.annotation.SysLog;
import com.qlp.cloud.provider.quartz.entity.ScheduleJob;
import com.qlp.cloud.provider.quartz.service.ScheduleJobService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/schedule")
public class ScheduleJobController {

    private final ScheduleJobService scheduleJobService;

    /**
     * 简单分页查询
     * @return
     */
    @GetMapping("/page")
    public R<IPage<ScheduleJob>> getBookPage(Page<ScheduleJob> page, ScheduleJob scheduleJob) {
        return new R<>(scheduleJobService.getScheduleJobPage(page,scheduleJob));
    }

    /**
     * 立即执行任务
     */
    @SysLog("立即执行任务")
    @PostMapping("/run")
    @PreAuthorize("@pms.hasPermission('sys_schedule_run')")
    public R run(@RequestParam Long[] jobIds){
        scheduleJobService.run(jobIds);
        return new R<>();
    }

    @PostMapping("/save")
    public R save(@RequestBody ScheduleJob scheduleJob) {
        scheduleJobService.saveJob(scheduleJob);
        return new R<>();
    }

}
