package com.krt.ruanjian.course.entity;

import com.krt.core.base.BaseEntity;

/**
 * @Description: 审核表（记录学生申请的题目）实体类
 * @author pengYi
 * @date 2017年09月08日
 * @version 1.0
 */
@SuppressWarnings("serial")
public class TitleExamine extends BaseEntity {

	private Integer title_examine_id;//主键
	private Integer title_id;//题目id
	private Integer applicant;//申请人
	private Integer auditor;//审核人
	private String status;//1 : 未审核  2 ： 审核通过  3 ： 审核未通过
	private String ts;//
	private Integer dr;//

	public void setTitle_examine_id(Integer title_examine_id){
		this.title_examine_id = title_examine_id;
	}

	public Integer getTitle_examine_id(){
		return this.title_examine_id;
	}
	public void setTitle_id(Integer title_id){
		this.title_id = title_id;
	}

	public Integer getTitle_id(){
		return this.title_id;
	}
	public void setApplicant(Integer applicant){
		this.applicant = applicant;
	}

	public Integer getApplicant(){
		return this.applicant;
	}
	public void setAuditor(Integer auditor){
		this.auditor = auditor;
	}

	public Integer getAuditor(){
		return this.auditor;
	}
	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return this.status;
	}
	public void setTs(String ts){
		this.ts = ts;
	}

	public String getTs(){
		return this.ts;
	}
	public void setDr(Integer dr){
		this.dr = dr;
	}

	public Integer getDr(){
		return this.dr;
	}
}