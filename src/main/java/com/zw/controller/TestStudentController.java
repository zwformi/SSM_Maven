package com.zw.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alibaba.fastjson.JSON;
import com.zw.pojo.User;
import com.zw.service.IUserService;


@Controller
@RequestMapping("/user")
public class TestStudentController {
	@Resource
	private IUserService userService = null; 
	private static Logger logger = Logger.getLogger(TestStudentController.class);
	
	@RequestMapping("/showUser")
	 public String user(HttpServletRequest request,Model model){

		  System.out.println("<<====开始===>>");
		  
		  int userId = Integer.parseInt(request.getParameter("id"));
		  User user = userService.getUserById(userId);  
		  logger.info(JSON.toJSONString(user));	
		  model.addAttribute("user", user);		
		  
		  System.out.println("<<====结束===>>");
		  return "showUser";
		  
	  }
	
	@RequestMapping("/showAllUser")
	 public String allUser(HttpServletRequest request,Model model){

		  System.out.println("<<====开始===>>");
		  
		  List<User> lUser = userService.selectAllStudent(); 
		  for(User user:lUser){
				logger.info("值："+user.getUserName());
				logger.info(JSON.toJSONString(user));
			}
		  model.addAttribute("userList", lUser);		
		  
		  System.out.println("<<====结束===>>");
		  return "showUser";
		  
	  }
}
