package com.wy.quartz_demo;

import com.wy.quartz_demo.job.JobWithParam;
import com.wy.quartz_demo.job.SimpleJob;
import org.junit.jupiter.api.Test;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

@SpringBootTest
class QuartzDemoApplicationTests {
    @Autowired
    Scheduler scheduler;

    @Test
    void testSimpleTrigger() throws SchedulerException, InterruptedException {
        JobDetail job = newJob(SimpleJob.class).withIdentity("simplejob", "simplejobGroup").build();

        SimpleTrigger trigger = newTrigger()
                .withIdentity("simpleTrigger")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(2)
                        .withRepeatCount(2))
                .build();

        scheduler.scheduleJob(job, trigger);

        TimeUnit.SECONDS.sleep(5);
    }

    @Test
    void testCronTrigger() throws SchedulerException, InterruptedException {
        JobDetail job = newJob(SimpleJob.class).withIdentity("cronjob", "cronjobGroup").build();

        CronTrigger trigger = newTrigger()
                .withIdentity("cronTrigger")
                .withSchedule(cronSchedule("0/2 * * * * ?"))
                .build();

        scheduler.scheduleJob(job, trigger);

        TimeUnit.SECONDS.sleep(7);
    }

    @Test
    void testPutParam() throws SchedulerException, InterruptedException {
        JobDetail job = newJob(JobWithParam.class)
                .usingJobData("jobKey", "value1")
                .withIdentity("jobWithParam")
                .build();

        Trigger trigger = newTrigger()
                .withIdentity("triggerWithParam", "triggerGroup")
                .usingJobData("triggerKey", "triggerValue")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?"))
                .build();

        scheduler.scheduleJob(job, trigger);

        TimeUnit.SECONDS.sleep(7);
    }

}
