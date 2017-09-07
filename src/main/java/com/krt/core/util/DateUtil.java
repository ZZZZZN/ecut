package com.krt.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**  
*   
* 项目名称：java  
* 类名称：DateUtil  
* 类描述： 日期工具类 
* 创建人：殷帅  
* 创建时间：2014-1-14 上午09:35:40  
* 修改人：殷帅
* 修改时间：2014-1-14 上午09:35:40  
* 修改备注：  
* @version   
*   
*/ 
public class DateUtil {
	
	private static final long ONE_MINUTE = 60;
	private static final long ONE_HOUR = 3600;
	private static final long ONE_DAY = 86400;
	private static final long ONE_WEEK = 604800;
	private static final long ONE_MONTH = 2592000;
	private static final long ONE_QUARTER = 7776000;
	private static final long ONE_YEAR = 31104000;
	
	/**
	 * 获取当前年份
	 * @return
	 */
	public static String getYear() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR) + "";
	}
	/**
	 * 获取但前月份
	 * @return
	 */
	public static String getMonth() {
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH) + 1;
		return month + "";
	}
	/**
	 * 获取当前日子
	 * @return
	 */
	public static String getDay() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DATE) + "";
	}
	/**
	 * 获取当前小时
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @return
	 */
	public static String get24Hour() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.HOUR_OF_DAY) + "";
	}
	/**
	 * 获取当前分钟
	 * @return
	 */
	public static String getMinute() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MINUTE) + "";
	}
	/**
	 * 获取当前秒数
	 * @return
	 */
	public static String getSecond() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.SECOND) + "";
	}
	/**
	 * 获取当前日期
	 * @return
	 */
	public static String getDate() {
		return getYear() + "-" + getMonth() + "-" + getDay();
	}
	/**
	 * 获取
	 * @return
	 */
	public static String getString() {
		return getYear()+ getMonth() + getDay()+get24Hour()+getMinute()+getSecond();
	}
	/**
	 * 获取今天的日期（自定义格式化）
	 * @param format
	 * @return
	 */
	public static String getDate(String format) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat simple = new SimpleDateFormat(format);
		String date = simple.format(calendar.getTime());
		if("MM".equals(format)){
			return String.valueOf(Integer.valueOf(date));
		}
		if("dd".equals(format)){
			return String.valueOf(Integer.valueOf(date));
		}
		return date;
	}
	/**
	 * 获取日期（自定义格式化）
	 * @param date
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static String getDate(String date,String format){
		SimpleDateFormat simple = null;
		String d="";
		Date sdate;
		try {
		if(date.length()==6||date.length()==7){
			simple = new SimpleDateFormat("yyyy-MM");
		}else{
			simple = new SimpleDateFormat("yyyy-MM-dd");
		}
		SimpleDateFormat simple2 = new SimpleDateFormat(format);
		sdate = simple.parse(date);
		d = simple2.format(sdate);
		if("MM".equals(format)){
			return String.valueOf(Integer.valueOf(d));
		}
		if("dd".equals(format)){
			return String.valueOf(Integer.valueOf(d));
		}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 日期间隔（相对）
	 * @param date
	 * @return 
	 * 
	 */
	public static String dayfromDay(String type,String d1,String d2,String format) throws ParseException{
		SimpleDateFormat simple = new SimpleDateFormat(format);
		Date date1 = simple.parse(d1);
		Date date2 = simple.parse(d2);
		long t1 = date1.getTime()/1000;
		long t2 = date2.getTime()/1000;
		long remain = t2-t1;
		long minute = remain/ONE_MINUTE;
		long hour = remain/ONE_HOUR;
		long day = remain/ONE_DAY;
		if("year".equals(type)) {
			   Calendar calendar = Calendar.getInstance();
			   calendar.setTime(date1);
			   int time = calendar.get(Calendar.YEAR);
			   calendar.setTime(date2);
			   return String.valueOf(calendar.get(Calendar.YEAR)-time);
		  	}

		 if("quarter".equals(type)) {
			   Calendar calendar = Calendar.getInstance();
			   calendar.setTime(date1);
			   int time = calendar.get(Calendar.YEAR) * 4;
			   calendar.setTime(date2);
			   time -= calendar.get(Calendar.YEAR) * 4;
			   calendar.setTime(date1);
			   time += calendar.get(Calendar.MONTH) / 4;
			   calendar.setTime(date2);
			   return String.valueOf(calendar.get(Calendar.MONTH)-time / 4);
		  	}

		 if("month".equals(type)) {
			   Calendar calendar = Calendar.getInstance();
			   calendar.setTime(date1);
			   int time = calendar.get(Calendar.YEAR) * 12;
			   calendar.setTime(date2);
			   time -= calendar.get(Calendar.YEAR) * 12;
			   calendar.setTime(date1);
			   time += calendar.get(Calendar.MONTH);
			   calendar.setTime(date2);
			   return String.valueOf(calendar.get(Calendar.MONTH)-time);
		  	}

		 if ("week".equals(type)) {
			   Calendar calendar = Calendar.getInstance();
			   calendar.setTime(date1);
			   int time = calendar.get(Calendar.YEAR) * 52;
			   calendar.setTime(date2);
			   time -= calendar.get(Calendar.YEAR) * 52;
			   calendar.setTime(date1);
			   time += calendar.get(Calendar.WEEK_OF_YEAR);
			   calendar.setTime(date2);
			   return String.valueOf(calendar.get(Calendar.WEEK_OF_YEAR)-time);
		  	}
			if("day".equals(type)){
				return ""+day;
			}
			if("hour".equals(type)){
				return ""+hour;
			}
			if("minute".equals(type)){
				return ""+minute;
			}

		  return "";
		
	}
	/**
	 * 日期间隔（绝对时间）
	 * @param type
	 * @param date1
	 * @param date2
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static String daytoDay(String type,String date1,String date2,String format) throws ParseException{
		SimpleDateFormat simple = new SimpleDateFormat(format);
		Date d1 = simple.parse(date1);
		Date d2 = simple.parse(date2); 
		long t1 = d1.getTime()/1000;
		long t2 = d2.getTime()/1000;
		long remain = t2-t1;
		long minute = remain/ONE_MINUTE;
		long hour = remain/ONE_HOUR;
		long day = remain/ONE_DAY;
		long week = remain/ONE_WEEK;
		long month = remain/ONE_MONTH;
		long quarter = remain/ONE_QUARTER;
		long year = remain/ONE_YEAR;
		if("minute".equals(type)){
			return ""+minute;
		}
		if("hour".equals(type)){
			return ""+hour;
		}
		if("day".equals(type)){
			return ""+day;
		}
		if("week".equals(type)){
			return ""+week;
		}
		if("month".equals(type)){
			return ""+month;
		}
		if("quarter".equals(type)){
			return ""+quarter;
		}
		if("year".equals(type)){
			return ""+year;
		}
		return "";
	}
	
	/**
	 * 日期间隔（相对）
	 * @param date
	 * @return 
	 * 
	 */
	public static String datediff(String type,Date date1,Date date2) throws ParseException{
		long t1 = date1.getTime()/1000;
		long t2 = date2.getTime()/1000;
		long remain = t2-t1;
		long minute = remain/ONE_MINUTE;
		long hour = remain/ONE_HOUR;
		long day = remain/ONE_DAY;
		if("year".equals(type)) {
			   Calendar calendar = Calendar.getInstance();
			   calendar.setTime(date1);
			   int time = calendar.get(Calendar.YEAR);
			   calendar.setTime(date2);
			   return String.valueOf(calendar.get(Calendar.YEAR)-time);
		  	}

		 if("quarter".equals(type)) {
			   Calendar calendar = Calendar.getInstance();
			   calendar.setTime(date1);
			   int time = calendar.get(Calendar.YEAR) * 4;
			   calendar.setTime(date2);
			   time -= calendar.get(Calendar.YEAR) * 4;
			   calendar.setTime(date1);
			   time += calendar.get(Calendar.MONTH) / 4;
			   calendar.setTime(date2);
			   return String.valueOf(calendar.get(Calendar.MONTH)-time / 4);
		  	}

		 if("month".equals(type)) {
			   Calendar calendar = Calendar.getInstance();
			   calendar.setTime(date1);
			   int time = calendar.get(Calendar.YEAR) * 12;
			   calendar.setTime(date2);
			   time -= calendar.get(Calendar.YEAR) * 12;
			   calendar.setTime(date1);
			   time += calendar.get(Calendar.MONTH);
			   calendar.setTime(date2);
			   return String.valueOf(calendar.get(Calendar.MONTH)-time);
		  	}

		 if ("week".equals(type)) {
			   Calendar calendar = Calendar.getInstance();
			   calendar.setTime(date1);
			   int time = calendar.get(Calendar.YEAR) * 52;
			   calendar.setTime(date2);
			   time -= calendar.get(Calendar.YEAR) * 52;
			   calendar.setTime(date1);
			   time += calendar.get(Calendar.WEEK_OF_YEAR);
			   calendar.setTime(date2);
			   return String.valueOf(calendar.get(Calendar.WEEK_OF_YEAR)-time);
		  	}
			if("day".equals(type)){
				return ""+day;
			}
			if("hour".equals(type)){
				return ""+hour;
			}
			if("minute".equals(type)){
				return ""+minute;
			}

		  return "";
		
	}
	/**
	 * 自定义时间相加
	 * @return
	 */
	public static Date dateAdd(String type,int amount, Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if("minute".equals(type)){
			calendar.add(Calendar.MINUTE, amount);
		}
		if("hour".equals(type)){
			calendar.add(Calendar.HOUR, amount);
		}
		if("day".equals(type)){
			calendar.add(Calendar.DATE, amount);
		}
		if("month".equals(type)){
			calendar.add(Calendar.MONTH, amount);
		}
		if("year".equals(type)){
			calendar.add(Calendar.YEAR, amount);
		}
		return calendar.getTime();
		
			 
	}
	
	/**
	 * 将字符串转换成日期类型
	 * @param format
	 * @param sdate
	 * @return
	 * @throws ParseException
	 */
	public static Date stringToDate(String format,String sdate){
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat(format);
		try {
			date = dateformat.parse(sdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * 将日期类型转换成字符串类型
	 * @param format
	 * @param sdate
	 * @return
	 */
	public static String dateToString(String format,Date sdate){
		SimpleDateFormat simple = new SimpleDateFormat(format);
		String date = simple.format(sdate);
		return date;
	}
	
	/*
	 * 时间戳转Date
	 */
	public static Date timeStampToDate(String timeStamp) {
		try {
		      SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		      Long time = new Long(timeStamp);
		      String d = format.format(time);
		      Date date;
			  date = format.parse(d);
			  return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
	      return null;
	}
	/*
	 * Date转时间戳
	 */
	public static Long dateToTimeStamp(Date date){ 
	   return  date.getTime();
	}
	
	/*
	 * 获取互联网时间
	 */
	public static Date getIntenetTime(){
//		try {
//			TimeZone.setDefault(TimeZone.getTimeZone("GMT+8")); // 时区设置 
//			URL url=new URL("http://www.bjtime.cn");//取得资源对象
//			URLConnection uc  = url.openConnection();
//			uc.connect(); //发出连接
//			long ld=uc.getDate(); //取得网站日期时间
//			Date date= DateUtil.timeStampToDate(String.valueOf(ld)); //转换为标准时间对象
//			return date;
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		return new Date();
	}
	
	/**
	 *  主方法测试
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
		//System.out.println(DateUtil.getYear());
		//System.out.println(DateUtil.getMonth());
		//System.out.println(DateUtil.getDay());
		//System.out.println(DateUtil.get24Hour());
		//System.out.println(DateUtil.getMinute());
		//System.out.println(DateUtil.getSecond());
		//System.out.println(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
		//System.out.println(DateUtil.getDate("yyyy-MM-dd HH:mm"));
		//System.out.println(DateUtil.getDate("yyyy-MM-dd"));
		//System.out.println(DateUtil.getDate("yyyy"));
		//System.out.println(DateUtil.getDate("MM"));
		//System.out.println(DateUtil.getDate("dd"));
		//System.out.println(DateUtil.getDate("2014-1-14","yyyy-MM-dd"));
		//System.out.println(DateUtil.getDate("2014-1-14","yyyy-MM"));
		//System.out.println(DateUtil.getDate("2014-1-14","MM"));
		//System.out.println(DateUtil.getDate("2014-1-14","dd"));
		//System.out.println(DateUtil.getDate("2014-01","yyyy"));
		//System.out.println(DateUtil.getDate("2014-01","MM"));
		//System.out.println("相差年："+DateUtil.daytoDay("year","2014-9-1", "2015-1-15", "yyyy-MM-dd"));
		//System.out.println("相差季度："+DateUtil.daytoDay("quarter","2014-1-14", "2014-02-15", "yyyy-MM-dd"));
		//System.out.println("相差月："+DateUtil.daytoDay("month","2013-1-31", "2012-3-1", "yyyy-MM-dd"));
		//System.out.println("相差周："+DateUtil.daytoDay("week","2014-01-3", "2014-02-2", "yyyy-MM"));
		//System.out.println("相差天："+DateUtil.daytoDay("day","2014-2-1", "2014-2-2", "yyyy-MM"));
		//System.out.println("相差小时："+DateUtil.daytoDay("hour","2014-1-10", "2014-2-20", "yyyy-MM-dd"));
		//System.out.println("相差分钟："+DateUtil.daytoDay("minute","2014-1-14", "2014-1-15", "yyyy-MM-dd"));
		
		
		//System.out.println("相差年："+DateUtil.dayfromDay("year","2014-9-1", "2015-1-15", "yyyy-MM-dd"));
		//System.out.println("相差季度："+DateUtil.dayfromDay("quarter","2014-1-14", "2014-02-15", "yyyy-MM-dd"));
		//System.out.println("相差月："+DateUtil.dayfromDay("month","2011-1-31", "2012-3-1", "yyyy-MM-dd"));
		//System.out.println("相差周："+DateUtil.dayfromDay("week","2014-01-3", "2014-02-2", "yyyy-MM"));
		//System.out.println("相差天："+DateUtil.dayfromDay("day","2014-2-1", "2014-2-2", "yyyy-MM"));
		//System.out.println("相差小时："+DateUtil.dayfromDay("hour","2014-1-10", "2014-2-20", "yyyy-MM-dd"));
		//System.out.println("相差分钟："+DateUtil.dayfromDay("minute","2014-1-14", "2014-1-15", "yyyy-MM-dd"));	
		
		//System.out.println(DateUtil.dateAdd("year",2,new Date()));
		//System.out.println(DateUtil.dateAdd("month",2,new Date()));
		//System.out.println(DateUtil.dateAdd("day",2,new Date()));
		System.out.println(DateUtil.dateToString("yyyy-MM-dd HH:mm:ss", new Date()));
		System.out.println(DateUtil.dateToString("yyyy-MM-dd HH:mm:ss", DateUtil.getIntenetTime()));
	}
	
}
