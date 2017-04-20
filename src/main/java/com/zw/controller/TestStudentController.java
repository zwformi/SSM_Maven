package com.zw.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller

public class TestStudentController {
	
	 @RequestMapping(value="/test", method = RequestMethod.GET)
	 @ResponseBody
	 public ModelAndView test(HttpServletRequest request){
		  ModelAndView mav = new ModelAndView();
		  System.out.println("你已经进入了Controller层！！");
		  Map<String,Object> map = new HashMap<String, Object>();
		  map.put("a", 1);
		  map.put("b", "2");
		  mav.setViewName("404");
		  mav.addObject(map);
		  return mav;
		  
	  }
}
