package com.zw.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Ȩ��ע��
 * @author zw
 *
 */
@Target(ElementType.METHOD)//���ע����Ӧ���ڷ�����
@Retention(RetentionPolicy.RUNTIME)
public @interface PrivilegeInfo {
	 /**
     * Ȩ�޵�����
     * @return
     */
    String value() default "";
}
