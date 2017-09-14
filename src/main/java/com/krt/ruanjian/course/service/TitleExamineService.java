package com.krt.ruanjian.course.service;

import javax.annotation.Resource;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.krt.core.bean.DataTable;
import org.springframework.stereotype.Service;
import com.krt.ruanjian.course.entity.TitleExamine;
import com.krt.ruanjian.course.mapper.TitleExamineMapper;
import com.krt.core.base.BaseMapper;
import com.krt.core.base.BaseServiceImpl;

import java.util.List;
import java.util.Map;

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

	/**
	 * 修改审核状态
	 */
    public int updateStatusById(Map param) {
		return titleExamineMapper.updateStatusById(param);
    }


	public DataTable selectByApplicant(Integer start, Integer length, Integer draw, Map para) {
		DataTable dataTable = new DataTable();
		// 下面两句要连着写在一起，就可以实现分页
		dataTable.setLength(length);
		dataTable.setPageNum(start);
		PageHelper.startPage(dataTable.getPageNum(), dataTable.getLength());
		List<Map> list = titleExamineMapper.selectByApplicant(para);
		// 下面这句是为了获取分页信息，比如记录总数等等
		PageInfo<Map> pageInfo = new PageInfo<Map>(list);
		dataTable.setData(list);
		dataTable.setRecordsTotal(Long.valueOf(dataTable.getLength()));
		dataTable.setRecordsFiltered(pageInfo.getTotal());
		return dataTable;
	}

	/**
	 * 系主任获取所有题目（审核教师所在系的教师上传的题目）
	 */
	public DataTable getTitleByMajor(Integer start, Integer length, Integer draw, Map para) {
		String flag = (String)para.get("flag");
		DataTable dataTable = new DataTable();
		// 下面两句要连着写在一起，就可以实现分页
		dataTable.setLength(length);
		dataTable.setPageNum(start);
		PageHelper.startPage(dataTable.getPageNum(), dataTable.getLength());
		List<Map> list;
		list = titleExamineMapper.getTitleByMajor(para);
		// 下面这句是为了获取分页信息，比如记录总数等等
		PageInfo<Map> pageInfo = new PageInfo<Map>(list);
		dataTable.setData(list);
		dataTable.setRecordsTotal(Long.valueOf(dataTable.getLength()));
		dataTable.setRecordsFiltered(pageInfo.getTotal());
		return dataTable;
	}

    public int checkStuSelTitles(Map param) {
		List<TitleExamine> tmp = titleExamineMapper.checkStuSelTitles(param);
		return tmp.size();
    }
}
