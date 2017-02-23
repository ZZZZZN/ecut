package com.krt.admin.system.controller;

import com.krt.admin.system.entity.QuartzJob;
import com.krt.admin.system.service.QuartzJobService;
import com.krt.common.annotation.LogAop;
import com.krt.common.base.BaseController;
import com.krt.common.bean.DataTable;
import com.krt.common.bean.ReturnBean;
import com.krt.common.util.SpringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.quartz.CronScheduleBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 殷帅
 * @version 1.0
 * @Description: 任务调度控制层
 * @date 2016年7月22日
 */
@SuppressWarnings({"unchecked", "rawtypes"})
@Controller
public class QuartzJobController extends BaseController {

    @Resource
    private QuartzJobService quartzJobService;

    /**
     * 表达式设置
     *
     * @return
     */
    @RequestMapping("admin/system/quartzJob/quartzCron")
    public String quartzCron() {
        return "admin/system/quartzJob/quartzCron";
    }

    /**
     * 任务调度管理
     *
     * @return
     */
    @RequiresPermissions("quartzJob:list")
    @RequestMapping("admin/system/quartzJob/quartzJob_listUI")
    public String quartzJob_listUI() {
        return "admin/system/quartzJob/quartzJob_listUI";
    }

    /**
     * 任务调度管理
     *
     * @param request
     * @return
     */
    @RequiresPermissions("quartzJob:list")
    @RequestMapping("admin/system/quartzJob/quartzJob_list")
    @ResponseBody
    public DataTable quartzJob_list(Integer start, Integer length, Integer draw, HttpServletRequest request) {
        Map para = new HashMap();
        DataTable dg = quartzJobService.selectListPara(start, length, draw, para);
        return dg;
    }

    /**
     * 添加任务
     *
     * @return
     */
    @RequiresPermissions("quartzJob:insert")
    @RequestMapping("admin/system/quartzJob/quartzJob_insertUI")
    public String quartzJob_insertUI() {
        return "admin/system/quartzJob/quartzJob_insertUI";
    }

    /**
     * 添加任务
     *
     * @param quartzJob
     * @return
     */
    @LogAop(description = "添加任务")
    @RequiresPermissions("quartzJob:insert")
    @RequestMapping("admin/system/quartzJob/quartzJob_insert")
    @ResponseBody
    public ReturnBean quartzJob_insert(QuartzJob quartzJob) {
        ReturnBean rb;
        try {
            CronScheduleBuilder.cronSchedule(quartzJob.getCronExpression());
            rb = ReturnBean.getSuccessReturnBean();
        } catch (Exception e) {
            rb = ReturnBean.getCustomReturnBean("error_cron");// cron表达式有误，不能被解析！
            return rb;
        }
        Object obj = null;
        try {
            if (StringUtils.isNotBlank(quartzJob.getSpringId())) {
                obj = SpringUtils.getBean(quartzJob.getSpringId());
            } else {
                Class clazz = Class.forName(quartzJob.getBeanClass());
                obj = clazz.newInstance();
            }
        } catch (Exception e) {
            rb = ReturnBean.getCustomReturnBean("error_class");// 执行任务类不存在
            return rb;
        }
        if (obj == null) {
            rb = ReturnBean.getCustomReturnBean("error_class");// 执行任务类不存在
            return rb;
        } else {
            Class clazz = obj.getClass();
            Method method = null;
            try {
                method = clazz.getMethod(quartzJob.getMethodName(), null);
            } catch (Exception e) {
                rb = ReturnBean.getCustomReturnBean("error_method");// 未找到目标方法
                return rb;
            }
            if (method == null) {
                rb = ReturnBean.getCustomReturnBean("error_method");// 未找到目标方法
                return rb;
            }
        }
        try {
            quartzJob.setJobStatus("0");
            quartzJob.setIsConcurrent("0");
            quartzJobService.insert(quartzJob);
        } catch (Exception e) {
            rb = ReturnBean.getCustomReturnBean("error_nameAndGroup");// 保存失败，检查
            return rb;
        }
        return rb;
    }

    /**
     * 启动 or 停止任务
     *
     * @param id
     * @param jobStatus
     * @return
     */
    @LogAop(description = "启动or停止任务")
    @RequestMapping("admin/system/quartzJob/quartzJob_startOrStop")
    @ResponseBody
    public ReturnBean quartzJob_startOrStop(Integer id, String jobStatus) {
        ReturnBean rb;
        try {
            quartzJobService.updateStatus(id, jobStatus);
            rb = ReturnBean.getSuccessReturnBean();
        } catch (Exception e) {
            rb = ReturnBean.getErrorReturnBean();
            logger.error("启动or停止任务失败！", e);
        }
        return rb;
    }

    /**
     * 修改任务页
     *
     * @return
     */
    @RequiresPermissions("quartzJob:update")
    @RequestMapping("admin/system/quartzJob/quartzJob_updateUI")
    public String quartzJob_updateUI(Integer id, Model model) {
        Map quartzJob = quartzJobService.selectById(id);
        model.addAttribute("quartzJob", quartzJob);
        return "admin/system/quartzJob/quartzJob_updateUI";
    }

    /**
     * 修改任务
     *
     * @param quartzJob
     * @return
     */
    @LogAop(description = "修改任务")
    @RequiresPermissions("quartzJob:update")
    @RequestMapping("admin/system/quartzJob/quartzJob_update")
    @ResponseBody
    public ReturnBean quartzJob_update(QuartzJob quartzJob) {
        ReturnBean rb;
        try {
            CronScheduleBuilder.cronSchedule(quartzJob.getCronExpression());
            rb = ReturnBean.getSuccessReturnBean();
        } catch (Exception e) {
            rb = ReturnBean.getCustomReturnBean("error_cron");// cron表达式有误，不能被解析！
            return rb;
        }
        Object obj = null;
        try {
            if (StringUtils.isNotBlank(quartzJob.getSpringId())) {
                obj = SpringUtils.getBean(quartzJob.getSpringId());
            } else {
                Class clazz = Class.forName(quartzJob.getBeanClass());
                obj = clazz.newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
            rb = ReturnBean.getCustomReturnBean("error_class1");// 执行任务类不存在
            return rb;
        }
        if (obj == null) {
            rb = ReturnBean.getCustomReturnBean("error_class2");// 执行任务类不存在
            return rb;
        } else {
            Class clazz = obj.getClass();
            Method method = null;
            try {
                method = clazz.getMethod(quartzJob.getMethodName(), null);
            } catch (Exception e) {
                rb = ReturnBean.getCustomReturnBean("error_method");// 未找到目标方法
                return rb;
            }
            if (method == null) {
                rb = ReturnBean.getCustomReturnBean("error_method");// 未找到目标方法
                return rb;
            }
        }
        try {
            quartzJobService.update(quartzJob);
        } catch (Exception e) {
            rb = ReturnBean.getCustomReturnBean("error_nameAndGroup");// 保存失败，检查
            return rb;
        }
        return rb;
    }

    /**
     * 删除任务
     *
     * @return
     */
    @LogAop(description = "删除任务")
    @RequiresPermissions("quartzJob:delete")
    @RequestMapping("admin/system/quartzJob/quartzJob_delete")
    @ResponseBody
    public ReturnBean quartzJob_delete(Integer id) {
        ReturnBean rb;
        try {
            quartzJobService.delete(id);
            rb = ReturnBean.getSuccessReturnBean();
        } catch (Exception e) {
            rb = ReturnBean.getErrorReturnBean();
            logger.warn("删除任务失败", e);
        }
        return rb;
    }

    /**
     * 立即执行一次任务
     *
     * @param id
     * @return
     */
    @LogAop(description = "立即执行一次任务")
    @RequiresPermissions("quartzJob:doOnce")
    @RequestMapping("admin/system/quartzJob/quartzJob_doOnce")
    @ResponseBody
    public ReturnBean quartzJob_doOnce(Integer id) {
        ReturnBean rb;
        try {
            quartzJobService.quartzJob_doOnce(id);
            rb = ReturnBean.getSuccessReturnBean();
        } catch (Exception e) {
            rb = ReturnBean.getErrorReturnBean();
            logger.warn("立即执行一次任务失败", e);
        }
        return rb;
    }

    /**
     * 检测任务名和组名
     *
     * @param id       任务id
     * @param jobName  任务名
     * @param jobGroup 组名
     * @return
     */
    @RequestMapping("admin/system/quartzJob/checkJobName")
    @ResponseBody
    public Boolean checkJobName(Integer id, String jobName, String jobGroup) {
        Map para = new HashMap();
        para.put("id", id);
        para.put("jobName", jobName);
        para.put("jobGroup", jobGroup);
        Integer count = quartzJobService.checkJobName(para);
        if (count > 0) {
            return false;
        } else {
            return true;
        }

    }
}
