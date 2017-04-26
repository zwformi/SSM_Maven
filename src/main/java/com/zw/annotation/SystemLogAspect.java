package com.zw.annotation;

import java.lang.reflect.Method;

import javax.annotation.Resource;
/*import javax.servlet.http.*;*/
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.*;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.zw.pojo.User;
import com.zw.service.LogService;
import com.zw.pojo.Logxx;
import com.zw.util.DateUtil;

/** 
 * �е��� 
 * @author tiangai 
 * @since 2014-08-05 Pm 20:35 
 * @version 1.0 
 */  
@Aspect   
@Component 
public class SystemLogAspect{

	@Autowired  
	HttpServletRequest request; //������Ի�ȡ��request
	private String UserName="";
    //ע��Service���ڰ���־�������ݿ�   
    @Resource  
    private LogService logService;  
    //�����쳣��־��¼����  
    private static final Logger logger = Logger.getLogger(SystemLogAspect.class);  
  
    //Service���е�   
    @Pointcut("@annotation(com.zw.annotation.SystemServiceLog)")  
    public void serviceAspect() {  
    }  
  
    //Controller���е�  
    @Pointcut("@annotation(com.zw.annotation.SystemControllerLog)")  
    public void controllerAspect() {  
    }  
    

    /** 
     * ǰ��֪ͨ ��������Controller���¼�û��Ĳ��� 
     * 
     * @param joinPoint �е� 
     */  
    @Before("controllerAspect()")  
    public void doBefore(JoinPoint joinPoint) {      
  
    	/*HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); */  
        HttpSession session = request.getSession();  
        //��ȡsession�е��û�  
        User user = (User) session.getAttribute("user");  
        if(user!=null){
        	UserName = user.getUserName();
        }
        else{
        	UserName = "�ο�";
        }
        //�����IP  
        String ip = request.getRemoteAddr();  
        try {  
            //*========����̨���=========*//  
            System.out.println("=====ǰ��֪ͨ��ʼ=====");  
            System.out.println("���󷽷�:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));  
            System.out.println("��������:" + getControllerMethodDescription(joinPoint));  
            System.out.println("������:" + UserName);  
            System.out.println("����IP:" + ip);  
            //*========���ݿ���־=========*//  
            Logxx log = new Logxx();  
            log.setDescription(getControllerMethodDescription(joinPoint));  
            log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));  
            log.setType(0);  //������־
            log.setRequestIp(ip);  
            log.setExceptionCode(null);  
            log.setExceptionDetail(null);  
            log.setParams(null);  
            log.setCreateBy(UserName);  
            log.setCreateDate(DateUtil.getCurrentDate());  
            //�������ݿ�  
            logService.addlog(log);  
            System.out.println("=====ǰ��֪ͨ����=====");  
        } catch (Exception e) {  
            //��¼�����쳣��־  
            logger.error("==ǰ��֪ͨ�쳣==");  
            logger.error("�쳣��Ϣ:{"+e.getMessage()+"}");  
        }  
    }  
  
    /** 
     * �쳣֪ͨ ��������service���¼�쳣��־ 
     * 
     * @param joinPoint 
     * @param e 
     */  
    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")  
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {  
        /*HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();*/  
        HttpSession session = request.getSession();  
        //��ȡsession�е��û�  
       /* User user = (User) session.getAttribute(WebConstants.CURRENT_USER); */ 
        User user = (User) session.getAttribute("user");         
        if(user!=null){
        	UserName = user.getUserName();
        }
        else{
        	UserName = "�ο�";
        }
        //��ȡ����ip  
        String ip = request.getRemoteAddr();  
        //��ȡ�û����󷽷��Ĳ��������л�ΪJSON��ʽ�ַ���  
        String params = "";  
        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {  
            for (int i = 0; i < joinPoint.getArgs().length; i++) {  
                params += JSON.toJSONString(joinPoint.getArgs()[i]) + ";";  
            }  
        }  
        try {  
              /*========����̨���=========*/  
            System.out.println("=====�쳣֪ͨ��ʼ=====");  
            System.out.println("�쳣����:" + e.getClass().getName());  
            System.out.println("�쳣��Ϣ:" + e.getMessage());  
            System.out.println("�쳣����:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));  
            System.out.println("��������:" + getServiceMthodDescription(joinPoint));  
            System.out.println("������:" + UserName);  
            System.out.println("����IP:" + ip);  
            System.out.println("�������:" + params);  
               /*==========���ݿ���־=========*/  
            Logxx log = new Logxx();  
            log.setDescription(getServiceMthodDescription(joinPoint));   
            log.setExceptionCode(e.getClass().getName());  
            log.setType(1);  //�쳣����
            log.setExceptionDetail(e.getMessage());  
            log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));  
            log.setParams(params);  
            log.setCreateBy(UserName);  
            log.setCreateDate(DateUtil.getCurrentDate());  
            log.setRequestIp(ip);  
            //�������ݿ�  
            logService.addlog(log);  
            System.out.println("=====�쳣֪ͨ����=====");  
        } catch (Exception ex) {  
            //��¼�����쳣��־  
            logger.error("==�쳣֪ͨ�쳣==");  
            logger.error("�쳣��Ϣ:{"+ex.getMessage()+"}");  
        }  
         /*==========��¼�����쳣��־==========*/  
        logger.error("�쳣����:{}�쳣����:{}�쳣��Ϣ:{}����:{}"+ joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName()+ e.getClass().getName()+ e.getMessage()+params); 
    }
    
    
    /** 
     * ��ȡע���жԷ�����������Ϣ ����service��ע��  
     * 
     * @param joinPoint �е� 
     * @return �������� 
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
     * ��ȡע���жԷ�����������Ϣ ����Controller��ע�� 
     * 
     * @param joinPoint �е� 
     * @return �������� 
     * @throws Exception 
     */  
    public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {  
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
    
/*    public int getOrder() {  
        // TODO Auto-generated method stub  
        return 1;  
    }*/
}
