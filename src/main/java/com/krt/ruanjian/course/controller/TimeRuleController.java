package com.krt.ruanjian.course.controller;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.krt.core.bean.DataTable;
import com.krt.core.bean.ReturnBean;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.krt.ruanjian.course.entity.TimeRule;
import com.krt.ruanjian.course.service.TimeRuleService;
import com.krt.core.annotation.LogAop;
import com.krt.core.base.BaseController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Description:time_rule控制层
 * @author:pengYi
 * @date:2017年09月07日
 * @version:1.0
 */
@SuppressWarnings({ "rawtypes" })
@Controller
public class TimeRuleController extends BaseController {

	@Resource
	private TimeRuleService timeRuleService;

	/**
	 * time_rule管理页
	 * 
	 * @return
	 */
	@RequiresPermissions("timeRule:list")
	@RequestMapping("ruanjian/course/timeRule_listUI")
	public String timeRule_listUI(HttpServletRequest request) {
		Map timeRuleMap = timeRuleService.selectById(1);
		request.setAttribute("timeRule", timeRuleMap);
		return "ruanjian/course/timeRule_listUI";
	}

	/**
	 * time_rule管理
	 * 
	 * @param start
	 *            起始数
	 * @param length
	 *            每页显示行数
	 * @param draw
	 *            客户端请求次数
	 * @param request
	 * @return
	 */
	@RequiresPermissions("timeRule:list")
	@RequestMapping("ruanjian/course/timeRule_list")
	@ResponseBody
	public DataTable timeRule_list(Integer start, Integer length, Integer draw,
								   HttpServletRequest request) {
		Map para = new HashMap();
		DataTable dt = timeRuleService.selectListPara(start, length, draw, para);
		return dt;
	}

	/**
	 * 新增time_rule页
	 * 
	 * @return
	 */
	@RequiresPermissions("timeRule:insert")
	@RequestMapping("ruanjian/course/timeRule_insertUI")
	public String timeRule_insertUI(HttpServletRequest request) { return "ruanjian/course/timeRule_insertUI"; }

	/**
	 * 添加time_rule
	 * 
	 * @param timeRule
	 *            time_rule
	 * @return
	 */
	@LogAop(description = "添加time_rule")
//	@RequiresPermissions("timeRule:insert")
	@RequestMapping("ruanjian/course/timeRule_insert")
	@ResponseBody
	public ReturnBean timeRule_insert(TimeRule timeRule) {
		ReturnBean rb;
		try {
			timeRuleService.insert(timeRule);
			rb = ReturnBean.getSuccessReturnBean();
		} catch (Exception e) {
			logger.error("添加time_rule失败", e);
			rb = ReturnBean.getErrorReturnBean();
		}
		return rb;
	}

	/**
	 * 修改time_rule页
	 * 
	 * @param id
	 *            time_rule id
	 * @param request
	 * @return
	 */
	@RequiresPermissions("timeRule:update")
	@RequestMapping("ruanjian/course/timeRule_updateUI")
	public String timeRule_updateUI(Integer id, HttpServletRequest request) {
		Map timeRuleMap = timeRuleService.selectById(id);
		request.setAttribute("timeRule", timeRuleMap);
		return "ruanjian/course/timeRule_updateUI";
	}

	/**
	 * 修改time_rule
	 * 
	 * @param timeRule
	 *            time_rule
	 * @return
	 */
	@LogAop(description = "修改time_rule")
//	@RequiresPermissions("timeRule:update")
	@RequestMapping("ruanjian/course/timeRule_update")
	@ResponseBody
	public ReturnBean timeRule_update(TimeRule timeRule) {
		ReturnBean rb;
		try {
			timeRuleService.update(timeRule);
			rb = ReturnBean.getSuccessReturnBean();
		} catch (Exception e) {
			logger.error("修改time_rule失败", e);
			rb = ReturnBean.getErrorReturnBean();
		}
		return rb;
	}

	/**
	 * 删除time_rule
	 * 
	 * @param id
	 *            time_rule id
	 * @return
	 */
	@LogAop(description = "删除time_rule")
	@RequiresPermissions("timeRule:delete")
	@RequestMapping("ruanjian/course/timeRule_delete")
	@ResponseBody
	public ReturnBean timeRule_delete(Integer id) {
		ReturnBean rb;
		try {
			timeRuleService.delete(id);
			rb = ReturnBean.getSuccessReturnBean();
		} catch (Exception e) {
			logger.error("删除time_rule失败", e);
			rb = ReturnBean.getErrorReturnBean();
		}
		return rb;
	}
}
