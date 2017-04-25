package com.zw.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zw.dao.UserDao;
import com.zw.pojo.User;

@Controller
public class LoginController {
	@Resource
	private UserDao userdao;
	@RequestMapping(value="/login",method = RequestMethod.POST)
	 public String user(HttpServletRequest request,Model model){
		  
		String name = request.getParameter("name");
		         String paw = request.getParameter("paw");
		         if (name == null || name == "") {
		             model.addAttribute("msg", "�˺�Ϊ��");
		             return "login";
		         }
		         User user = userdao.selectByName(name);
		         if(user!=null){
		        	 if (!user.getPassword().equals(paw)) {
			             model.addAttribute("msg", "�������");
			             return "login";
			         }			        
		         }
		         else{
		        	 model.addAttribute("msg", "�˺Ų�����");
		             return "login";
		         }
		         model.addAttribute("user", user);
		         request.getSession().setAttribute("current_user", user);
		         System.out.println(request.getSession().getAttribute("current_user").toString());
		         return "index";
		     }		
	
	@RequestMapping("/toLogin")
	public String toLogin() {
	    return "login";
	}
	
}
