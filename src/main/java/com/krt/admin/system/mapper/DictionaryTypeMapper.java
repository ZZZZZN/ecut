package com.krt.admin.system.mapper;

import com.krt.admin.system.entity.DictionaryType;
import com.krt.core.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**  
 * @Description: 字典类型映射层
 * @date 2016年7月25日
 * @version 1.0
 */
public interface DictionaryTypeMapper extends BaseMapper<DictionaryType>{

	Integer checkCode(@Param("code") String code, @Param("id") Integer id);
   
}