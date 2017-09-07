package com.krt.ruanjian.course.controller;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.krt.ruanjian.course.entity.Major;
import com.krt.ruanjian.course.service.MajorService;
import com.krt.core.bean.DataTable;
import com.krt.core.bean.ReturnBean;
import com.krt.core.annotation.LogAop;
import com.krt.core.base.BaseController;

/**
 * @Description:专业表控制层
 * @author:pengYi
 * @date:2017年09月07日
 * @version:1.0
 */
@SuppressWarnings({ "rawtypes" })
@Controller
public class MajorController extends BaseController {

	@Resource
	private MajorService majorService;

	/**
	 * 专业表管理页
	 * 
	 * @return
	 */
	@RequiresPermissions("major:list")
	@RequestMapping("ruanjian/course/major_listUI")
	public String major_listUI() {
		return "ruanjian/course/major_listUI";
	}

	/**
	 * 专业表管理
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
	@RequiresPermissions("major:list")
	@RequestMapping("ruanjian/course/major_list")
	@ResponseBody
	public DataTable major_list(Integer start, Integer length, Integer draw,
			HttpServletRequest request) {
		Map para = new HashMap();
		DataTable dt = majorService.selectListPara(start, length, draw, para);
		return dt;
	}

	/**
	 * 新增专业表页
	 * 
	 * @return
	 */
	@RequiresPermissions("major:insert")
	@RequestMapping("ruanjian/course/major_insertUI")
	public String major_insertUI(HttpServletRequest request) {
		return "ruanjian/course/major_insertUI";
	}

	/**
	 * 添加专业表
	 * 
	 * @param major
	 *            专业表
	 * @return
	 */
	@LogAop(description = "添加专业表")
	@RequiresPermissions("major:insert")
	@RequestMapping("ruanjian/course/major_insert")
	@ResponseBody
	public ReturnBean major_insert(Major major) {
		ReturnBean rb;
		try {
			majorService.insert(major);
			rb = ReturnBean.getSuccessReturnBean();
		} catch (Exception e) {
			logger.error("添加专业表失败", e);
			rb = ReturnBean.getErrorReturnBean();
		}
		return rb;
	}

	/**
	 * 修改专业表页
	 * 
	 * @param id
	 *            专业表 id
	 * @param request
	 * @return
	 */
	@RequiresPermissions("major:update")
	@RequestMapping("ruanjian/course/major_updateUI")
	public String major_updateUI(Integer id, HttpServletRequest request) {
		Map majorMap = majorService.selectById(id);
		request.setAttribute("major", majorMap);
		return "ruanjian/course/major_updateUI";
	}

	/**
	 * 修改专业表
	 * 
	 * @param major
	 *            专业表
	 * @return
	 */
	@LogAop(description = "修改专业表")
	@RequiresPermissions("major:update")
	@RequestMapping("ruanjian/course/major_update")
	@ResponseBody
	public ReturnBean major_update(Major major) {
		ReturnBean rb;
		try {
			majorService.update(major);
			rb = ReturnBean.getSuccessReturnBean();
		} catch (Exception e) {
			logger.error("修改专业表失败", e);
			rb = ReturnBean.getErrorReturnBean();
		}
		return rb;
	}

	/**
	 * 删除专业表
	 * 
	 * @param id
	 *            专业表 id
	 * @return
	 */
	@LogAop(description = "删除专业表")
	@RequiresPermissions("major:delete")
	@RequestMapping("ruanjian/course/major_delete")
	@ResponseBody
	public ReturnBean major_delete(Integer id) {
		ReturnBean rb;
		try {
			majorService.delete(id);
			rb = ReturnBean.getSuccessReturnBean();
		} catch (Exception e) {
			logger.error("删除专业表失败", e);
			rb = ReturnBean.getErrorReturnBean();
		}
		return rb;
	}
}
