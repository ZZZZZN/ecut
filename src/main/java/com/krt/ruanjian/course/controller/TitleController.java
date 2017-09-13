package com.krt.ruanjian.course.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.krt.admin.system.service.UserService;
import com.krt.core.util.DateUtil;
import com.krt.core.util.ShiroUtil;
import com.krt.ruanjian.course.entity.TitleExamine;
import com.krt.ruanjian.course.enums.MajorEnum;
import com.krt.ruanjian.course.service.MajorService;
import com.krt.ruanjian.course.service.TitleExamineService;
import org.apache.shiro.SecurityUtils;
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
	@Resource
	private TitleExamineService titleExamineService;
	@Resource
	private UserService userService;

	/**
	 * 选题表管理页
	 *
	 * @ return
	 */

	@RequiresPermissions("title:list")
	@RequestMapping("ruanjian/course/title_listUI")
	public String title_listUI() {
		return "ruanjian/course/title_listUI";
	}

	/**
	 * 学生申请课题管理页
	 * 
	 * @return
	 */
	@RequiresPermissions("title:application")
	@RequestMapping("ruanjian/course/title_Application_listUI")
	public String title_Application_listUI() {
		return "ruanjian/titleApplication/title_Application_listUI";
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
		Map user = ShiroUtil.getCurrentUser();
		Integer userId = (Integer)user.get("id");
		String roleCode = (String)user.get("roleCode");
		para.put("userId", userId);
		para.put("roleCode", roleCode);
		DataTable dt = titleService.selectListPara(start, length, draw, para);
		//取出list中的data值将专业代码转换成专业名称
		/*List<HashMap<String, String>> list = dt.getData();
		for (int i =0; i < list.size(); i++) {
			String[] array = list.get(i).get("suitMajor").split(",");
			String newData = "";
			MajorEnum tmpEnum;
			for (int j = 0; j < array.length; j++) {
				tmpEnum = MajorEnum.getMajorNameByCode(array[j]);
				if (j+1 == array.length) {
					newData += tmpEnum.getName() ;
				} else {
					newData += tmpEnum.getName() + ",";
				}
			}
			list.get(i).put("suitMajor", newData);
		}*/
		return dt;
	}
	/*
	* 选题结果查看
	* */
	@RequiresPermissions("title:result")
	@RequestMapping("ruanjian/course/title_Application_ResultUI")
	public String title_Application_resultUI() {
		return "ruanjian/titleApplication/title_Application_ResultUI";
	}

	/**
	 *
	 */
	@RequiresPermissions("title:result")
	@RequestMapping("ruanjian/course/title_application_Resulelist")
	@ResponseBody
	public DataTable title_application_Resultlist(Integer start, Integer length, Integer draw,
											HttpServletRequest request) {
		Map para = new HashMap();
		Map info=userService.selectById(Integer.parseInt(ShiroUtil.getCurrentUser().get("id").toString()));
		/*Map major= majorService.selectMajorCodeByMajorName(info.get("major").toString());*/
		para.put("id",info.get("id"));
		para.put("major",info.get("major"));
		para.put("role",info.get("roleCode"));
		DataTable dt = titleExamineService.selectByApplicant(start,length,draw,para);
		return dt;
	}

	/**
	 * 学生选题表管理
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
	@RequiresPermissions("title:application")
	@RequestMapping("ruanjian/course/title_application_list")
	@ResponseBody
	public DataTable title_application_list(Integer start, Integer length, Integer draw,
								HttpServletRequest request) {
		Map para = new HashMap();
        Map info=userService.selectById(Integer.parseInt(ShiroUtil.getCurrentUser().get("id").toString()));
		/*Map major= majorService.selectMajorCodeByMajorName(info.get("major").toString());*/
        para.put("id",info.get("id"));
        para.put("major",info.get("major"));
        para.put("role",info.get("roleCode"));
		DataTable dt = titleService.selectListStudent(start, length, draw, para);
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
			Map user = ShiroUtil.getCurrentUser();
            title.setTs(DateUtil.dateToString("yyyy-MM-dd HH:mm:ss"
					, DateUtil.getIntenetTime()));
			title.setAuthor((Integer)user.get("id"));
			title.setDr(0);
			title.setFlag(1);
			titleService.insert(title);
			rb = ReturnBean.getSuccessReturnBean();
		} catch (Exception e) {
			logger.error("添加选题表失败", e);
			rb = ReturnBean.getErrorReturnBean();
		}
		return rb;
	}

	@LogAop(description = "申请选题")
	@RequiresPermissions("title:application")
	@RequestMapping("ruanjian/course/title_application")
	@ResponseBody
	public ReturnBean title_application(HttpServletRequest request) {
		ReturnBean rb;
		try {
			String id= request.getParameter("id");
			Map map= titleService.selectById(Integer.parseInt(id));
			Integer number= titleService.countPassNumber(id);
			if (number<Integer.parseInt(map.get("limit_person").toString())) {
				TitleExamine titleExamine = new TitleExamine();
				titleExamine.setTitle_id(Integer.parseInt(id));
				titleExamine.setAuditor(Integer.parseInt(map.get("author").toString()));
				titleExamine.setApplicant(Integer.parseInt(ShiroUtil.getCurrentUser().get("id").toString()));
				titleExamine.setTs(DateUtil.dateToString("yyyy-MM-dd HH:mm:ss", DateUtil.getIntenetTime()));
				titleExamine.setDr(0);
				titleExamine.setStatus("1");
				titleExamineService.insert(titleExamine);
				rb = ReturnBean.getSuccessReturnBean();
			}
			else
			{
				rb=ReturnBean.getCustomReturnBean("overstep");
			}
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
		List<Map> map= majorService.selectAll();
		request.setAttribute("map",map);
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
	 * 查看选题表页
	 *
	 * @param id
	 *            选题表 id
	 * @param request
	 * @return
	 */
	@RequiresPermissions("title:applicationSee")
	@RequestMapping("ruanjian/course/title_seeUI")
	public String title_seeUI(Integer id, HttpServletRequest request) {
		Map titleMap = titleService.selectById(id);
		/*String[] array = ((String)titleMap.get("suitMajor")).split(",");
		MajorEnum majorEnum;
		String newData = "";
		for (int i = 0; i< array.length; i++) {
			majorEnum = MajorEnum.getMajorNameByCode(array[i]);
			if (i+1 == array.length) {
				newData += majorEnum.getName() ;
			} else {
				newData += majorEnum.getName() + ",";
			}
		}*/
		request.setAttribute("title", titleMap);
		return "ruanjian/course/title_seeUI";
	}

	/**
	 * 查看选题表页
	 *
	 * @param id
	 *            选题表 id
	 * @param request
	 * @return
	 */
	@RequiresPermissions("title:applicationSee")
	@RequestMapping("ruanjian/course/title_application_seeUI")
	public String title_application_seeUI(Integer id, HttpServletRequest request) {
		Map titleMap = titleService.selectById(id);
		/*String[] array = ((String)titleMap.get("suitMajor")).split(",");
		MajorEnum majorEnum;
		String newData = "";
		for (int i = 0; i< array.length; i++) {
			majorEnum = MajorEnum.getMajorNameByCode(array[i]);
			if (i+1 == array.length) {
				newData += majorEnum.getName() ;
			} else {
				newData += majorEnum.getName() + ",";
			}
		}*/
		request.setAttribute("title", titleMap);
		return "ruanjian/titleApplication/title_ApplicationseeUI";
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
