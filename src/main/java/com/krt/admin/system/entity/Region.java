package com.krt.admin.system.entity;


import com.krt.common.base.BaseEntity;

/**
 * @Description：Region实体类 
 * @author：殷帅
 * @date：2016-07-26
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Region extends BaseEntity {

	private String code;//地区编码
	private String name;//地区名称
	private String type;//区域类型
	private Integer pid;//父id

	public void setCode(String code){
		this.code=code;
	}
	public String getCode(){
		return code;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setType(String type){
		this.type=type;
	}
	public String getType(){
		return type;
	}
	public void setPid(Integer pid){
		this.pid=pid;
	}
	public Integer getPid(){
		return pid;
	}
}
