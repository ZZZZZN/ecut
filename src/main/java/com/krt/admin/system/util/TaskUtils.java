package com.krt.admin.system.util;

import com.krt.admin.system.entity.QuartzJob;
import com.krt.common.util.SpringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;

/**        
* 类名称：TaskUtils   
* 类描述：任务操作工具类 
* 创建人：殷帅   
* 创建时间：2016年3月8日    
*    
*/
public class TaskUtils {

	public final static Logger logger = Logger.getLogger(TaskUtils.class);

	/**
	 * 通过反射调用scheduleJob中定义的方法
	 * 
	 * @param scheduleJob
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void invokMethod(QuartzJob scheduleJob) {
		Object object = null;
		Class clazz = null;
		if (StringUtils.isNotBlank(scheduleJob.getSpringId())) {
			object = SpringUtils.getBean(scheduleJob.getSpringId());
		} else if (StringUtils.isNotBlank(scheduleJob.getBeanClass())) {
			try {
				clazz = Class.forName(scheduleJob.getBeanClass());
				object = clazz.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		if (object == null) {
			logger.error("任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，请检查是否配置正确！！！");
			return;
		}
		clazz = object.getClass();
		Method method = null;
		try {
			method = clazz.getDeclaredMethod(scheduleJob.getMethodName());
		} catch (NoSuchMethodException e) {
			logger.error("任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，方法名设置错误！！！");
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		if (method != null) {
			try {
				method.invoke(object);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		logger.debug("任务名称 = [" + scheduleJob.getJobName() + "]----------启动成功");
	}
}
