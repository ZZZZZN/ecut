package com.krt.admin.system.mapper;

import com.krt.admin.system.entity.User;
import com.krt.core.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**  
 * @Description: 用户管理映射层
 * @author 殷帅
 * @date 2016年7月16日
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
public interface UserMapper extends BaseMapper<User> {

	Integer checkUsername(@Param("username") String username, @Param("id") Integer id);

	Map selectByUsername(@Param("username") String username);
	
}