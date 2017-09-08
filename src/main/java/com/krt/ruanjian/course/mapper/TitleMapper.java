package com.krt.ruanjian.course.mapper;

import com.krt.core.base.BaseMapper;
import com.krt.ruanjian.course.entity.Title;

import java.util.List;
import java.util.Map;

/**
 * @Description: 选题表映射层
 * @author pengYi
 * @date 2017年09月07日
 * @version 1.0
 */
public interface TitleMapper extends BaseMapper<Title>{

    List<Map> selectListStudent (Map para);

}
