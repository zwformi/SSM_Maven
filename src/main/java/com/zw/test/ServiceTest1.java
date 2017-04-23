package com.zw.test;

import java.util.List;

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

@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
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
		List<User> lUser = userService.selectAllStudent();
		for(User user:lUser){
			logger.info("值："+user.getUserName());
			logger.info(JSON.toJSONString(user));
		}
	    
	}

}
