package com.zw.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zw.pojo.User;
import com.zw.service.IUserService;
import com.zw.util.ExportExcel;


@Controller
@RequestMapping("/user")
public class TestStudentController {
	@Resource
	private IUserService userService = null; 
	private static Logger logger = Logger.getLogger(TestStudentController.class);
	
	@RequestMapping("/showUser")
	 public String user(HttpServletRequest request,Model model){

		  System.out.println("<<====��ʼ===>>");
		  
		  int userId = Integer.parseInt(request.getParameter("id"));
		  User user = userService.getUserById(userId);  
		 /* logger.info(JSON.toJSONString(user));	*/
		  model.addAttribute("user", user);		
		  
		  System.out.println("<<====����===>>");
		  return "showUser";
		  
	  }
	
	@RequestMapping("/showAllUser")
	@ResponseBody
	 public List<User> allUser(HttpServletRequest request,Model model){

		  System.out.println("<<====��ʼ===>>");
		  
		  List<User> lUser = userService.selectAllStudent(); 
		/*  for(User user:lUser){
				logger.info("ֵ��"+user.getUserName());
				logger.info(JSON.toJSONString(user));
			}*/
		  /*model.addAttribute("userList", lUser);*/		
		  
		  System.out.println("<<====����===>>");
		  /*return "showUser";*/
		  return lUser;
		  
	  }
	
	@RequestMapping(value = "/dowmLoadExl", method = RequestMethod.GET)
	public void downLoad(HttpServletRequest request,HttpServletResponse response)
	{
		 File file = new File("C:\\Users\\Administrator\\Desktop\\ͼƬ\\tx.jpg"); 
	      response.setContentType("octets/stream");
	      response.addHeader("Content-Disposition", "attachment;filename=test.xls");
	      //����ͼ��
	      ExportExcel<User> ex = new ExportExcel<User>();
	      String[] headers = { "�û�id", "�û���", "�û�����","�û�����" };
	      List<User> dataset = new ArrayList<User>();
	      try {
	         BufferedInputStream bis = new BufferedInputStream(
	               new FileInputStream(file));
	         byte[] buf = new byte[bis.available()];
	         while ((bis.read(buf)) != -1) {
	            //��ͼƬ���ݴ�ŵ�����������
	         }
	         dataset = userService.selectAllStudent();
	         OutputStream out = response.getOutputStream();
	         System.out.println(out.toString());
	         ex.exportExcel(headers, dataset, out);
	         out.close();
	         System.out.println("excel�����ɹ���");
	      } catch (FileNotFoundException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      } catch (IOException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
		
	}
}
