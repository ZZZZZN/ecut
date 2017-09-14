package com.krt.admin.system.service;

import com.krt.admin.system.entity.User;
import com.krt.core.util.ExcelUtil;
import com.krt.ruanjian.course.service.MajorService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WangXin on 2017/9/7.
 */
@Service
public class UserExportService {

    @Resource
    private UserService userService;

    @Resource
    private MajorService majorService;

    /**
     * 创建学生Excel记录
     * @param users
     * @return
     */
    public List<Map> createStuExcelRecord(List<User> users) {
        List<Map> listmap = new ArrayList<Map>();
        Map map = new HashMap<String, Object>();
        map.put("sheetName", "sheet1");
        listmap.add(map);
//        Project project=null;
        User user = null;
        for (int j = 0; j < users.size(); j++) {
            user=users.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();
//            mapValue.put("id", user.getId());
            mapValue.put("username", user.getUsername());
            mapValue.put("name", user.getName());
            mapValue.put("stu_class", user.getStu_class());
            mapValue.put("institute", user.getInstitute());
            mapValue.put("major", user.getMajor());
            mapValue.put("training_site", user.getTraining_site());
            mapValue.put("company", user.getCompany());
            mapValue.put("note", user.getNote());
            listmap.add(mapValue);
        }
        return listmap;
    }

    /**
     * 创建教师Excel记录
     * @param users
     * @return
     */
    public List<Map> createTeacExcelRecord(List<User> users) {
        List<Map> listmap = new ArrayList<Map>();
        Map map = new HashMap<String, Object>();
        map.put("sheetName", "sheet1");
        listmap.add(map);
//        Project project=null;
        User user = null;
        for (int j = 0; j < users.size(); j++) {
            user=users.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();
//            mapValue.put("id", user.getId());
            mapValue.put("name", user.getName());
            mapValue.put("username", user.getUsername());
            mapValue.put("institute", user.getInstitute());
//            mapValue.put("major", user.getMajor());
            mapValue.put("title_level", user.getTitle_level());
            mapValue.put("department", user.getDepartment());
            mapValue.put("note", user.getNote());
            listmap.add(mapValue);
        }
        return listmap;
    }

}
