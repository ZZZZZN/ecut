package com.krt.admin.system.entity;


import com.krt.common.base.BaseEntity;

/**
 * @Description: 系统操作日志实体类
 * @author 殷帅
 * @date 2016年7月21日
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Log extends BaseEntity {

	private String username; //用户名
	private String params; //参数
	private String method; //方法
	private String requestIp; // 请求id
	private String description; //描述
	private String type; //日志类型  0:登录日志 1：操作日志
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
	public String getRequestIp() {
		return requestIp;
	}
	public void setRequestIp(String requestIp) {
		this.requestIp = requestIp;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
