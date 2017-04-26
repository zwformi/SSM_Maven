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
	 //�����쳣��־��¼����  
    private static final Logger logger = Logger.getLogger(PrivilegeAspect.class);  
  
    //Service���е�   
    @Pointcut("@annotation(com.zw.annotation.PrivilegeInfo)")  
    public void serviceAspect() {  
    } 
	
    @Around("serviceAspect()")
    public void isAccessMethod(ProceedingJoinPoint joinPoint) throws Throwable {
    	/**
         * 1.��ȡ����Ŀ�귽��Ӧ�þ߱���Ȩ��
         *  Ϊ����Ŀ�귽����PrivilegeInfoע�⣬�������Ƕ���Ľ���������Ҫ�õ���Ŀ�����class��ʽ������������
         */
        Class targetClass = joinPoint.getTarget().getClass();
        String methodName = joinPoint.getSignature().getName();
        //�õ��÷����ķ���Ȩ��
        String methodAccess = parse(targetClass, methodName);
    	
        //��ǰ�û���ӵ�е�����Ȩ��
        //TODO ���ݿⷵ���û�Ȩ������
        List<String> privilegeList =new ArrayList<String>();   
        privilegeList.add("save");
        privilegeList.add("add");
        privilegeList.add("del");
        /*
         * 2.�����û���Ȩ�ޣ����Ƿ�ӵ��Ŀ�귽����Ӧ��Ȩ��
         */
        boolean isAccessed = false;
        for (String Privilege : privilegeList) {
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
            if (Privilege != null && 
            		Privilege.equalsIgnoreCase(methodAccess)) {
                isAccessed = true;
                break;
            }
        }
        /*
         * 3.����û�ӵ��Ȩ�ޣ������Ŀ�귽���������û�У��򲻵���Ŀ�귽����ֻ������ʾ
         */
        if (isAccessed) {
            joinPoint.proceed();//����Ŀ�귽��
        } else {
            System.out.println("��û��Ȩ��");
        }
	
    }
    
    /**
     * ����ע��
     * @param targetClass��Ŀ�����class��ʽ
     * @param methodName���ڿͻ��˵����ĸ�����,methodName�ʹ����ĸ�������
     * @return
     * @throws Exception 
     */
    public static String parse(Class targetClass, String methodName) throws Exception {
        /*
         * Ϊ����������￼�Ǹ÷���û�в���
         */
        Method method = targetClass.getMethod(methodName);
        String privilegeInfo = "";
        //�жϷ������Ƿ���Privilegeע��
        if (method.isAnnotationPresent(PrivilegeInfo.class)) {
            //�õ������ϵ�ע��
            privilegeInfo = method.getAnnotation(PrivilegeInfo.class).value();
        }
        return privilegeInfo;
    }
	
}
