package com.krt.ruanjian.course.entity;

import com.krt.core.base.BaseEntity;

/**
 * @Description: time_rule实体类
 * @author pengYi
 * @date 2017年09月07日
 * @version 1.0
 */
@SuppressWarnings("serial")
public class TimeRule extends BaseEntity {

	private String teacher_time_begin;//教师出题开始时间
	private String teacher_time_end;//教师出题结束时间
	private String student_time_begin;//学生选题开始时间
	private String student_time_end;//学生选题结束时间
	private String task_time_begin;//毕业设计任务书下达开始时间
	private String task_time_end;//毕业设计任务书下达结束时间
	private String early_stage_begin;//过程前期开始时间
	private String early_stage_end;//过程前期开始时间
	private String mid_stage_begin;//过程中期开始时间
	private String mid_stage_end;//过程中期结束时间
	private String later_stage_begin;//过程后期开始时间
	private String later_stage_end;//过程后期结束时间
	private String defence_time_begin;//答辩开始时间
	private String defence_time_end;//答辩结束时间

	public void setTeacher_time_begin(String teacher_time_begin){
		this.teacher_time_begin = teacher_time_begin;
	}

	public String getTeacher_time_begin(){
		return this.teacher_time_begin;
	}
	public void setTeacher_time_end(String teacher_time_end){
		this.teacher_time_end = teacher_time_end;
	}

	public String getTeacher_time_end(){
		return this.teacher_time_end;
	}
	public void setStudent_time_begin(String student_time_begin){
		this.student_time_begin = student_time_begin;
	}

	public String getStudent_time_begin(){
		return this.student_time_begin;
	}
	public void setStudent_time_end(String student_time_end){
		this.student_time_end = student_time_end;
	}

	public String getStudent_time_end(){
		return this.student_time_end;
	}
	public void setTask_time_begin(String task_time_begin){
		this.task_time_begin = task_time_begin;
	}

	public String getTask_time_begin(){
		return this.task_time_begin;
	}
	public void setTask_time_end(String task_time_end){
		this.task_time_end = task_time_end;
	}

	public String getTask_time_end(){
		return this.task_time_end;
	}
	public void setEarly_stage_begin(String early_stage_begin){
		this.early_stage_begin = early_stage_begin;
	}

	public String getEarly_stage_begin(){
		return this.early_stage_begin;
	}
	public void setEarly_stage_end(String early_stage_end){
		this.early_stage_end = early_stage_end;
	}

	public String getEarly_stage_end(){
		return this.early_stage_end;
	}
	public void setMid_stage_begin(String mid_stage_begin){
		this.mid_stage_begin = mid_stage_begin;
	}

	public String getMid_stage_begin(){
		return this.mid_stage_begin;
	}
	public void setMid_stage_end(String mid_stage_end){
		this.mid_stage_end = mid_stage_end;
	}

	public String getMid_stage_end(){
		return this.mid_stage_end;
	}
	public void setLater_stage_begin(String later_stage_begin){
		this.later_stage_begin = later_stage_begin;
	}

	public String getLater_stage_begin(){
		return this.later_stage_begin;
	}
	public void setLater_stage_end(String later_stage_end){
		this.later_stage_end = later_stage_end;
	}

	public String getLater_stage_end(){
		return this.later_stage_end;
	}
	public void setDefence_time_begin(String defence_time_begin){
		this.defence_time_begin = defence_time_begin;
	}

	public String getDefence_time_begin(){
		return this.defence_time_begin;
	}
	public void setDefence_time_end(String defence_time_end){
		this.defence_time_end = defence_time_end;
	}

	public String getDefence_time_end(){
		return this.defence_time_end;
	}
}