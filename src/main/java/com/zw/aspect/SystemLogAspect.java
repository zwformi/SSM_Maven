package com.zw.aspect;

import java.lang.reflect.Method;

import javax.annotation.Resource;
/*import javax.servlet.http.*;*/
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zw.pojo.User;
import com.zw.service.LogService;
import com.zw.annotation.SystemControllerLog;
import com.zw.annotation.SystemLoginLog;
import com.zw.annotation.SystemServiceLog;
import com.zw.pojo.Logxx;
import com.zw.util.DateUtil;

/** 
 * 切面类 
 * @author zw 
 * @since 2016-04-26 Pm 20:35 
 * @version 1.0 
 */  
@Aspect   
@Component 
public class SystemLogAspect{

	@Autowired  
	HttpServletRequest request; //这里可以获取到request
	private String UserName="";
    //注入Service用于把日志保存数据库   
    @Resource  
    private LogService logService;  
    //本地异常日志记录对象  
    private static final Logger logger = Logger.getLogger(SystemLogAspect.class);  
  
    //Service层切点   
    @Pointcut("@annotation(com.zw.annotation.SystemServiceLog)")  
    public void serviceAspect() {  
    }  
  
    //Controller层切点  
    @Pointcut("@annotation(com.zw.annotation.SystemControllerLog)")  
    public void controllerAspect() {  
    }  
    
    //用户登陆切入点
    @Pointcut("@annotation(com.zw.annotation.SystemLoginLog)")  
    public void userLoginAspect() {  
    }  
    /** 
     * 前置通知 用于拦截Controller层记录用户的操作 
     * 
     * @param joinPoint 切点 
     */  
    @Before("controllerAspect()")  
    public void doBefore(JoinPoint joinPoint) {      
  
    	/*HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); */  
        HttpSession session = request.getSession();  
        //读取session中的用户  
        User user = (User) session.getAttribute("user");  
        if(user!=null){
        	UserName = user.getUserName();
        }
        else{
        	UserName = "游客";
        }
        //请求的IP  
        String ip = request.getRemoteAddr();  
        try {  
            //*========控制台输出=========*//  
            System.out.println("=====前置通知开始=====");  
            System.out.println("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));  
            System.out.println("方法描述:" + getControllerMethodDescriptionOfBefore(joinPoint));  
            System.out.println("请求人:" + UserName);  
            System.out.println("请求IP:" + ip);  
            //*========数据库日志=========*//  
            Logxx log = new Logxx();  
            log.setDescription(getControllerMethodDescriptionOfBefore(joinPoint));  
            log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));  
            log.setType(0);  //操作日志
            log.setRequestIp(ip);  
            log.setExceptionCode(null);  
            log.setExceptionDetail(null);  
            log.setParams(null);  
            log.setCreateBy(UserName);  
            log.setCreateDate(DateUtil.getCurrentDate());  
            //保存数据库  
            logService.addlog(log);  
            System.out.println("=====前置通知结束=====");  
        } catch (Exception e) {  
            //记录本地异常日志  
            logger.error("==前置通知异常==");  
            logger.error("异常信息:{"+e.getMessage()+"}");  
        }  
    }  
  
    /** 
     * 异常通知 用于拦截service层记录异常日志 
     * 
     * @param joinPoint 
     * @param e 
     */  
    @AfterThrowing(value="serviceAspect()", throwing = "ex")  
    public void doAfterThrowing(JoinPoint joinPoint, Throwable ex) { 
        /*HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();*/  
        HttpSession session = request.getSession();  
        //读取session中的用户  
       /* User user = (User) session.getAttribute(WebConstants.CURRENT_USER); */ 
        User user = (User) session.getAttribute("user");         
        if(user!=null){
        	UserName = user.getUserName();
        }
        else{
        	UserName = "游客";
        }
        //获取请求ip  
        String ip = request.getRemoteAddr();  
        //获取用户请求方法的参数并序列化为JSON格式字符串  
        String params = "";  
        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {         	
            for (int i = 0; i < joinPoint.getArgs().length; i++) {  
                params += String.valueOf(joinPoint.getArgs()[i]) + ";";  
            }  
            
        }  
        try {  
              /*========控制台输出=========*/  
            System.out.println("=====异常通知开始=====");  
            System.out.println("异常代码:" + ex.getClass().getName());  
            System.out.println("异常信息:" + ex.getMessage());  
            System.out.println("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));  
            System.out.println("方法描述:" + getServiceMthodDescription(joinPoint));  
            System.out.println("请求人:" + UserName);  
            System.out.println("请求IP:" + ip);  
            System.out.println("请求参数:" + params);  
               /*==========数据库日志=========*/  
            Logxx log = new Logxx();  
            log.setDescription(getServiceMthodDescription(joinPoint));   
            log.setExceptionCode(ex.getClass().getName());  
            log.setType(1);  //异常类型
            log.setExceptionDetail(ex.getMessage());  
            log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));  
            log.setParams(params);  
            log.setCreateBy(UserName);  
            log.setCreateDate(DateUtil.getCurrentDate());  
            log.setRequestIp(ip);  
            //保存数据库  
            logService.addlog(log);  
            System.out.println("=====异常通知结束=====");  
        } catch (Exception e) {  
            //记录本地异常日志  
            logger.error("==异常通知异常==");  
            logger.error("异常信息:{"+e.getMessage()+"}");  
        }  
         /*==========记录本地异常日志==========*/  
        logger.error("异常方法:{"+joinPoint.getTarget().getClass().getName()+"}异常代码:{"+joinPoint.getSignature().getName()+"}异常信息:{"+ex.getClass().getName()+":"+ex.getMessage()+"}参数:{"+params+"}"); 
    }
    
    //用于记录用户登陆日志
    @After("userLoginAspect()")  
    public void afterMethod(JoinPoint joinPoint) {      
  
    	/*HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); */  
        HttpSession session = request.getSession();  
        //读取session中的用户  
        User user = (User) session.getAttribute("user");  
        if(user!=null){
        	UserName = user.getUserName();
        }
        else{
        	UserName = "游客";
        }
        //请求的IP  
        String ip = request.getRemoteAddr();  
        try {  
            //*========控制台输出=========*//  
            System.out.println("=====后置通知开始=====");  
            System.out.println("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));  
            System.out.println("方法描述:" + getControllerMethodDescriptionOfAfter(joinPoint));  
            System.out.println("请求人:" + UserName);  
            System.out.println("请求IP:" + ip);  
            //*========数据库日志=========*//  
            Logxx log = new Logxx();  
            log.setDescription(getControllerMethodDescriptionOfAfter(joinPoint));  
            log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));  
            log.setType(0);  //操作日志
            log.setRequestIp(ip);  
            log.setExceptionCode(null);  
            log.setExceptionDetail(null);  
            log.setParams(null);  
            log.setCreateBy(UserName);  
            log.setCreateDate(DateUtil.getCurrentDate());  
            //保存数据库  
            logService.addlog(log);  
            System.out.println("=====后置通知结束=====");  
        } catch (Exception e) {  
            //记录本地异常日志  
            logger.error("==后置通知异常==");  
            logger.error("异常信息:{"+e.getMessage()+"}");  
        }  
    }  
    /** 
     * 获取注解中对方法的描述信息 用于service层注解  
     * 
     * @param joinPoint 切点 
     * @return 方法描述 
     * @throws Exception 
     */  
    public static String getServiceMthodDescription(JoinPoint joinPoint)  
            throws Exception {  
        String targetName = joinPoint.getTarget().getClass().getName();  
        String methodName = joinPoint.getSignature().getName();  
        Object[] arguments = joinPoint.getArgs();  
        Class targetClass = Class.forName(targetName);  
        Method[] methods = targetClass.getMethods();  
        String description = "";  
        for (Method method : methods) {  
            if (method.getName().equals(methodName)) {  
                Class[] clazzs = method.getParameterTypes();  
                if (clazzs.length == arguments.length) {  
                    description = method.getAnnotation(SystemServiceLog.class).description();  
                    break;  
                }  
            }  
        }  
        return description;  
    }  
  
    /** 
     * 获取注解中对方法的描述信息 用于Controller层注解 
     * 
     * @param joinPoint 切点 
     * @return 方法描述 
     * @throws Exception 
     */  
    public static String getControllerMethodDescriptionOfBefore(JoinPoint joinPoint) throws Exception {  
        String targetName = joinPoint.getTarget().getClass().getName();  
        String methodName = joinPoint.getSignature().getName();  
        Object[] arguments = joinPoint.getArgs();  
        Class targetClass = Class.forName(targetName);  
        Method[] methods = targetClass.getMethods();  
        String description = "";  
        for (Method method : methods) {  
            if (method.getName().equals(methodName)) {  
                Class[] clazzs = method.getParameterTypes();   
                if (clazzs.length == arguments.length) {  
                    description = method.getAnnotation(SystemControllerLog.class).description();  
                    break;  
                }  
            }  
        }  
        return description;   
    }  
    
    /** 
     * 后置通知
     * 获取注解中对方法的描述信息 用于Controller层注解 
     * 
     * @param joinPoint 切点 
     * @return 方法描述 
     * @throws Exception 
     */  
    public static String getControllerMethodDescriptionOfAfter(JoinPoint joinPoint) throws Exception {  
        String targetName = joinPoint.getTarget().getClass().getName();  
        String methodName = joinPoint.getSignature().getName();  
        Object[] arguments = joinPoint.getArgs();  
        Class targetClass = Class.forName(targetName);  
        Method[] methods = targetClass.getMethods();  
        String description = "";  
        for (Method method : methods) {  
            if (method.getName().equals(methodName)) {  
                Class[] clazzs = method.getParameterTypes();   
                if (clazzs.length == arguments.length) {  
                    description = method.getAnnotation(SystemLoginLog.class).description();  
                    break;  
                }  
            }  
        }  
        return description;   
    }  
}
