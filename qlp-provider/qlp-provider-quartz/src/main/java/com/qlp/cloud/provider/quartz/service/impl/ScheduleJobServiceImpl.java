package com.qlp.cloud.provider.quartz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qlp.cloud.provider.quartz.entity.ScheduleJob;
import com.qlp.cloud.provider.quartz.mapper.ScheduleJobMapper;
import com.qlp.cloud.provider.quartz.service.ScheduleJobService;
import com.qlp.cloud.provider.quartz.util.ScheduleUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@AllArgsConstructor
public class ScheduleJobServiceImpl extends ServiceImpl<ScheduleJobMapper, ScheduleJob> implements ScheduleJobService {

    private final Scheduler scheduler;

    @Override
    public IPage<ScheduleJob> getScheduleJobPage(Page<ScheduleJob> page, ScheduleJob scheduleJob) {
        return baseMapper.getScheduleJobPage(page, scheduleJob);
    }

    @Override
    public void saveJob(ScheduleJob scheduleJob) {
        scheduleJob.setCreateTime(LocalDateTime.now());
        scheduleJob.setStatus(0);
        this.save(scheduleJob);

        ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void run(Long[] jobIds) {
        for(Long jobId : jobIds){
            ScheduleJob scheduleJob = this.getById(jobId);
            if(scheduleJob == null) {
                scheduleJob = new ScheduleJob();
                scheduleJob.setJobId(jobId);
                scheduleJob.setBeanName("novelTask");
                scheduleJob.setMethodName("getBookInfo");
                scheduleJob.setParams("qlp");
                scheduleJob.setCronExpression("0 0 12 * * ?");
                scheduleJob.setStatus(0);
            }
            ScheduleUtils.run(scheduler, scheduleJob);
        }
    }


}
