package com.krt.admin.system.service;

import com.krt.admin.system.entity.QuartzJob;
import com.krt.admin.system.mapper.QuartzJobMapper;
import com.krt.admin.system.util.QuartzJobFactory;
import com.krt.admin.system.util.QuartzJobFactoryDisallowConcurrentExecution;
import com.krt.core.base.BaseMapper;
import com.krt.core.base.BaseServiceImpl;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * @author 殷帅
 * @version 1.0
 * @Description: 任务调度服务层
 * @date 2016年7月22日
 */
@SuppressWarnings({"unchecked", "rawtypes"})
@Service
public class QuartzJobService extends BaseServiceImpl<QuartzJob> {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;
    @Resource
    private QuartzJobMapper quartzJobMapper;

    @Override
    public BaseMapper<QuartzJob> getMapper() {
        return quartzJobMapper;
    }

    /**
     * 启动 or 停止任务
     *
     * @param id
     * @param jobStatus
     * @throws Exception
     */
    public void updateStatus(Integer id, String jobStatus) throws Exception {
        QuartzJob quartzJob = quartzJobMapper.selectEntityById(id);
        if (quartzJob == null) {
            return;
        }
        if ("1".equals(jobStatus)) {
            logger.debug("停止任务");
            deleteJob(quartzJob);
            quartzJob.setJobStatus("0"); // 停止任务
        } else if ("0".equals(jobStatus)) {
            logger.debug("启动任务");
            quartzJob.setJobStatus("1"); // 启动任务
            addJob(quartzJob);
        }
        quartzJobMapper.update(quartzJob);
    }

    /**
     * 停止任务
     *
     * @param quartzJob
     * @throws SchedulerException
     */
    public void deleteJob(QuartzJob quartzJob) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(quartzJob.getJobName(), quartzJob.getJobGroup());
        scheduler.deleteJob(jobKey);
    }

    /**
     * 添加任务
     *
     * @param quartzJob
     * @throws SchedulerException
     */
    public void addJob(QuartzJob quartzJob) throws SchedulerException {
        if (quartzJob == null || !"1".equals(quartzJob.getJobStatus())) {
            return;
        }
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        TriggerKey triggerKey = TriggerKey.triggerKey(quartzJob.getJobName(), quartzJob.getJobGroup());
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        // 不存在，创建一个
        if (null == trigger) {
            Class clazz = "1".equals(quartzJob.getIsConcurrent()) ? QuartzJobFactory.class : QuartzJobFactoryDisallowConcurrentExecution.class;
            JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(quartzJob.getJobName(), quartzJob.getJobGroup()).build();
            jobDetail.getJobDataMap().put("quartzJob", quartzJob);
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(quartzJob.getCronExpression());
            trigger = TriggerBuilder.newTrigger().withIdentity(quartzJob.getJobName(), quartzJob.getJobGroup()).withSchedule(scheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);
        } else {
            // Trigger已存在，那么更新相应的定时设置
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(quartzJob.getCronExpression());
            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        }
    }

    /**
     * 立即执行一次任务
     *
     * @param id
     * @throws SchedulerException
     */
    public void quartzJob_doOnce(Integer id) throws SchedulerException {
        QuartzJob quartzJob = quartzJobMapper.selectEntityById(id);
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(quartzJob.getJobName(), quartzJob.getJobGroup());
        scheduler.triggerJob(jobKey);
    }

    /**
     * 检测任务名 和 组名
     *
     * @param para
     * @return
     */
    public Integer checkJobName(Map para) {
        return quartzJobMapper.checkJobName(para);
    }

    /**
     * 服务器启动初始化正在运行的任务
     *
     * @throws Exception
     */
    @PostConstruct
    public void init() throws Exception {
        //获取所有需要运行的任务
        List<QuartzJob> jobList = quartzJobMapper.selectEntityList();
        for (QuartzJob quartzJob : jobList) {
            addJob(quartzJob);
        }
    }

}
