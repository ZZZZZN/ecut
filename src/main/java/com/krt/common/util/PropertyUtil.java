package com.krt.common.util;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**  
*   
* 项目名称：krtUserCenter  
* 类名称：PropertiesUtil  
* 类描述： property文件读取工具类 
* 创建人：殷帅  
* 创建时间：2015-11-24 上午09:32:06  
*   
*/ 
public class PropertyUtil {
	
	private static Logger logger = Logger.getLogger(PropertyUtil.class);
	
	//定义属性文件
	private  Properties p = null;
	
	public PropertyUtil(String FileName){
		p = new Properties(); 
		try {
			logger.debug("开始读取"+FileName);
			p.load(new InputStreamReader(PropertyUtil.class.getClassLoader().getResourceAsStream(FileName), "UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("读取"+FileName+"异常");
		}
	}
	
	// 根据key读取value
	public  String getValue(String key) { 	 
		return p.getProperty(key);
	}
	
	public static void main(String[] args) {
		PropertyUtil propertyUtil = new PropertyUtil("message.properties");
		System.out.println(propertyUtil.getValue("card_pwd"));
	}
}
