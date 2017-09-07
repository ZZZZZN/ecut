package com.krt.admin.system.mapper;

import com.krt.admin.system.entity.QuartzJob;
import com.krt.core.base.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * @Description: 任务调度映射层
 * @author 殷帅
 * @date 2016年7月22日
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
public interface QuartzJobMapper extends BaseMapper<QuartzJob> {

	QuartzJob selectEntityById(Integer id);

	Integer checkJobName(Map para);

	List<QuartzJob> selectEntityList();
}