package com.zw.aspect;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zw.annotation.PrivilegeInfo;

/** 
 * 切面类 
 * @author zw 
 * @since 2016-04-26 Pm 20:35 
 * @version 1.0 
 */  
@Aspect   
@Component
public class PrivilegeAspect {
	@Autowired  
	HttpServletRequest request; //这里可以获取到request
	 //本地异常日志记录对象  
    private static final Logger logger = Logger.getLogger(PrivilegeAspect.class);  
  
    //Service层切点   
    @Pointcut("@annotation(com.zw.annotation.PrivilegeInfo)")  
    public void serviceAspect() {  
    } 
	
    @Around("serviceAspect()")
    public void isAccessMethod(ProceedingJoinPoint joinPoint) throws Throwable {
    	/**
         * 1.获取访问目标方法应该具备的权限
         *  为解析目标方法的PrivilegeInfo注解，根据我们定义的解析器，需要得到：目标类的class形式　方法的名称
         */
        Class targetClass = joinPoint.getTarget().getClass();
        String methodName = joinPoint.getSignature().getName();
        //得到该方法的访问权限
        String methodAccess = parse(targetClass, methodName);
    	
        //当前用户所拥有的所有权限
        //TODO 数据库返回用户权限数据
        List<String> privilegeList =new ArrayList<String>();   
        privilegeList.add("save");
        privilegeList.add("add");
        privilegeList.add("del");
        /*
         * 2.遍历用户的权限，看是否拥有目标方法对应的权限
         */
        boolean isAccessed = false;
        for (String Privilege : privilegeList) {
            /*
             * 如果目标方法没有使用PrivilegeInfo注解，则解析出来的权限字符串就为空字符串
             * 则默认用户拥有这个权限
             */
            if ("".equals(methodAccess)) {
                isAccessed = true;
                break;
            }
            /*
             * 用户原有权限列表中有的权限与目标方法上PrivilegeInfo注解配置的权限进行匹配
             */
            if (Privilege != null && 
            		Privilege.equalsIgnoreCase(methodAccess)) {
                isAccessed = true;
                break;
            }
        }
        /*
         * 3.如果用户拥有权限，则调用目标方法　，如果没有，则不调用目标方法，只给出提示
         */
        if (isAccessed) {
            joinPoint.proceed();//调用目标方法
        } else {
            System.out.println("你没有权限");
        }
	
    }
    
    /**
     * 解析注解
     * @param targetClass　目标类的class形式
     * @param methodName　在客户端调用哪个方法,methodName就代表哪个方法　
     * @return
     * @throws Exception 
     */
    public static String parse(Class targetClass, String methodName) throws Exception {
        /*
         * 为简单起见，这里考虑该方法没有参数
         */
        Method method = targetClass.getMethod(methodName);
        String privilegeInfo = "";
        //判断方法上是否有Privilege注解
        if (method.isAnnotationPresent(PrivilegeInfo.class)) {
            //得到方法上的注解
            privilegeInfo = method.getAnnotation(PrivilegeInfo.class).value();
        }
        return privilegeInfo;
    }
	
}
