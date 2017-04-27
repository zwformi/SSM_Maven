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
 * ������ 
 * @author zw 
 * @since 2016-04-26 Pm 20:35 
 * @version 1.0 
 */  
@Aspect   
@Component
public class PrivilegeAspect {
	@Autowired  
	HttpServletRequest request; //������Ի�ȡ��request
	@Resource
	PrivilegeDao privilegedao;
	 //�����쳣��־��¼����  
    private static final Logger logger = Logger.getLogger(PrivilegeAspect.class);  
   
  
    //Service���е�   
    @Pointcut("@annotation(com.zw.annotation.PrivilegeInfo)")  
    public void serviceAspect() {  
    } 
	
    @Around("serviceAspect()")
    public Object isAccessMethod(ProceedingJoinPoint joinPoint) throws Throwable {

        //�õ��÷����ķ���Ȩ��
        String methodAccess = getServicePrivilegeInfo(joinPoint);
    	
        //��ǰ�û���ӵ�е�����Ȩ��
        //TODO ���ݿⷵ���û�Ȩ������
        HttpSession session = request.getSession();  
        //��ȡsession�е��û�  
        User user = (User) session.getAttribute("user");
        int roleid = user.getRoleid();
        List<Privilege> privilegeList =privilegedao.selectPrivilege(roleid);
        /*
         * 2.�����û���Ȩ�ޣ����Ƿ�ӵ��Ŀ�귽����Ӧ��Ȩ��
         */
        boolean isAccessed = false;
        for (Privilege Privilege : privilegeList) {
            /*
             * ���Ŀ�귽��û��ʹ��PrivilegeInfoע�⣬�����������Ȩ���ַ�����Ϊ���ַ���
             * ��Ĭ���û�ӵ�����Ȩ��
             */
            if ("".equals(methodAccess)) {
                isAccessed = true;
                break;
            }
            /*
             * �û�ԭ��Ȩ���б����е�Ȩ����Ŀ�귽����PrivilegeInfoע�����õ�Ȩ�޽���ƥ��
             */
            if (Privilege.getModule_node() != null && 
            		Privilege.getModule_node().equalsIgnoreCase(methodAccess)) {
                isAccessed = true;
                break;
            }
        }
        /*
         * 3.����û�ӵ��Ȩ�ޣ������Ŀ�귽���������û�У��򲻵���Ŀ�귽����ֻ������ʾ
         */
        if (isAccessed) {
            joinPoint.proceed();//����Ŀ�귽��
            return 1;
        } else {
            System.out.println("��û��Ȩ��");
            return 0;
        }
	
    }
    
    /**
     * ����ע���ȡȨ��
     * @param targetClass��Ŀ�����class��ʽ
     * @param methodName���ڿͻ��˵����ĸ�����,methodName�ʹ����ĸ�������
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
