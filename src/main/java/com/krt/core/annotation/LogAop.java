package com.krt.core.annotation;

import java.lang.annotation.*;


/**  
 * @Description: 自定义日志注解
 * @date 2016年7月21日
 * @version 1.0
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})    
@Retention(RetentionPolicy.RUNTIME)  
@Documented 
public @interface LogAop {
	String description()  default "";
	String type() default "1"; 
}
