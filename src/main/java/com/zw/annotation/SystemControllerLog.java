package com.zw.annotation;

import java.lang.annotation.*;


/** 
 *�Զ���ע�� ����Controller 
 */  
@Target({ElementType.PARAMETER, ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
@Documented  
public @interface SystemControllerLog {
	 String description() default "";  
}
