package com.zw.aspect;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zw.annotation.PrivilegeInfo;
import com.zw.annotation.SystemLoginLog;
import com.zw.dao.PrivilegeDao;
import com.zw.pojo.Privilege;
import com.zw.pojo.User;

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
	@Resource
	PrivilegeDao privilegedao;
	 //本地异常日志记录对象  
    private static final Logger logger = Logger.getLogger(PrivilegeAspect.class);  
   
  
    //Service层切点   
    @Pointcut("@annotation(com.zw.annotation.PrivilegeInfo)")  
    public void serviceAspect() {  
    } 
	
    @Around("serviceAspect()")
    public Object isAccessMethod(ProceedingJoinPoint joinPoint) throws Throwable {

        //得到该方法的访问权限
        String methodAccess = getServicePrivilegeInfo(joinPoint);
    	
        //当前用户所拥有的所有权限
        //TODO 数据库返回用户权限数据
        HttpSession session = request.getSession();  
        //读取session中的用户  
        User user = (User) session.getAttribute("user");
        int roleid = user.getRoleid();
        List<Privilege> privilegeList =privilegedao.selectPrivilege(roleid);
        /*
         * 2.遍历用户的权限，看是否拥有目标方法对应的权限
         */
        boolean isAccessed = false;
        for (Privilege Privilege : privilegeList) {
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
            if (Privilege.getModule_node() != null && 
            		Privilege.getModule_node().equalsIgnoreCase(methodAccess)) {
                isAccessed = true;
                break;
            }
        }
        /*
         * 3.如果用户拥有权限，则调用目标方法　，如果没有，则不调用目标方法，只给出提示
         */
        if (isAccessed) {
            joinPoint.proceed();//调用目标方法
            return 1;
        } else {
            System.out.println("你没有权限");
            return 0;
        }
	
    }
    
    /**
     * 解析注解获取权限
     * @param targetClass　目标类的class形式
     * @param methodName　在客户端调用哪个方法,methodName就代表哪个方法　
     * @return
     * @throws Exception 
     */
    public static String getServicePrivilegeInfo(JoinPoint joinPoint) throws Exception {
    	String targetName = joinPoint.getTarget().getClass().getName();  
        String methodName = joinPoint.getSignature().getName();  
        Object[] arguments = joinPoint.getArgs();  
        Class targetClass = Class.forName(targetName);  
        Method[] methods = targetClass.getMethods();  
        String privilegeInfo = "";  
        for (Method method : methods) {  
            if (method.getName().equals(methodName)) {  
                Class[] clazzs = method.getParameterTypes();   
                if (clazzs.length == arguments.length) {  
                	privilegeInfo = method.getAnnotation(PrivilegeInfo.class).value();  
                    break;  
                }  
            }  
        }  
        return privilegeInfo;   
    }  
}
