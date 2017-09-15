package com.krt.ruanjian.course.controller;

import com.krt.core.base.BaseController;
import com.krt.core.bean.DataTable;
import com.krt.core.bean.ReturnBean;
import com.krt.core.util.ExcelUtil;
import com.krt.core.util.ShiroUtil;
import com.krt.ruanjian.course.entity.TitleExamine;
import com.krt.ruanjian.course.service.DataExportService;
import com.krt.ruanjian.course.service.TitleExamineService;
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
        String columnNames[]={"学号","学生姓名","学生班级","设计题目",
                "课题类型","课题来源","指导老师姓名","职称"};
        //map中的key
        String keys[] = {"stuNo","stuName","stuClass","titleName",
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
                "指导老师姓名","教师所在系"};
        //map中的key
        String keys[] = {"stuNo","stuName","stuClass","majorName","note",
                 "teacName","department"};
        dataExportService.stuSelDataExport(resp, fileName,newList,keys,columnNames);
        return rb;
    }
}