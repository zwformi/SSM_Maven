package com.zw.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.zw.pojo.User;
import com.zw.service.IUserService;


@Controller
public class TestStudentController {
	@Resource
	private IUserService userService = null; 
	private static Logger logger = Logger.getLogger(TestStudentController.class);
	
	 @RequestMapping(value="/test", method = RequestMethod.GET)
	 @ResponseBody
	 public ModelAndView test(HttpServletRequest request){
		  ModelAndView mav = new ModelAndView();
		  System.out.println("<<====开始===>>");
		  System.out.println("你已经进入了Controller层！！");
		  
		  User user = userService.getUserById(1);  
		  logger.info(222);
		  logger.info(JSON.toJSONString(user));
		  System.out.println("success");
		  
		  Map<String,Object> map = new HashMap<String, Object>();
		  map.put("a", 1);
		  mav.setViewName("404");
		  mav.addObject(map);
		  
		  System.out.println("<<====结束===>>");
		  return mav;
		  
	  }
}
