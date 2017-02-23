package com.krt.admin.system.service;

import com.krt.admin.system.entity.User;
import com.krt.admin.system.mapper.LoginMapper;
import com.krt.common.base.BaseMapper;
import com.krt.common.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 类名称：LoginService
 * 类描述：系统登录服务层
 * 创建人：殷帅
 * 创建时间：2016年3月6日
 */
@SuppressWarnings("rawtypes")
@Service
public class LoginService extends BaseServiceImpl<User> {

    @Resource
    private LoginMapper loginMapper;

    @Override
    public BaseMapper<User> getMapper() {
        return loginMapper;
    }

    public Map queryUser(Map para) {
        return loginMapper.queryUser(para);
    }

    public void changePass(String username, String new_password) {
        loginMapper.changePass(username, new_password);
    }
}
