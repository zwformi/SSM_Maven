package com.zw.test;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.zw.pojo.User;
import com.zw.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)		//��ʾ�̳���SpringJUnit4ClassRunner��
@ContextConfiguration(locations = {"classpath:springmybatis.xml"})
public class ServiceTest1 {
	@Resource
	private IUserService userService = null; 
	private static Logger logger = Logger.getLogger(ServiceTest1.class);
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test1() {
		User user = userService.getUserById(1);
	    System.out.println(user.getUserName());
	    logger.info("ֵ��"+user.getUserName());
		logger.info(JSON.toJSONString(user));
		System.out.println(111);
	}

}
