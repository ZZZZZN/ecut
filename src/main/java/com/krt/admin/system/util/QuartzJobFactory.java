package com.krt.admin.system.util;

import com.krt.admin.system.entity.QuartzJob;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


/**
* 类名称：QuartzJobFactory   
* 类描述：计划任务执行处 无状态 
* 创建人：殷帅   
* 创建时间：2016年3月8日    
*    
*/
public class QuartzJobFactory implements Job {
	public final Logger log = Logger.getLogger(this.getClass());

	public void execute(JobExecutionContext context) throws JobExecutionException {
		QuartzJob scheduleJob = (QuartzJob) context.getMergedJobDataMap().get("quartzJob");
		TaskUtils.invokMethod(scheduleJob);
	} 
}