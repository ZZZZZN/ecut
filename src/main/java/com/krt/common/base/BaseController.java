package com.krt.common.base;

import com.krt.common.util.DateEditor;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**  
 * @Description: 控制器基类
 * @author 殷帅
 * @date 2016年7月16日
 * @version 1.0
 */
public class BaseController {
	
	protected final Logger logger = Logger.getLogger(this.getClass());

	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		//对于需要转换为Date类型的属性，使用DateEditor进行处理
		binder.registerCustomEditor(Date.class, new DateEditor());
	}
	
	/**
	 * 异常统一处理(不处理捕获的异常)
	 * @param ex
	 * @throws Exception
	 */
	@ExceptionHandler(RuntimeException.class) 
	public String exceptionHandler(Exception ex) throws Exception {
		if (ex instanceof UnauthorizedException) {//shiro无权限
			logger.error("shiro无权限",ex);
			return "error/noPermission";
		}else{
			logger.error("程序500异常",ex);
			return "error/500";
		}
	}
	
}
