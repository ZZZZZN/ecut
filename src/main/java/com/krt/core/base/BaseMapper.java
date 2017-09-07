package com.krt.core.base;

import java.util.List;
import java.util.Map;

/**
 * @Description: 公共抽取的映射层
 * @author 殷帅
 * @date 2016年7月16日
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
public interface BaseMapper<T> extends BaseService<T> {

	/**
	 * 查询列表
	 * 
	 * @return
	 */
	public List<Map> selectList();

	/**
	 * 带参数查询
	 * 
	 * @param para
	 * @return
	 */
	public List<Map> selectListPara(Map para);
}
