package com.krt.ruanjian.course.entity;

import com.krt.core.base.BaseEntity;

/**
 * @Description: 专业表实体类
 * @author pengYi
 * @date 2017年09月07日
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Major extends BaseEntity {

	private String major_name;//专业名称
	private String major_code;//专业代码

	public void setMajor_name(String major_name){
		this.major_name = major_name;
	}

	public String getMajor_name(){
		return this.major_name;
	}
	public void setMajor_code(String major_code){
		this.major_code = major_code;
	}

	public String getMajor_code(){
		return this.major_code;
	}
}