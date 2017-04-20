package com.zw.test;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.zw.pojo.User;
import com.zw.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:springmybatis.xml"})

public class MyTest {
	private static Logger logger = Logger.getLogger(MyTest.class);

	@Resource
	private IUserService userService = null;  

	@Test
	public void test() {
		User user = userService.getUserById(1); 
		logger.info(JSON.toJSONString(user));
	}
}
