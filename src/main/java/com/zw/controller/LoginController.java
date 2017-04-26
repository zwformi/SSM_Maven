package com.zw.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zw.annotation.SystemControllerLog;
import com.zw.dao.UserDao;
import com.zw.pojo.User;

@Controller
public class LoginController {
	@Resource
	private UserDao userdao;
	@RequestMapping(value="/login",method = RequestMethod.POST)
	@SystemControllerLog(description = "”√ªßµ«¬º")
	 public String user(HttpServletRequest request,Model model){
		  
		String name = request.getParameter("name");
		         String paw = request.getParameter("paw");
		         if (name == null || name == "") {
		             model.addAttribute("msg", "’À∫≈Œ™ø’");
		             return "login";
		         }
		         User user = userdao.selectByName(name);
		         if(user!=null){
		        	 if (!user.getPassword().equals(paw)) {
			             model.addAttribute("msg", "√‹¬Î¥ÌŒÛ");
			             return "login";
			         }			        
		         }
		         else{
		        	 model.addAttribute("msg", "’À∫≈≤ª¥Ê‘⁄");
		             return "login";
		         }
		         model.addAttribute("user", user);
		         request.getSession().setAttribute("user", user);
		         System.out.println(request.getSession().getAttribute("user").toString());
		         return "index";
		     }		
	
	@RequestMapping("/logout")
	public String toLogin(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/login.jsp";
	}
	
}
