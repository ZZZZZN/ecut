package com.krt.core.base;

import java.util.Map;

/**
 * 
 * @Description: 抽取公共服务层接口
 * @date 2016年7月16日
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
public interface BaseService<T> {
	/**
	 * 插入
	 * 
	 * @param entity
	 * @return
	 */
	public void insert(T entity) throws Exception;

	/**
	 * 根据id删除
	 * 
	 * @param id
	 */
	public void delete(Integer id) throws Exception;

	/**
	 * 更新
	 * 
	 * @param entity
	 * @throws Exception 
	 */
	public void update(T entity) throws Exception;

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	public Map selectById(Integer id);
}
