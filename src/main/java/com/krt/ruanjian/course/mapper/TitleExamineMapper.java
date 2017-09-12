package com.krt.ruanjian.course.mapper;

import com.krt.core.base.BaseMapper;
import com.krt.ruanjian.course.entity.TitleExamine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 审核表（记录学生申请的题目）映射层
 * @author pengYi
 * @date 2017年09月08日
 * @version 1.0
 */
public interface TitleExamineMapper extends BaseMapper<TitleExamine>{

    /**
     * 修改审核状态
     */
    int updateStatusById(Map param);

    List<Map> selectByApplicant(Map param);

    List<Map> getTitleByMajor(Map para);

    List<Map> getTitlePassAndFail(Map para);
}
