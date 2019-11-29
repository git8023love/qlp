package com.qlp.cloud.provider.quartz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qlp.cloud.provider.quartz.entity.ScheduleJob;
import org.apache.ibatis.annotations.Param;

public interface ScheduleJobMapper extends BaseMapper<ScheduleJob> {
    /**
     * 定时任务查询
     * @param page
     * @param scheduleJob 定时任务表
     * @return
     */
    IPage<ScheduleJob> getScheduleJobPage(Page<ScheduleJob> page, @Param("scheduleJob") ScheduleJob scheduleJob);
}
