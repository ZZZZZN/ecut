package com.krt.core.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.util.Map;

/**  
 * @Description: Shiro工具类
 * @author 殷帅
 * @date 2016年7月20日
 * @version 1.0
 */
public class ShiroUtil {

	/**
	 * 将一些数据放到ShiroSession中,以便于其它地方使用
	 * @param key
	 * @param value
	 */
	public static void setSession(Object key, Object value){
		Subject currentUser = SecurityUtils.getSubject();
		if(null != currentUser){
			Session session = currentUser.getSession();
			System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");
			if(null != session){
				session.setAttribute(key, value);
			}
		}
	}

	/**
	 * 获取session用户
	 * @return
	 */
	public static Map getCurrentUser() {
		Map currentUser = null;
		Subject subject = SecurityUtils.getSubject();
		if (null != subject) {
			Session session = subject.getSession();
			currentUser = (Map) session.getAttribute("currentUser");
		}
		return currentUser;
	}

    /**
	 * 退出
	 */
	public static void logOut(){
		Subject subject = SecurityUtils.getSubject();
		if (null != subject) {
			subject.logout();
		}
	}
}
