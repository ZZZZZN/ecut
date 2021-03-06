package com.krt.admin.system.service;

import com.krt.admin.system.entity.User;
import com.krt.core.util.ExcelUtil;
import com.krt.ruanjian.course.service.MajorService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by WangXin on 2017/9/7.
 */
@Service
public class UserImportService {

    @Resource
    private UserService userService;

    @Resource
    private MajorService majorService;
    /**
     * 将读出的教师excel数据存到数据库
     * @param file
     * @return
     */
    public String insertTeacherExcelValue(MultipartFile file) {


        String result = "";
        ExcelUtil excelUtil = new ExcelUtil();
        List<User> userList = excelUtil.getTeacherExcelInfo(file);

            if(userList != null && !userList.isEmpty()){
                for (int i = 0; i < userList.size(); i++) {
                    userList.get(i).setRoleCode("teacr_in");
                    userList.get(i).setStatus("0");
                    if (userList.get(i).getMajor() != null) {
                        Map map = majorService.selectMajorCodeByMajorName(userList.get(i).getMajor());
                        userList.get(i).setMajor((String) map.get("major_code"));
                    }
                    try {
                        userService.insert(userList.get(i));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                result = "success";
            }else{
                result = "null";
            }
        return result;
    }

    /**
     * 将读出的student excel数据存到数据库
     * @param file
     * @return
     */
    public String insertStudentExcelValue(MultipartFile file) {


        String result = "";
        ExcelUtil excelUtil = new ExcelUtil();
        List<User> userList = excelUtil.getStudentExcelInfo(file);
        try {
            if(userList != null && !userList.isEmpty()){
                for (int i = 0; i < userList.size(); i++) {
                    userList.get(i).setStatus("0");
                    userList.get(i).setPassword(userList.get(i).getUsername());
                    if (userList.get(i).getTraining_site().contains("校内")) {
                        userList.get(i).setRoleCode("stu_in");
                    } else {
                        userList.get(i).setRoleCode("stu_out");
                    }
                    if (userList.get(i).getMajor() != null) {
                        Map map = majorService.selectMajorCodeByMajorName(userList.get(i).getMajor());
                        userList.get(i).setMajor((String) map.get("major_code"));
                    }
                    userService.insert(userList.get(i));
                }
                result = "上传成功";
            }else{
                result = "上传失败";
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "上传失败";
        }
        return result;
    }





}
