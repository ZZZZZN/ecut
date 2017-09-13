package com.krt.ruanjian.course.controller;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.krt.core.bean.DataTable;
import com.krt.core.bean.ReturnBean;
import com.krt.core.util.ShiroUtil;
import com.krt.ruanjian.course.entity.Title;
import com.krt.ruanjian.course.service.TitleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.krt.ruanjian.course.entity.TitleExamine;
import com.krt.ruanjian.course.service.TitleExamineService;
import com.krt.core.annotation.LogAop;
import com.krt.core.base.BaseController;

/**
 * @Description:审核表（记录学生申请的题目）控制层
 * @author:pengYi
 * @date:2017年09月08日
 * @version:1.0
 */
@SuppressWarnings({ "rawtypes" })
@Controller
public class TitleExamineController extends BaseController {

	@Resource
	private TitleExamineService titleExamineService;

	@Resource
	private TitleService titleService;

	/**
	 * 审核表（记录学生申请的题目）管理页
	 * 
	 * @return
	 */
	@RequiresPermissions("titleExamine:list")
	@RequestMapping("ruanjian/course/titleExamine_listUI")
	public String titleExamine_listUI() {
		return "ruanjian/course/titleExamine_listUI";
	}

	/**
	 * 审核表（记录学生申请的题目）管理
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
	@RequiresPermissions("titleExamine:list")
	@RequestMapping("ruanjian/course/titleExamine_list")
	@ResponseBody
	public DataTable titleExamine_list(Integer start, Integer length, Integer draw,
									   HttpServletRequest request) {
		String status=request.getParameter("status");
		Map para = new HashMap();
		Map user = ShiroUtil.getCurrentUser();
		Integer authorId = (Integer)user.get("id");
		para.put("authorId", authorId);
		para.put("status", status);
		para.put("role",user.get("roleCode"));
		DataTable dt = titleExamineService.selectListPara(start, length, draw, para);
		return dt;
	}

	/**
	 * 新增审核表（记录学生申请的题目）页
	 * 
	 * @return
	 */
	@RequiresPermissions("titleExamine:insert")
	@RequestMapping("ruanjian/course/titleExamine_insertUI")
	public String titleExamine_insertUI(HttpServletRequest request) {
		return "ruanjian/course/titleExamine_insertUI";
	}

	/**
	 * 添加审核表（记录学生申请的题目）
	 * 
	 * @param titleExamine
	 *            审核表（记录学生申请的题目）
	 * @return
	 */
	@LogAop(description = "添加审核表（记录学生申请的题目）")
	@RequiresPermissions("titleExamine:insert")
	@RequestMapping("ruanjian/course/titleExamine_insert")
	@ResponseBody
	public ReturnBean titleExamine_insert(TitleExamine titleExamine) {
		ReturnBean rb;
		try {
			titleExamineService.insert(titleExamine);
			rb = ReturnBean.getSuccessReturnBean();
		} catch (Exception e) {
			logger.error("添加审核表（记录学生申请的题目）失败", e);
			rb = ReturnBean.getErrorReturnBean();
		}
		return rb;
	}

	/**
	 * 修改审核表（记录学生申请的题目）页
	 * 
	 * @param id
	 *            审核表（记录学生申请的题目） id
	 * @param request
	 * @return
	 */
	@RequiresPermissions("titleExamine:update")
	@RequestMapping("ruanjian/course/titleExamine_updateUI")
	public String titleExamine_updateUI(Integer id, HttpServletRequest request) {
		Map titleExamineMap = titleExamineService.selectById(id);
		request.setAttribute("titleExamine", titleExamineMap);
		return "ruanjian/course/titleExamine_updateUI";
	}

	/**
	 * 修改审核表（记录学生申请的题目）
	 * 
	 * @param titleExamine
	 *            审核表（记录学生申请的题目）
	 * @return
	 */
	@LogAop(description = "修改审核表（记录学生申请的题目）")
	@RequiresPermissions("titleExamine:update")
	@RequestMapping("ruanjian/course/titleExamine_update")
	@ResponseBody
	public ReturnBean titleExamine_update(TitleExamine titleExamine) {
		ReturnBean rb;
		try {
			titleExamineService.update(titleExamine);
			rb = ReturnBean.getSuccessReturnBean();
		} catch (Exception e) {
			logger.error("修改审核表（记录学生申请的题目）失败", e);
			rb = ReturnBean.getErrorReturnBean();
		}
		return rb;
	}

	/**
	 * 删除审核表（记录学生申请的题目）
	 * 
	 * @param id
	 *            审核表（记录学生申请的题目） id
	 * @return
	 */
	@LogAop(description = "删除审核表（记录学生申请的题目）")
	@RequiresPermissions("titleExamine:delete")
	@RequestMapping("ruanjian/course/titleExamine_delete")
	@ResponseBody
	public ReturnBean titleExamine_delete(Integer id) {
		ReturnBean rb;
		try {
			titleExamineService.delete(id);
			rb = ReturnBean.getSuccessReturnBean();
		} catch (Exception e) {
			logger.error("删除审核表（记录学生申请的题目）失败", e);
			rb = ReturnBean.getErrorReturnBean();
		}
		return rb;
	}

	/**
	 * 教师审核
	 * @author pengYi
	 * @date 2017-9-10
	 */
	@RequiresPermissions("titleExamine:passOrFail")
	@RequestMapping("ruanjian/course/titleExamine_passOrFail")
	@ResponseBody
	public ReturnBean titleExamine_passOrFail(Integer id, String status) {
		ReturnBean rb = null;
		Map param = new HashMap();
		param.put("id", id);
		param.put("status", status);
		int result = titleExamineService.updateStatusById(param);
		if (result == 1) {
			rb = ReturnBean.getSuccessReturnBean();
			return rb;
		} else {
			rb = ReturnBean.getErrorReturnBean();
			return rb;
		}
	}

//	/**
//	 * 选题结果页面
//	 */
//	@RequiresPermissions("titleExamine:passList")
//	@RequestMapping("ruanjian/course/titleExamine_passListUI")
//	public String titleExamine_passListtUI() {
//
//		return "ruanjian/course/titleExamine_passListUI";
//	}
//
//	/**
//	 * 返回选题结果集合
//	 */
//	@RequiresPermissions("titleExamine:passList")
//	@RequestMapping("ruanjian/course/titleExamine_passList")
//	@ResponseBody
//	public DataTable titleExamine_passList(Integer start, Integer length, Integer draw,
//									   HttpServletRequest request) {
//		Map para = new HashMap();
//		Map user = ShiroUtil.getCurrentUser();
//		Integer authorId = (Integer)user.get("id");
//		para.put("authorId", authorId);
//		para.put("status", 2);
//		DataTable dt = titleExamineService.selectListPara(start, length, draw, para);
//		return dt;
//	}

	@RequiresPermissions("titleExamine:bossList")
	@RequestMapping("ruanjian/course/boss/titleExamine_bossListUI")
	public String titleExamine_bossListUI() {

		return "ruanjian/course/boss/titleExamine_bossListUI";
	}

	/**
	 * 系主任查看该专业老师上传题目
	 */
	@RequiresPermissions("titleExamine:bossList")
	@RequestMapping("ruanjian/course/boss/titleExamine_bossList")
	@ResponseBody
	public DataTable titleExamine_bossList(Integer start, Integer length, Integer draw,
									   HttpServletRequest request) {
		Map para = new HashMap();
		Map user = ShiroUtil.getCurrentUser();
		Integer id = (Integer)user.get("id");
		para.put("userId", id);
		para.put("flag", "1");
		DataTable dt = titleExamineService.getTitleByMajor(start, length, draw, para);
		return dt;
	}
	/**
	 * 系主任审核
	 * @author pengYi
	 * @date 2017-9-11
	 */
	@RequiresPermissions("titleExamine:anoPassOrFail")
	@RequestMapping("ruanjian/course/boss/titleExamine_anoPassOrFail")
	@ResponseBody
	public ReturnBean titleExamine_anoPassOrFail(Integer id, Integer flag) {
		ReturnBean rb = null;
		Title title = new Title();
		title.setId(id);
		title.setFlag(flag);
		try {
			titleService.update(title);
			rb = ReturnBean.getSuccessReturnBean();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rb;
	}

	@RequiresPermissions("titleExamine:bossResultList")
	@RequestMapping("ruanjian/course/boss/titleExamine_bossResultListUI")
	public String titleExamine_bossResultListUI() {

		return "ruanjian/course/boss/titleExamine_bossResultListUI";
	}

	/**
	 * 系主任审题结果
	 */
	@RequiresPermissions("titleExamine:bossResultList")
	@RequestMapping("ruanjian/course/boss/titleExamine_bossResultList")
	@ResponseBody
	public DataTable titleExamine_bossResultList(Integer start, Integer length, Integer draw,
										   HttpServletRequest request) {
		Map para = new HashMap();
		Map user = ShiroUtil.getCurrentUser();
		Integer id = (Integer)user.get("id");
		para.put("userId", id);
		para.put("flag",null);
		DataTable dt = titleExamineService.getTitleByMajor(start, length, draw, para);
		return dt;
	}

}
