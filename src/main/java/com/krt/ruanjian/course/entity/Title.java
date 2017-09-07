package com.krt.ruanjian.course.entity;

import com.krt.core.base.BaseEntity;

/**
 * @Description: 选题表实体类
 * @author pengYi
 * @date 2017年09月07日
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Title extends BaseEntity {

	private Integer title_id;//主键
	private String title_name;//题名
	private String start_time;//选题开始时间
	private String end_time;//选题结束时间
	private String title_limit;//选题上限数量
	private String author;//出题人
	private String title_type;//1 : 校内选题  2 ： 校外选题
	private Integer dr;//逻辑删除
	private String ts;//时间戳

	public void setTitle_id(Integer title_id){
		this.title_id = title_id;
	}

	public Integer getTitle_id(){
		return this.title_id;
	}
	public void setTitle_name(String title_name){
		this.title_name = title_name;
	}

	public String getTitle_name(){
		return this.title_name;
	}
	public void setStart_time(String start_time){
		this.start_time = start_time;
	}

	public String getStart_time(){
		return this.start_time;
	}
	public void setEnd_time(String end_time){
		this.end_time = end_time;
	}

	public String getEnd_time(){
		return this.end_time;
	}
	public void setTitle_limit(String title_limit){
		this.title_limit = title_limit;
	}

	public String getTitle_limit(){
		return this.title_limit;
	}
	public void setAuthor(String author){
		this.author = author;
	}

	public String getAuthor(){
		return this.author;
	}
	public void setTitle_type(String title_type){
		this.title_type = title_type;
	}

	public String getTitle_type(){
		return this.title_type;
	}
	public void setDr(Integer dr){
		this.dr = dr;
	}

	public Integer getDr(){
		return this.dr;
	}
	public void setTs(String ts){
		this.ts = ts;
	}

	public String getTs(){
		return this.ts;
	}
}