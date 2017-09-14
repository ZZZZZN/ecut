package com.krt.admin.system.controller;

import com.krt.admin.system.entity.User;
import com.krt.admin.system.mapper.UserMapper;
import com.krt.admin.system.service.UserExportService;
import com.krt.admin.system.util.BeanToMapUtil;
import com.krt.core.base.BaseController;
import com.krt.core.bean.DataTable;
import com.krt.core.util.ExcelUtil;
import com.krt.ruanjian.course.service.MajorService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WangXin on 2017/9/13.
 */
@Controller
public class UserExportController extends BaseController{

    @Resource
    MajorService majorService;

    @Resource
    UserExportService userExportService;

    @Resource
    UserMapper userMapper;

    /**
     * 软件学院学生Excel导出
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("user:list")
    @RequestMapping("formExport/students")
    @ResponseBody
    public String download(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String major = null;
        if (request.getParameter("major_name1") != null && !"".equals(request.getParameter("major_name1"))) {
            major = new String(request.getParameter("major_name1").getBytes("iso-8859-1"), "utf-8");
        }

        String institute = "软件学院";
        String major_code = "";
        if (major != null && !"".equals(major)) {
            major_code = majorService.selectMajorCodeByMajorName(major).get("major_code").toString();
        } else {
            major_code = "080902";
        }

        List<Map> userList = userMapper.selectStudentsByInstituteAndMajor(institute, major_code);


        String fileName="excel文件";
        //填充projects数据
        List<User> users=BeanToMapUtil.convertListMap2ListBean(userList, User.class);
        List<Map> list1=userExportService.createStuExcelRecord(users);
        String columnNames[]={"姓名","学号","班级","学院","专业","实训地点","所在企业","备注"};//列名
        String keys[]   =    {"name","username","stu_class","institute","major","training_site","company","note"};//map中的key
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ExcelUtil.createWorkBook(list1,keys,columnNames).write(os);
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
        return null;
    }

    /**
     * 软件学院教师Excel导出
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("user:list")
    @RequestMapping("formExport/teachers")
    @ResponseBody
    public String download_teacher(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String major = null;
        if (request.getParameter("major_name1") != null && !"".equals(request.getParameter("major_name1"))) {
            major = new String(request.getParameter("major_name1").getBytes("iso-8859-1"), "utf-8");
        }

        String institute = "软件学院";
        String major_code = "";
        if (major != null && !"".equals(major)) {
            major_code = majorService.selectMajorCodeByMajorName(major).get("major_code").toString();
        } else {
            major_code = "080902";
        }

        List<Map> userList = userMapper.selectTeachersByInstituteAndMajor(institute, major_code);


        String fileName="excel文件";
        //填充projects数据
        List<User> users=BeanToMapUtil.convertListMap2ListBean(userList, User.class);
        List<Map> list1=userExportService.createTeacExcelRecord(users);
        String columnNames[]={"姓名","工号","学院","职称","所在系","备注"};//列名
        String keys[]   =    {"name","username","institute","title_level","department","note"};//map中的key
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ExcelUtil.createWorkBook(list1,keys,columnNames).write(os);
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
        return null;
    }


    /**
     * 信工学院学生Excel导出
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("user:list")
    @RequestMapping("formExport/students_xg")
    @ResponseBody
    public String download_xg(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String major = null;
        if (request.getParameter("major_name1") != null && !"".equals(request.getParameter("major_name1"))) {
            major = new String(request.getParameter("major_name1").getBytes("iso-8859-1"), "utf-8");
        }

        String institute = "信工学院";
        String major_code = "";
        if (major != null && !"".equals(major)) {
            major_code = majorService.selectMajorCodeByMajorName(major).get("major_code").toString();
        } else {
            major_code = "080703";
        }

        List<Map> userList = userMapper.selectStudentsByInstituteAndMajor(institute, major_code);


        String fileName="excel文件";
        //填充projects数据
        List<User> users=BeanToMapUtil.convertListMap2ListBean(userList, User.class);
        List<Map> list1=userExportService.createStuExcelRecord(users);
        String columnNames[]={"姓名","学号","班级","学院","专业","实训地点","所在企业","备注"};//列名
        String keys[]   =    {"name","username","stu_class","institute","major","training_site","company","note"};//map中的key
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ExcelUtil.createWorkBook(list1,keys,columnNames).write(os);
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
        return null;
    }

    /**
     * 信工学院教师Excel导出
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresPermissions("user:list")
    @RequestMapping("formExport/teachers_xg")
    @ResponseBody
    public String download_teacher_xg(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String major = null;
        if (request.getParameter("major_name1") != null && !"".equals(request.getParameter("major_name1"))) {
            major = new String(request.getParameter("major_name1").getBytes("iso-8859-1"), "utf-8");
        }

        String institute = "信工学院";
        String major_code = "";
        if (major != null && !"".equals(major)) {
            major_code = majorService.selectMajorCodeByMajorName(major).get("major_code").toString();
        } else {
            major_code = "080703";
        }

        List<Map> userList = userMapper.selectTeachersByInstituteAndMajor(institute, major_code);


        String fileName="excel文件";
        //填充projects数据
        List<User> users=BeanToMapUtil.convertListMap2ListBean(userList, User.class);
        List<Map> list1=userExportService.createTeacExcelRecord(users);
        String columnNames[]={"姓名","工号","学院","职称","所在系","备注"};//列名
        String keys[]   =    {"name","username","institute","title_level","department","note"};//map中的key
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ExcelUtil.createWorkBook(list1,keys,columnNames).write(os);
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
        return null;
    }



}
