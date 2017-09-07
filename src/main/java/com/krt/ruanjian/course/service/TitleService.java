package com.krt.ruanjian.course.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.krt.ruanjian.course.entity.Title;
import com.krt.ruanjian.course.mapper.TitleMapper;
import com.krt.core.base.BaseMapper;
import com.krt.core.base.BaseServiceImpl;

/**
 * @Description: 选题表服务层
 * @author pengYi
 * @date 2017年09月07日
 * @version 1.0
 */
@Service
public class TitleService extends BaseServiceImpl<Title>{

	@Resource
	private TitleMapper titleMapper;

	@Override
	public BaseMapper<Title> getMapper() {
		return titleMapper;
	}
}
