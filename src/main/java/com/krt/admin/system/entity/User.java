package com.krt.admin.system.entity;

import com.krt.common.base.BaseEntity;

/**
 * @Description：T_user实体类 
 * @author：殷帅
 * @date：2016-08-30
 * @version 1.0
 */
@SuppressWarnings("serial")
public class User extends BaseEntity{

	private String username;
	private String password;
	private String name;
	private String status;
	private String roleCode;//角色id

	public void setUsername(String username){
		this.username=username;
	}
	public String getUsername(){
		return username;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public String getPassword(){
		return password;
	}
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setStatus(String status){
		this.status=status;
	}
	public String getStatus(){
		return status;
	}
	public void setRoleCode(String roleCode){
		this.roleCode=roleCode;
	}
	public String getRoleCode(){
		return roleCode;
	}
	
	@Override
	public String toString() {
		return "User [username=" + username +", name=" + name + ", status=" + status + ", roleCode="
				+ roleCode + "]";
	}
	
	
	
}
