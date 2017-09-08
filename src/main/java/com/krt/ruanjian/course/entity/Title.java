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

	private String title_name;//题名
	private String title_type;//课题类型
	private String title_source;//课题来源
	private Integer suitMajor;//适用专业
	private String suitScope;//适用实训所在地
	private String limit_person;//上线人数
	private String meaning_target;//课程意义与目标
	private String condition_work;//学生基本条件和前期工作
	private Integer author;//出题人
	private Integer dr;//逻辑删除
	private String ts;//时间戳

	public void setTitle_name(String title_name){
		this.title_name = title_name;
	}

	public String getTitle_name(){
		return this.title_name;
	}
	public void setTitle_type(String title_type){
		this.title_type = title_type;
	}

	public String getTitle_type(){
		return this.title_type;
	}
	public void setTitle_source(String title_source){
		this.title_source = title_source;
	}

	public String getTitle_source(){
		return this.title_source;
	}
	public void setSuitMajor(Integer suitMajor){
		this.suitMajor = suitMajor;
	}

	public Integer getSuitMajor(){
		return this.suitMajor;
	}
	public void setSuitScope(String suitScope){
		this.suitScope = suitScope;
	}

	public String getSuitScope(){
		return this.suitScope;
	}
	public void setLimit_person(String limit_person){
		this.limit_person = limit_person;
	}

	public String getLimit_person(){
		return this.limit_person;
	}
	public void setMeaning_target(String meaning_target){
		this.meaning_target = meaning_target;
	}

	public String getMeaning_target(){
		return this.meaning_target;
	}
	public void setCondition_work(String condition_work){
		this.condition_work = condition_work;
	}

	public String getCondition_work(){
		return this.condition_work;
	}
	public void setDr(Integer dr){
		this.dr = dr;
	}

	public Integer getAuthor() {
		return author;
	}

	public void setAuthor(Integer author) {
		this.author = author;
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