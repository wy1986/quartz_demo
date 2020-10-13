package com.wy.quartz_demo.job;

import com.wy.quartz_demo.util.DateUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.StringJoiner;

public class SimpleJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        StringJoiner outStr = new StringJoiner("|")
                .add(DateUtil.formatter(new Date()))
                .add(jobExecutionContext.getJobDetail().getKey().getName());

        System.out.println(outStr);
    }
}
