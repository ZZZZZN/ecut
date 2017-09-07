package com.krt.ruanjian.course.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.krt.ruanjian.course.entity.Major;
import com.krt.ruanjian.course.mapper.MajorMapper;
import com.krt.core.base.BaseMapper;
import com.krt.core.base.BaseServiceImpl;

/**
 * @Description: 专业表服务层
 * @author pengYi
 * @date 2017年09月07日
 * @version 1.0
 */
@Service
public class MajorService extends BaseServiceImpl<Major>{

	@Resource
	private MajorMapper majorMapper;

	@Override
	public BaseMapper<Major> getMapper() {
		return majorMapper;
	}
}
