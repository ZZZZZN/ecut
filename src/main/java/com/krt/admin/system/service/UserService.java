package com.krt.admin.system.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.krt.admin.system.entity.User;
import com.krt.admin.system.mapper.UserMapper;
import com.krt.core.base.BaseMapper;
import com.krt.core.base.BaseServiceImpl;
import com.krt.core.bean.DataTable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author 殷帅
 * @version 1.0
 * @Description: 用户管理服务层
 * @date 2016年7月16日
 */
@SuppressWarnings("rawtypes")
@Service
public class UserService extends BaseServiceImpl<User> {

    @Resource
    private UserMapper userMapper;

    @Override
    public BaseMapper<User> getMapper() {
        return userMapper;
    }

    /**
     * 检测用户名
     *
     * @param username 用户名
     * @param id       用户id
     * @return
     */
    public Integer checkUsername(String username, Integer id) {
        return userMapper.checkUsername(username, id);
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param username
     * @return
     */
    public Map selectByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    /**
     * 根据学院和专业查询学生名单
     * @param start
     * @param length
     * @param draw
     * @param institute
     * @param major_code
     * @return
     */
    public DataTable selectStudentsByInstituteAndMajor(Integer start, Integer length, Integer draw,
                                                       String institute,String major_code) {
        DataTable dataTable = new DataTable();
        // 下面两句要连着写在一起，就可以实现分页
        dataTable.setLength(length);
        dataTable.setPageNum(start);
        PageHelper.startPage(dataTable.getPageNum(), dataTable.getLength());
//        List<Map> list = titleMapper.selectListStudent(para);
        List<Map> list = userMapper.selectStudentsByInstituteAndMajor(institute,major_code);
        // 下面这句是为了获取分页信息，比如记录总数等等
        PageInfo<Map> pageInfo = new PageInfo<Map>(list);
        dataTable.setData(list);
        dataTable.setRecordsTotal(Long.valueOf(dataTable.getLength()));
        dataTable.setRecordsFiltered(pageInfo.getTotal());
        return dataTable;
    }

    /**
     * 根据学院和专业查询教师名单
     * @param start
     * @param length
     * @param draw
     * @param institute
     * @param major_code
     * @return
     */
    public DataTable selectTeachersByInstituteAndMajor(Integer start, Integer length, Integer draw,
                                                       String institute,String major_code) {
        DataTable dataTable = new DataTable();
        // 下面两句要连着写在一起，就可以实现分页
        dataTable.setLength(length);
        dataTable.setPageNum(start);
        PageHelper.startPage(dataTable.getPageNum(), dataTable.getLength());
//        List<Map> list = titleMapper.selectListStudent(para);
        List<Map> list = userMapper.selectTeachersByInstituteAndMajor(institute,major_code);
        // 下面这句是为了获取分页信息，比如记录总数等等
        PageInfo<Map> pageInfo = new PageInfo<Map>(list);
        dataTable.setData(list);
        dataTable.setRecordsTotal(Long.valueOf(dataTable.getLength()));
        dataTable.setRecordsFiltered(pageInfo.getTotal());
        return dataTable;
    }

    public Integer selectTeacherLevelnumByid(Integer id){
        return userMapper.selectTeacherLevelnumByid(id);
    }

    public Map selectById2(Integer id) {
        return userMapper.selectById2(id);
    }

}
