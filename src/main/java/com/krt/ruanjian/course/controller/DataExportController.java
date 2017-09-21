package com.krt.ruanjian.course.controller;

import com.krt.core.base.BaseController;
import com.krt.core.bean.DataTable;
import com.krt.core.bean.ReturnBean;
import com.krt.core.util.ExcelUtil;
import com.krt.core.util.ShiroUtil;
import com.krt.ruanjian.course.entity.TitleExamine;
import com.krt.ruanjian.course.mapper.TitleMapper;
import com.krt.ruanjian.course.service.DataExportService;
import com.krt.ruanjian.course.service.TitleExamineService;
import com.krt.ruanjian.course.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据导出控制层
 */
@Controller
public class DataExportController extends BaseController {

    @Autowired
    private TitleExamineService titleExamineService;
    @Autowired
    private TitleService titleService;
    @Autowired
    private TitleMapper titleMapper;


    @Autowired
    private DataExportService dataExportService;

    /**
     * 返回学生选题数据页面
     */
    @RequestMapping("ruanjian/course/boss/stuSelDataExportUI")
    public String stuSelDataExportUI() {
        return "ruanjian/course/boss/stuSelDataExportUI";
    }

    /**
     * 渲染学生选题数据页面
     */
    @RequestMapping("ruanjian/course/boss/stuSelDataExport")
    @ResponseBody
    public DataTable stuSelDataExport(Integer start, Integer length, Integer draw,
                                      HttpServletRequest request) {
        Map para = new HashMap();
        String stuNo = request.getParameter("stuNo");
        String teacName = request.getParameter("teacName");
        para.put("stuNo", stuNo);
        para.put("teacName", teacName);
        DataTable dt = titleExamineService.getStuSelData(start, length, draw, para);
        return dt;
    }

    /**
     * 返回教师所带学生信息页面
     */
    @RequestMapping("ruanjian/course/boss/teachersStuDataExportUI")
    public String teachersStuDataExportUI() {
        return "ruanjian/course/boss/teachersStuDataExportUI";
    }

    /**
     * 渲染教师所带学生信息页面
     */
    @RequestMapping("ruanjian/course/boss/teachersStuDataExport")
    @ResponseBody
    public DataTable teachersStuDataExport(Integer start, Integer length, Integer draw,
                                      HttpServletRequest request) {
        Map para = new HashMap();
        String stuNo = request.getParameter("stuNo");
        String teacName = request.getParameter("teacName");
        para.put("stuNo", stuNo);
        para.put("teacName", teacName);
        DataTable dt = titleExamineService.getStuSelData(start, length, draw, para);
        return dt;
    }


    /**
     * 导出学生选题数据 excel文件
     */
    @RequestMapping("ruanjian/course/boss/exportExcelForAdmin")
    @ResponseBody
    public ReturnBean exportExcelForAdmin(HttpServletRequest request, HttpServletResponse resp) throws Exception {
        ReturnBean rb =null;
        Map para = new HashMap();
        List<Map> list = titleExamineService.getStuSelDataList(para);
        //添加sheet
        Map map = new HashMap();
        map.put("sheetName","选题结果");
        List<Map> newList = new ArrayList<Map>();
        newList.add(map);
        for(Map tmp : list) {
            newList.add(tmp);
        }
        String fileName="学生选题数据";
        //列名
        String columnNames[]={"学号","学生姓名","学生班级","备注","设计题目",
                "课题类型","课题来源","指导老师姓名","职称"};
        //map中的key
        String keys[] = {"stuNo","stuName","stuClass","note","titleName",
                "titleType","titleSource","teacName","titleLevel"};
        dataExportService.stuSelDataExport(resp, fileName,newList,keys,columnNames);
        return rb;
    }

    /**
     * 导出教师所带学生信息 excel文件
     */
    @RequestMapping("ruanjian/course/boss/exportExcelForTeacher")
    @ResponseBody
    public ReturnBean exportExcelForTeacher(HttpServletRequest request, HttpServletResponse resp) throws Exception {
        ReturnBean rb =null;
        Map para = new HashMap();
        List<Map> list = titleExamineService.getStuSelDataList(para);
        //添加sheet
        Map map = new HashMap();
        map.put("sheetName","sheet1");
        List<Map> newList = new ArrayList<Map>();
        newList.add(map);
        for(Map tmp : list) {
            newList.add(tmp);
        }
        String fileName="教师所带学生信息";
        //列名
        String columnNames[]={"学号","学生姓名","学生班级","学生专业","备注",
                "指导老师姓名","指导老师学历","教师所在系"};
        //map中的key
        String keys[] = {"stuNo","stuName","stuClass","majorName","note",
                 "teacName","teacEducation","department"};
        dataExportService.stuSelDataExport(resp, fileName,newList,keys,columnNames);
        return rb;
    }
    @RequestMapping("ruanjian/course/titleExport")
    public ReturnBean titleExport(HttpServletRequest request,HttpServletResponse response){
        ReturnBean rb =null;
        Map para = new HashMap();
        Map user = ShiroUtil.getCurrentUser();
        Integer  userId = Integer.parseInt(user.get("id").toString());
        String  roleCode = user.get("username").toString();
        String author= request.getParameter("author");
        String titlename=request.getParameter("titlename");
        String flag=request.getParameter("flag");
        para.put("userId", userId);
        para.put("author",author);
        para.put("titlename",titlename);
        para.put("roleCode",roleCode);
        para.put("flag",flag);
        List<Map> list =titleService.getMapper().selectListPara(para);
        //添加sheet
        Map map = new HashMap();
        map.put("sheetName","拟题数据");
        List<Map> newList = new ArrayList<Map>();
        newList.add(map);
        for(Map tmp : list) {
            newList.add(tmp);
        }
        String fileName="拟题数据导出";
        //列名
        String columnNames[]={"课题名称","课题类型","适用专业","适用实训所在地",
                "上限人数","出题老师","学历","课程意义与目标","学生基本条件和前期工作"};
        //map中的key
        String keys[] = {"title_name","title_type","suitMajorName","suitScope",
                "limit_person","author","education","meaning_target","condition_work"};
        try {
            dataExportService.stuSelDataExport(response,fileName,newList,keys,columnNames);
        } catch (IOException e) {
            rb=ReturnBean.getErrorReturnBean();
            e.printStackTrace();
            return rb;
        }
        rb=ReturnBean.getSuccessReturnBean();
        return rb;
    }

    @RequestMapping("ruanjian/course/teacherExport")
    public ReturnBean exportExport(HttpServletRequest request,HttpServletResponse response){
        ReturnBean rb =null;
        Map para = new HashMap();
        String teacher= request.getParameter("teacher");
        para.put("teacher",teacher);
        List<Map> list = titleMapper.teacherExport(para);
        //添加sheet
        Map map = new HashMap();
        map.put("sheetName","教师所带人数");
        List<Map> newList = new ArrayList<Map>();
        newList.add(map);
        for(Map tmp : list) {
            newList.add(tmp);
        }
        String fileName="教师所带人数导出";
        //列名
        String columnNames[]={"指导老师","职称","指导老师学历","审核通过题目数","选题通过学生人数",
                "未选到学生题目数"};
        //map中的key
        String keys[] = {"name","title_level","teacEducation","number","passnumber",
                "notselectednumber"};
        try {
            dataExportService.stuSelDataExport(response,fileName,newList,keys,columnNames);
        } catch (IOException e) {
            rb=ReturnBean.getErrorReturnBean();
            e.printStackTrace();
            return rb;
        }
        rb=ReturnBean.getSuccessReturnBean();
        return rb;
    }

    /**
     * 系主任页面导出
     */
    @RequestMapping("ruanjian/course/boss/exportExcelForDean")
    @ResponseBody
    public ReturnBean exportExcelForDean(HttpServletRequest request, HttpServletResponse resp) throws Exception {
        ReturnBean rb =null;
        Map user = ShiroUtil.getCurrentUser();
        Map para = new HashMap();
        para.put("flag", "");
        para.put("titlename","");
        para.put("author","");
        para.put("userId",user.get("id").toString());
        List<Map> list = titleExamineService.exportExcelForDean(para);
        //添加sheet
        Map map = new HashMap();
        map.put("sheetName","sheet1");
        List<Map> newList = new ArrayList<Map>();
        newList.add(map);
        for(Map tmp : list) {
            newList.add(tmp);
        }
        String fileName="教师拟题数据";
        //列名
        String columnNames[]={"课题名称","教师姓名","课题类型","课题来源","适用专业",
                "适用实训在地","课程意义与目标","学生基本条件","状态"};
        //map中的key
        String keys[] = {"titleName","teacherName","titleType","titleSource",
                "suitMajorName","suitScope","meaningTarget","conditionWork","flag"};
        try {
            dataExportService.stuSelDataExport(resp,fileName,newList,keys,columnNames);
        } catch (IOException e) {
            rb=ReturnBean.getErrorReturnBean();
            e.printStackTrace();
            return rb;
        }
        rb=ReturnBean.getSuccessReturnBean();
        return rb;
    }

    /**
     * 教师管理的申请记录导出
     */
    @RequestMapping("ruanjian/course/boss/exportExcelForTeaccher")
    @ResponseBody
    public ReturnBean exportExcelForTeaccher(HttpServletRequest request, HttpServletResponse resp) throws Exception {
        ReturnBean rb =null;
        Map para = new HashMap();
        Map user = ShiroUtil.getCurrentUser();
        Integer authorId = (Integer)user.get("id");
        para.put("authorId", authorId);
        para.put("status", "2");
        para.put("role",user.get("roleCode"));
        List<Map> list = titleExamineService.exportExcelForTeaccher(para);
        //添加sheet
        Map map = new HashMap();
        map.put("sheetName","sheet1");
        List<Map> newList = new ArrayList<Map>();
        newList.add(map);
        for(Map tmp : list) {
            newList.add(tmp);
        }
        String fileName="学生选题数据";
        //列名
        String columnNames[]={"学号","学生姓名","学生班级","设计题目",
                "指导老师姓名"};
        //map中的key
        String keys[] = {"stuNo","applyer","stuClass","titleName",
                "teacName"};
        try {
            dataExportService.stuSelDataExport(resp,fileName,newList,keys,columnNames);
        } catch (IOException e) {
            rb=ReturnBean.getErrorReturnBean();
            e.printStackTrace();
            return rb;
        }
        rb=ReturnBean.getSuccessReturnBean();
        return rb;
    }
}