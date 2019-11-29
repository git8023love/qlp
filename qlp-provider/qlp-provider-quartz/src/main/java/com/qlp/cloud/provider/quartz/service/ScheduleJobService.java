package com.qlp.cloud.provider.quartz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qlp.cloud.provider.quartz.entity.ScheduleJob;

public interface ScheduleJobService extends IService<ScheduleJob> {

    /**
     * 立即执行定时任务
     * @param jobIds
     */
    void run(Long[] jobIds);

    IPage<ScheduleJob> getScheduleJobPage(Page<ScheduleJob> page, ScheduleJob scheduleJob);

    /**
     * 保存定时任务
     * @param scheduleJob
     */
    void saveJob(ScheduleJob scheduleJob);
}
