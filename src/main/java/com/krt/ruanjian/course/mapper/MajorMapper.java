package com.krt.ruanjian.course.mapper;

import com.krt.core.base.BaseMapper;
import com.krt.ruanjian.course.entity.Major;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @Description: 专业表映射层
 * @author pengYi
 * @date 2017年09月07日
 * @version 1.0
 */
public interface MajorMapper extends BaseMapper<Major>{
    Map selectMajorCodeByMajorName(@Param("major")  String major);

}
