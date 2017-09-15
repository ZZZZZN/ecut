package com.krt.ruanjian.course.controller;

import com.krt.core.base.BaseController;
import com.krt.core.bean.DataTable;
import com.krt.core.bean.ReturnBean;
import com.krt.core.util.ExcelUtil;
import com.krt.core.util.ShiroUtil;
import com.krt.ruanjian.course.entity.TitleExamine;
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
    private DataExportService dataExportService;
    @Autowired
    private TitleService titleService;


    //返回学生选题数据页面
    @RequestMapping("ruanjian/course/boss/stuSelDataExportUI")
    public String stuSelDataExportUI() {
        return "ruanjian/course/boss/stuSelDataExportUI";
    }

    //渲染学生选题数据页面
    @RequestMapping("ruanjian/course/boss/stuSelDataExport")
    @ResponseBody
    public DataTable stuSelDataExport(Integer start, Integer length, Integer draw,
                                      HttpServletRequest request) {
        //获取学生选题数据
        Map para = new HashMap();
        DataTable dt = titleExamineService.getStuSelData(start, length, draw, para);
        return dt;
    }

    //导出学生选题数据 excel文件
    @RequestMapping("ruanjian/course/boss/startExport")
    @ResponseBody
    public ReturnBean download(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
        String fileName="excel文件";
        //列名
        String columnNames[]={"学号","学生姓名","学生班级","设计题目",
                "课题类型","课题来源","指导老师姓名","职称"};
        //map中的key
        String keys[] = {"stuNo","stuName","stuClass","titleName",
                "titleType","titleSource","teacName","titleLevel"};
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ExcelUtil.createWorkBook(newList,keys,columnNames).write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
        ServletOutputStream out = response.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (final IOException e) {
            throw e;
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
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
                "上限人数","出题老师","课程意义与目标","学生基本条件和前期工作"};
        //map中的key
        String keys[] = {"title_name","title_type","suitMajorName","suitScope",
                "limit_person","author","meaning_target","condition_work"};
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

}