package com.krt.admin.system.service;

import com.krt.admin.system.entity.User;
import com.krt.admin.system.mapper.UserMapper;
import com.krt.common.base.BaseMapper;
import com.krt.common.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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


}
