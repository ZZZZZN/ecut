package com.krt.admin.system.mapper;

import com.krt.admin.system.entity.Role;
import com.krt.core.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**  
 * @Description: 角色管理映射层
 * @date 2016年7月16日
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
public interface RoleMapper extends BaseMapper<Role> {

	Integer checkRoleName(@Param("roleName") String roleName, @Param("id") Integer id);

	Integer checkRoleCode(@Param("roleCode") String roleCode, @Param("id") Integer id);

	void deleteRoleRes(@Param("roleCode") String roleCode)  throws Exception;

	void insertRoleRes(List list)  throws Exception;

	List selectRoleUrlRes(@Param("roleCode") String roleCode, @Param("pid") Integer pid);

}
