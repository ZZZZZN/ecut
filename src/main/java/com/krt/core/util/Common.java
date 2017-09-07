package com.krt.core.util;

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;

/**
 * 
 * @Description: 公用的工具类
 * @author 殷帅
 * @date 2016年7月20日
 * @version 1.0
 */
public class Common {

	private static final Logger logger = Logger.getLogger(Common.class);

	/*
	 * null则返回空字符串
	 */
	public static String isBlank(String parameter) {
		return parameter == null ? "" : parameter.toString();
	}

	/**
	 * 转码
	 * 
	 * @param str
	 * @return
	 */
	public static String toUTF(String str) {
		if ("".equals(str) || str == null) {
			return "";
		} else {
			try {
				str = new String(str.trim().getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error("转码失败", e);
			}
			return str;
		}
	}

}
