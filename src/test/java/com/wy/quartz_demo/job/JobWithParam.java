package com.wy.quartz_demo.job;

import com.wy.quartz_demo.util.DateUtil;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.StringJoiner;

@PersistJobDataAfterExecution
public class JobWithParam extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        JobDataMap jobMap =  jobExecutionContext.getJobDetail().getJobDataMap();
        StringJoiner outStr = new StringJoiner("|")
                .add(DateUtil.formatter(new Date()))
                .add(jobExecutionContext.getJobDetail().getKey().getName())
                .add(jobMap.getString("jobKey"));

        System.out.println(outStr);

        jobMap.put("jobKey","jobValue2");
    }
}
