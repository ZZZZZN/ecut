package com.krt.admin.system.mapper;

import com.krt.admin.system.entity.Dictionary;
import com.krt.core.base.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**  
 * @Description: 字典映射层
 * @date 2016年7月25日
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
public interface DictionaryMapper extends BaseMapper<Dictionary> {

	Integer checkCode(@Param("type") String type, @Param("code") String code, @Param("id") Integer id);

	void deleteChildDic(@Param("id") Integer id);

	List selectChildList(@Param("id") Integer id);

	void deleteByType(@Param("type") String type) throws Exception;

	List selectDicByPidAndType(@Param("pid") Integer pid, @Param("type") String type);
	
}