package com.krt.core.util;

/**  
*   
* 项目名称：zgqzhsq  
* 类名称：Sjm  
* 类描述：生成随机码  
* 创建人：殷帅
* 创建时间：2014-9-1 下午07:10:37  
* 修改人：殷帅  
* 修改时间：2014-9-1 下午07:10:37  
* 修改备注：  
* @version   
*   
*/
public class SjmUtil {
	
	/*
	 * 获取字符随机码
	 */
	public  static String getString(int j){
		char mapTable[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
				'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
				'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8',
				'9' };
		// 取随机产生的随机码
		String strEnsure = "";
		// j代表j位随机码
		for (int i = 0; i < j; ++i) {
			strEnsure += mapTable[(int) (mapTable.length * Math.random())];
		}
		return strEnsure;
	}
	
	/*
	 * 获取随机数字
	 */
	public  static String getInt(int j){
		char mapTable[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8','9' };
		// 取随机产生的随机码
		String strEnsure = "";
		// j代表j位随机码
		for (int i = 0; i < j; ++i) {
			strEnsure += mapTable[(int) (mapTable.length * Math.random())];
		}
		return strEnsure;
	}
	public static void main(String[] args) {
		System.out.println(SjmUtil.getString(16));
	}
}
