package com.krt.ruanjian.course.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.krt.ruanjian.course.entity.TitleExamine;
import com.krt.ruanjian.course.mapper.TitleExamineMapper;
import com.krt.core.base.BaseMapper;
import com.krt.core.base.BaseServiceImpl;

/**
 * @Description: 审核表（记录学生申请的题目）服务层
 * @author pengYi
 * @date 2017年09月08日
 * @version 1.0
 */
@Service
public class TitleExamineService extends BaseServiceImpl<TitleExamine>{

	@Resource
	private TitleExamineMapper titleExamineMapper;

	@Override
	public BaseMapper<TitleExamine> getMapper() {
		return titleExamineMapper;
	}
}
