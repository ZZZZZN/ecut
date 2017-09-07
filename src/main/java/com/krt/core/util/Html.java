package com.krt.core.util;

import java.util.regex.Pattern;

/**  
*   
* 项目名称：zgqzhsq  
* 类名称：Html  
* 类描述：去掉html标签  
* 创建人：殷帅  
* 创建时间：2014-2-16 下午09:41:29    
*   
*/ 
public class Html {
	public static String getText(String htmlStr) {
		if(htmlStr==null || "".equals(htmlStr)) return "";
		      String textStr ="";    
		      Pattern pattern;
		      java.util.regex.Matcher matcher;    
		     
		     try {
		      String regEx_remark = "<!--.+?-->";
		      String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script> }    
		      String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style> }    
	          String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式    
	          String regEx_html1 = "<[^>]+";
	          htmlStr = htmlStr.replaceAll("\n","");
	          htmlStr = htmlStr.replaceAll("\t","");
	          htmlStr = htmlStr.replaceAll("\r","");
	          htmlStr = htmlStr.replaceAll("&nbsp;","");
	          pattern=Pattern.compile(regEx_remark);//过滤注释标签
	          matcher=pattern.matcher(htmlStr);
		      htmlStr=matcher.replaceAll("")    ;
		       
		      pattern = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);    
		      matcher = pattern.matcher(htmlStr);    
	          htmlStr = matcher.replaceAll(""); //过滤script标签    

	          pattern = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);    
	          matcher = pattern.matcher(htmlStr);    
	          htmlStr = matcher.replaceAll(""); //过滤style标签    
	        
	          pattern = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);    
	          matcher = pattern.matcher(htmlStr);    
	          htmlStr = matcher.replaceAll(""); //过滤html标签    
	            
	          pattern = Pattern.compile(regEx_html1,Pattern.CASE_INSENSITIVE);    
	          matcher = pattern.matcher(htmlStr);    
	          htmlStr = matcher.replaceAll(""); //过滤html标签    
		                
		       textStr = htmlStr.trim();    
		       
		   }catch(Exception e) {
		   //System.out.println("获取HTML中的text出错:");
		   e.printStackTrace();
		    }    
		    
		  return textStr;//返回文本字符串
		} 
}
