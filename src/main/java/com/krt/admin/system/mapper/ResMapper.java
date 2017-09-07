package com.krt.admin.system.mapper;

import com.krt.admin.system.entity.Res;
import com.krt.core.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**  
 * @Description: 资源管理映射层
 * @author 殷帅
 * @date 2016年7月19日
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
public interface ResMapper extends BaseMapper<Res> {

	List selectRootList();

	List selectAllTree();

	List<Map> selectRoleResList(@Param("roleCode") String roleCode);

	List selectChildResList(@Param("id") Integer id);
    
}