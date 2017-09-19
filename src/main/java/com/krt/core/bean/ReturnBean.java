package com.krt.core.bean;

import java.io.Serializable;

/**   
*     
* 类名称：ReturnBean   
* 类描述：返回实体  
* 创建时间：2016-1-13 下午03:37:18
*    
*/ 
@SuppressWarnings("serial")
public class ReturnBean implements Serializable{
	
	public static final String SUCCESS = "success";
	public static final String ERROR = "error";
	public String state="";
	
	public ReturnBean(String state) {
		this.state = state;
	}
	
	/*
	 * 成功
	 */
	public static ReturnBean getSuccessReturnBean(){
		return new ReturnBean(SUCCESS);
	}
	
	/*
	 * 失败
	 */
	public static ReturnBean getErrorReturnBean(){
		return new ReturnBean(ERROR);
	}
	
	/*
	 *  自定义
	 */
	public static ReturnBean getCustomReturnBean(String state){
		return new ReturnBean(state);
	}
	
}
