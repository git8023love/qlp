package com.qlp.cloud.provider.quartz.util;

import com.qlp.cloud.provider.quartz.entity.ScheduleJob;
import org.apache.commons.lang.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.lang.reflect.Method;

public class BuilderScheduleJob  extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get(ScheduleJob.JOB_PARAM_KEY);
//        //获取spring bean
//        ScheduleJobLogService  scheduleJobLogService = (ScheduleJobLogService) SpringContextUtils.getBean("scheduleJobLogService");

        //任务开始时间
        long startTime = System.currentTimeMillis();

        try {
            //执行任务
           // logger.debug("任务准备执行，任务ID：" + scheduleJob.getJobId());

            Object target = SpringContextUtils.getBean(scheduleJob.getBeanName());

            Method method = target.getClass().getDeclaredMethod(scheduleJob.getMethodName(), String.class);
            method.invoke(target, scheduleJob.getParams());

            System.out.println("执行成功");

           // logger.debug("任务执行完毕，任务ID：" + scheduleJob.getJobId() + "  总共耗时：" + times + "毫秒");
        } catch (Exception e) {
           e.printStackTrace();
        }finally {
           // scheduleJobLogService.save(log);
        }
    }
}
