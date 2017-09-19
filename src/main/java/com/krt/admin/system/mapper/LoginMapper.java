package com.krt.admin.system.mapper;

import com.krt.admin.system.entity.User;
import com.krt.core.base.BaseMapper;

import java.util.Map;

/**  
 * @Description: 登录操作映射层
 * @date 2016年7月15日
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
public interface LoginMapper extends BaseMapper<User>{

	Map queryUser(Map para);

	void changePass(String username, String new_password);

}
