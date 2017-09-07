package com.krt.core.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class IdUtil {
	
    /**
     * 随机生成UUID
     * @return
     */
    public static synchronized String getUUID(){
        UUID uuid=UUID.randomUUID();
        String str = uuid.toString();  
        String uuidStr=str.replace("-", "");
        return uuidStr;
    }
    /**
     * 根据字符串生成固定UUID
     * @param name
     * @return
     */
    public static synchronized String getUUID(String name){
        UUID uuid=UUID.nameUUIDFromBytes(name.getBytes());
        String str = uuid.toString();  
        String uuidStr=str.replace("-", "");
        return uuidStr;
    }
    
	
	//返回类似201603181601064663899883390订单号
	public static String getOrderNO(){
		return getOrderNO("yyyyMMddHHmmssSSS",10);
	}
	
	public synchronized static String getOrderNO(String datePartten,int randomNumeric) {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		SimpleDateFormat sd = new SimpleDateFormat(datePartten);
		return sd.format(new Date()) + RandomStringUtils.randomNumeric(randomNumeric);
	}

    public static void main(String[] args) {
		System.out.println(getOrderNO());
	}
}
