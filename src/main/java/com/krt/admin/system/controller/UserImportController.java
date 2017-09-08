package com.krt.admin.system.controller;

import com.krt.admin.system.service.UserImportService;
import com.krt.core.base.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**@Description 用户导入
 * Created by WangXin on 2017/9/7.
 */
@Controller
public class UserImportController extends BaseController{

    @Resource
    private UserImportService userImportService;

    @RequiresPermissions("user:import")
    @RequestMapping("ExcelImport/Teacher")
    public String teacherExcel(HttpServletRequest request, HttpServletResponse response) {
        return "ruanjian/userImport/teacherImport";
    }

    @RequiresPermissions("user:import")
    @RequestMapping("ExcelImport/Student")
    public String studentExcel(HttpServletRequest request, HttpServletResponse response) {
        return "ruanjian/userImport/studentImport";
    }

    @RequiresPermissions("user:import")
    @RequestMapping("ExcelImport/TeacherImport")
    @ResponseBody
    public String teacherExcelImport(@RequestParam(value="file",required = false)MultipartFile file,
                                     HttpServletRequest request, HttpServletResponse response) {
        String result = userImportService.insertTeacherExcelValue(file);
        return result;
    }

    @RequiresPermissions("user:import")
    @RequestMapping("ExcelImport/StudentImport")
    @ResponseBody
    public String studentExcelImport(@RequestParam(value="file",required = false)MultipartFile file,
                                     HttpServletRequest request, HttpServletResponse response) {
        String result = userImportService.insertStudentExcelValue(file);
        return result;
    }
}
