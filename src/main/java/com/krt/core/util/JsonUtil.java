package com.krt.core.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.*;


/**  
*   
* 项目名称：zfzwServer  
* 类名称：JsonUtil  
* 类描述：JSON转换工具类  
* 创建人：殷帅  
* 创建时间：2015-8-20 上午11:32:24  
*   
*/ 
public class JsonUtil {

	/**
	 * 对象转换成JSON字符串
	 * 
	 * @param obj
	 *            需要转换的对象
	 * @return 对象的string字符
	 */
	public static String toJson(Object obj) {
		JSONObject jSONObject = JSONObject.fromObject(obj);
		return jSONObject.toString();
	}

	/**
	 * JSON字符串转换成对象
	 * 
	 * @param jsonString
	 *            需要转换的字符串
	 * @param type
	 *            需要转换的对象类型
	 * @return 对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fromJson(String jsonString, Class<T> type) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		return (T) JSONObject.toBean(jsonObject, type);
	}

	/**
	 * 将JSONArray对象转换成list集合
	 * 
	 * @param jsonArr
	 * @return
	 */
	public static List<Object> jsonToList(JSONArray jsonArr) {
		List<Object> list = new ArrayList<Object>();
		for (Object obj : jsonArr) {
			if (obj instanceof JSONArray) {
				list.add(jsonToList((JSONArray) obj));
			} else if (obj instanceof JSONObject) {
				list.add(jsonToMap((JSONObject) obj));
			} else {
				list.add(obj);
			}
		}
		return list;
	}

	/**
	 * 将json字符串转换成map对象
	 * 
	 * @param json
	 * @return
	 */
	public static Map<String, Object> jsonToMap(String json) {
		JSONObject obj = JSONObject.fromObject(json);
		return jsonToMap(obj);
	}

	/**
	 * 将JSONObject转换成map对象
	 * 
	 * @param json
	 * @return
	 */
	public static Map<String, Object> jsonToMap(JSONObject obj) {
		Set<?> set = obj.keySet();
		Map<String, Object> map = new HashMap<String, Object>(set.size());
		for (Object key : obj.keySet()) {
			Object value = obj.get(key);
			if (value instanceof JSONArray) {
				map.put(key.toString(), jsonToList((JSONArray) value));
			} else if (value instanceof JSONObject) {
				map.put(key.toString(), jsonToMap((JSONObject) value));
			} else {
				map.put(key.toString(), obj.get(key));
			}

		}
		return map;
	}
		
	/*
	 * map 转json
	 */
	@SuppressWarnings("rawtypes")
	public static JSONObject mapToJson(Map map){
		JSONObject json  = new JSONObject();
		for (Object key : map.keySet()) {  
            String value = map.get(key)==null?"":(map.get(key)+"");
            json.put(key, value);
        } 
		return json;
	}
	
	/**
	 * 将Map的字段变成String
	 * @param m
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map mapTomap(Map m){
		Map map = null;
		if(m!=null){
			map = new HashMap();
			for (Object key : m.keySet()) {  
	            map.put(key+"", m.get(key)==null?"":m.get(key)+"");
	        }  
		}
		return map;
	}
	
	/**
	 * 将List的字段变成String
	 * @param list
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static List<Map> listToList(List<Map> list) {
		if(list!=null){
			for(Map m:list){
				if(m!=null){
					m = mapTomap(m);
				}
			}
			return list;
		}else{
			return null;
		}
	}
}
