package com.wy.quartz_demo.scheduler;

import com.wy.quartz_demo.job.SimpleJob;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

public class SimpleTriggerDemo {

    @Autowired
    Scheduler scheduler;

    @PostConstruct
    public void initJob() throws SchedulerException {
        JobDetail job = newJob(SimpleJob.class).withIdentity("simplejob", "simplejobGroup").build();

        SimpleTrigger trigger = newTrigger()
                .withIdentity("simpleTrigger")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(2)
                        .withRepeatCount(2))
                .build();

        scheduler.scheduleJob(job, trigger);
    }




}
