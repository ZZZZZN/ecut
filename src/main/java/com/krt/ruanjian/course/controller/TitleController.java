package com.krt.ruanjian.course.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.krt.ruanjian.course.service.MajorService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.krt.ruanjian.course.entity.Title;
import com.krt.ruanjian.course.service.TitleService;
import com.krt.core.bean.DataTable;
import com.krt.core.bean.ReturnBean;
import com.krt.core.annotation.LogAop;
import com.krt.core.base.BaseController;

/**
 * @Description:选题表控制层
 * @author:pengYi
 * @date:2017年09月07日
 * @version:1.0
 */
@SuppressWarnings({ "rawtypes" })
@Controller
public class TitleController extends BaseController {

	@Resource
	private TitleService titleService;
	@Resource
	private MajorService majorService;

	/**
	 * 选题表管理页
	 * 
	 * @return
	 */
	@RequiresPermissions("title:list")
	@RequestMapping("ruanjian/course/title_listUI")
	public String title_listUI() {
		return "ruanjian/course/title_listUI";
	}

	/**
	 * 选题表管理
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
	@RequiresPermissions("title:list")
	@RequestMapping("ruanjian/course/title_list")
	@ResponseBody
	public DataTable title_list(Integer start, Integer length, Integer draw,
			HttpServletRequest request) {
		Map para = new HashMap();
		DataTable dt = titleService.selectListPara(start, length, draw, para);
		return dt;
	}

	/**
	 * 新增选题表页
	 * 
	 * @return
	 */
	@RequiresPermissions("title:insert")
	@RequestMapping("ruanjian/course/title_insertUI")
	public String title_insertUI(HttpServletRequest request) {
        List<Map> map= majorService.selectAll();
        request.setAttribute("map",map);
		return "ruanjian/course/title_insertUI";
	}

	/**
	 * 添加选题表
	 * 
	 * @param title
	 *            选题表
	 * @return
	 */
	@LogAop(description = "添加选题表")
	@RequiresPermissions("title:insert")
	@RequestMapping("ruanjian/course/title_insert")
	@ResponseBody
	public ReturnBean title_insert(Title title) {
		ReturnBean rb;
		try {
			titleService.insert(title);
			rb = ReturnBean.getSuccessReturnBean();
		} catch (Exception e) {
			logger.error("添加选题表失败", e);
			rb = ReturnBean.getErrorReturnBean();
		}
		return rb;
	}

	/**
	 * 修改选题表页
	 * 
	 * @param id
	 *            选题表 id
	 * @param request
	 * @return
	 */
	@RequiresPermissions("title:update")
	@RequestMapping("ruanjian/course/title_updateUI")
	public String title_updateUI(Integer id, HttpServletRequest request) {
		Map titleMap = titleService.selectById(id);
		request.setAttribute("title", titleMap);
		return "ruanjian/course/title_updateUI";
	}

	/**
	 * 修改选题表
	 * 
	 * @param title
	 *            选题表
	 * @return
	 */
	@LogAop(description = "修改选题表")
	@RequiresPermissions("title:update")
	@RequestMapping("ruanjian/course/title_update")
	@ResponseBody
	public ReturnBean title_update(Title title) {
		ReturnBean rb;
		try {
			titleService.update(title);
			rb = ReturnBean.getSuccessReturnBean();
		} catch (Exception e) {
			logger.error("修改选题表失败", e);
			rb = ReturnBean.getErrorReturnBean();
		}
		return rb;
	}

	/**
	 * 删除选题表
	 * 
	 * @param id
	 *            选题表 id
	 * @return
	 */
	@LogAop(description = "删除选题表")
	@RequiresPermissions("title:delete")
	@RequestMapping("ruanjian/course/title_delete")
	@ResponseBody
	public ReturnBean title_delete(Integer id) {
		ReturnBean rb;
		try {
			titleService.delete(id);
			rb = ReturnBean.getSuccessReturnBean();
		} catch (Exception e) {
			logger.error("删除选题表失败", e);
			rb = ReturnBean.getErrorReturnBean();
		}
		return rb;
	}
}
