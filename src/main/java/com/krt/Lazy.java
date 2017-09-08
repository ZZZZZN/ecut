package com.krt;
import com.krt.generator.GenControllerUtil;
import com.krt.generator.GenEntityUtil;
import com.krt.generator.GenJspUtil;
import com.krt.generator.GenMapperUtil;
import com.krt.generator.GenMapperXmlUtil;
import com.krt.generator.GenServiceUtil;

/**  
 * @Description: 偷懒代码生成
 * @author 殷帅
 * @date 2016年7月26日
 * @version 1.0
 */
public class Lazy {
	
	public static void main(String[] args) {
		new Lazy().generator("com.krt.ruanjian.course", "title_examine", "TitleExamine");
	}
	
	public void generator(String packageName,String tableName,String entityName){
		new GenControllerUtil(packageName, tableName, entityName);
		new GenServiceUtil(packageName, tableName, entityName);
		new GenMapperUtil(packageName, tableName, entityName);
		new GenEntityUtil(packageName, tableName, entityName);
		new GenJspUtil(packageName, tableName, entityName);
		new GenMapperXmlUtil(packageName, tableName, entityName);
	}
}
