package com.zw.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

		//  System.out.println("<<====��ʼ===>>");
		  
		  List<User> lUser = userService.selectAllStudent(); 
		/*  for(User user:lUser){
				logger.info("ֵ��"+user.getUserName());
				logger.info(JSON.toJSONString(user));
			}*/
		  /*model.addAttribute("userList", lUser);*/		
		  
		//  System.out.println("<<====����===>>");
		  /*return "showUser";*/
		  return lUser;
		  
	  }
	
	@RequestMapping(value = "/dowmLoadExl", method = RequestMethod.GET)
	public void downLoad(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		 System.out.println(request.getParameter("strZipPath"));
		 File file = new File("C:\\Users\\Administrator\\Desktop\\ͼƬ\\swk2.png"); 
	      //�����û�
	      ExportExcel<User> ex = new ExportExcel<User>();
	      String[] headers = { "�û�id", "�û���", "�û�����","�û�����"};
	      List<User> dataset = new ArrayList<User>();
	      FileInputStream in = null;
	      OutputStream outEnd = null;
	      java.io.File fileEnd = null;
	      try {
	         BufferedInputStream bis = new BufferedInputStream(
	               new FileInputStream(file));
	         byte[] buf = new byte[bis.available()];
	         while ((bis.read(buf)) != -1) {
	            //��ͼƬ���ݴ�ŵ�����������
	         }
	         dataset = userService.selectAllStudent();

	         //�õ��ϴ��ļ��ı���Ŀ¼�����ϴ����ļ������WEB-INFĿ¼�£����������ֱ�ӷ��ʣ���֤�ϴ��ļ��İ�ȫ
	         String savePath = request.getSession().getServletContext().getRealPath("/upload");
	         String fileName = UUID.randomUUID().toString().replace("-", "")+".xls";
	         /*savePath = savePath+"\\"+fileName;*/
	        //�����ļ�uploadĿ¼
	         File uploadfile = new File(savePath);
	         if(!uploadfile.exists()&&!uploadfile.isDirectory()){
	             System.out.println("upload����Ŀ¼�����ļ������ڣ�");
	             uploadfile.mkdir();
	         }	        
	         OutputStream out = new FileOutputStream(savePath+"\\"+fileName);
	        // OutputStream out = response.getOutputStream();
	         System.out.println(out.toString());
	         //����excle
	         ex.exportExcel(headers, dataset, out);
	        
	         //����excle
	         fileEnd = new  java.io.File(savePath+"\\"+fileName);
	         //����ļ�������
	         if(!fileEnd.exists()){
	             request.setAttribute("message", "��Ҫ���ص���Դ�ѱ�ɾ������"); 
	             try {
					request.getRequestDispatcher("/index.jsp").forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	             return;
	         }
	         //������Ӧͷ��������������ظ��ļ�
	         response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
	         //��ȡҪ���ص��ļ������浽�ļ�������
	         in = new FileInputStream(savePath+"\\"+fileName);
	         //���������
	         outEnd = response.getOutputStream();
	         //����������
	         byte buffer[] = new byte[1024];
	         int len = 0;
	         //ѭ�����������е����ݶ�ȡ������������
	         while((len=in.read(buffer))>0){ 
	             //��������������ݵ��������ʵ���ļ�����
	        	 outEnd.write(buffer, 0, len);
	         }
	         //�ر��ļ�������
	         in.close();
	         //�ر������
	         outEnd.close();	
	         System.out.println("excel�����ɹ���");
	      } catch (FileNotFoundException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      } catch (IOException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }finally {  
	            if(in != null) in.close();  
	            if(outEnd != null) outEnd.close();  
	            if(fileEnd != null) fileEnd.delete(); // ɾ����ʱ�ļ�  
	        }  
		
	}
}
