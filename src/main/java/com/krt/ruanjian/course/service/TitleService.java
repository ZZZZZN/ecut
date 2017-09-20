package com.krt.ruanjian.course.service;

import javax.annotation.Resource;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.krt.core.bean.DataTable;
import org.springframework.stereotype.Service;
import com.krt.ruanjian.course.entity.Title;
import com.krt.ruanjian.course.mapper.TitleMapper;
import com.krt.core.base.BaseMapper;
import com.krt.core.base.BaseServiceImpl;

import java.util.List;
import java.util.Map;

/**
 * @Description: 选题表服务层
 * @author pengYi
 * @date 2017年09月07日
 * @version 1.0
 */
@Service
public class TitleService extends BaseServiceImpl<Title> {

	@Resource
	private TitleMapper titleMapper;

	@Override
	public BaseMapper<Title> getMapper() {
		return titleMapper;
	}

	public DataTable selectListStudent(Integer start, Integer length, Integer draw, Map para) {
		DataTable dataTable = new DataTable();
		// 下面两句要连着写在一起，就可以实现分页
		dataTable.setLength(length);
		dataTable.setPageNum(start);
		PageHelper.startPage(dataTable.getPageNum(), dataTable.getLength());
		List<Map> list = titleMapper.selectListStudent(para);
		// 下面这句是为了获取分页信息，比如记录总数等等
		PageInfo<Map> pageInfo = new PageInfo<Map>(list);
		dataTable.setData(list);
		dataTable.setRecordsTotal(Long.valueOf(dataTable.getLength()));
		dataTable.setRecordsFiltered(pageInfo.getTotal());
		return dataTable;
	}

	public Integer countPassNumber(String id){
		return titleMapper.countPassNumber(id);
	}

	public DataTable teacherExport(Integer start, Integer length, Integer draw, Map para) {
		DataTable dataTable = new DataTable();
		// 下面两句要连着写在一起，就可以实现分页
		dataTable.setLength(length);
		dataTable.setPageNum(start);
		PageHelper.startPage(dataTable.getPageNum(), dataTable.getLength());
		List<Map> list = titleMapper.teacherExport(para);
		// 下面这句是为了获取分页信息，比如记录总数等等
		PageInfo<Map> pageInfo = new PageInfo<Map>(list);
		dataTable.setData(list);
		dataTable.setRecordsTotal(Long.valueOf(dataTable.getLength()));
		dataTable.setRecordsFiltered(pageInfo.getTotal());
		return dataTable;
	}

    public void updateBatch(String[] array) {
		int result = titleMapper.updateBatch(array);
		//System.out.println(result);
	}
}