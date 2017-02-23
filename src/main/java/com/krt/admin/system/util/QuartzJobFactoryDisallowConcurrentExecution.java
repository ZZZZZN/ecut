package com.krt.admin.system.util;

import com.krt.admin.system.entity.QuartzJob;
import org.apache.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**        
* 类名称：QuartzJobFactoryDisallowConcurrentExecution   
* 类描述：若一个方法一次执行不完下次轮转时则等待改方法执行完后才执行下一次操作 
* 创建人：殷帅   
* 创建时间：2016年3月8日    
*    
*/
@DisallowConcurrentExecution
public class QuartzJobFactoryDisallowConcurrentExecution implements Job {
	
	public final Logger log = Logger.getLogger(this.getClass());

	public void execute(JobExecutionContext context) throws JobExecutionException {
		QuartzJob scheduleJob = (QuartzJob) context.getMergedJobDataMap().get("quartzJob");
		TaskUtils.invokMethod(scheduleJob);
	}
}