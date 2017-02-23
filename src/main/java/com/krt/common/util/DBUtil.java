package com.krt.common.util;

import org.apache.log4j.Logger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**  
*   
* 项目名称：java  
* 类名称：DBUtil  
* 类描述：数据库备份还原工具类  
* 创建人：殷帅  
* 创建时间：2014-12-3 上午11:44:56  
* 修改人：Administrator  
* 修改时间：2014-12-3 上午11:44:56  
* 修改备注：  
* @version   
*   
*/ 
public class DBUtil {
	
	private static Logger logger = Logger.getLogger(DBUtil.class);
	
	private static PropertyUtil propertyUtil = new PropertyUtil("db.properties");
	
    /**
     * 备份数据库
     * @author 殷帅
     * @date 2014-12-4
     */
	public static boolean backDB(){
		
		//生成备份文件名
		SimpleDateFormat sd=new SimpleDateFormat("yyyyMMddHHmmss");
		String fineName="dbBackUp-"+sd.format(new Date());
		String sqlName=fineName+".sql";
		String basePath = "D:\\";
		String pathSql=basePath+"dbBackUp"+File.separator;
		logger.debug(pathSql);
		logger.debug("开始备份数据库"+sqlName);
		try {
			File filePathSql = new File(pathSql);
			if(!filePathSql.exists()){
				filePathSql.mkdir();
			}
			StringBuffer sbs = new StringBuffer();
			sbs.append(propertyUtil.getValue("sqlPath"));
			sbs.append("mysqldump");
			sbs.append(" -h ").append(propertyUtil.getValue("host"));
			sbs.append(" -u").append(propertyUtil.getValue("username"));
			sbs.append(" -p").append(propertyUtil.getValue("password"));
			sbs.append(" ").append(propertyUtil.getValue("dbName"));
			logger.debug(sbs.toString());
		    Runtime rt = Runtime.getRuntime();
		    // 调用 调用mysql的安装目录的命令
		    Process child = rt.exec(sbs.toString());
		    // 设置导出编码为utf-8。这里必须是utf-8
		    // 把进程执行中的控制台输出信息写入.sql文件，即生成了备份文件。注：如果不对控制台信息进行读出，则会导致进程堵塞无法运行
		    InputStream in = child.getInputStream();// 控制台的输出信息作为输入流
		    InputStreamReader xx = new InputStreamReader(in, "utf-8");
		    // 设置输出流编码为utf-8。这里必须是utf-8，否则从流中读入的是乱码
		    String inStr;
		    StringBuffer sb = new StringBuffer("");
		    String outStr;
		    // 组合控制台输出信息字符串
		    BufferedReader br = new BufferedReader(xx);
		    while ((inStr = br.readLine()) != null) {
		     sb.append(inStr + "\r\n");
		    }
		    outStr = sb.toString();
		    // 要用来做导入用的sql目标文件：
		    FileOutputStream fout = new FileOutputStream( pathSql+sqlName);
		    OutputStreamWriter writer = new OutputStreamWriter(fout, "utf-8");
		    writer.write(outStr);
		    writer.flush();
		    in.close();
		    xx.close();
		    br.close();
		    writer.close();
		    fout.close();
		    logger.debug("备份数据库成功"+sqlName);
		    return true;
		  } catch (Exception e) {
			  e.printStackTrace();
			  logger.error("备份数据库失败"+sqlName,e);
			  return false;
		  }
	}
}
