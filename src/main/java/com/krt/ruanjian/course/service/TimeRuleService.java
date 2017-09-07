package com.krt.ruanjian.course.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.krt.ruanjian.course.entity.TimeRule;
import com.krt.ruanjian.course.mapper.TimeRuleMapper;
import com.krt.core.base.BaseMapper;
import com.krt.core.base.BaseServiceImpl;

/**
 * @Description: time_rule服务层
 * @author pengYi
 * @date 2017年09月07日
 * @version 1.0
 */
@Service
public class TimeRuleService extends BaseServiceImpl<TimeRule>{

	@Resource
	private TimeRuleMapper timeRuleMapper;

	@Override
	public BaseMapper<TimeRule> getMapper() {
		return timeRuleMapper;
	}
}
