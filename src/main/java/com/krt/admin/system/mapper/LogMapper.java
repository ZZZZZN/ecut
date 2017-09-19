package com.krt.admin.system.mapper;

import com.krt.admin.system.entity.Log;
import com.krt.core.base.BaseMapper;

/**  
 * @Description: 日志管理映射层
 * @date 2016年7月21日
 * @version 1.0
 */
public interface LogMapper extends BaseMapper<Log>{

	void deleteAll() throws Exception;

}
