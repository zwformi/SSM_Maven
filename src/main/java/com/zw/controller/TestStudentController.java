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

		  System.out.println("<<====开始===>>");
		  
		  int userId = Integer.parseInt(request.getParameter("id"));
		  User user = userService.getUserById(userId);  
		 /* logger.info(JSON.toJSONString(user));	*/
		  model.addAttribute("user", user);		
		  
		  System.out.println("<<====结束===>>");
		  return "showUser";
		  
	  }
	
	@RequestMapping("/showAllUser")
	@ResponseBody
	 public List<User> allUser(HttpServletRequest request,Model model){

		//  System.out.println("<<====开始===>>");
		  
		  List<User> lUser = userService.selectAllStudent(); 
		/*  for(User user:lUser){
				logger.info("值："+user.getUserName());
				logger.info(JSON.toJSONString(user));
			}*/
		  /*model.addAttribute("userList", lUser);*/		
		  
		//  System.out.println("<<====结束===>>");
		  /*return "showUser";*/
		  return lUser;
		  
	  }
	
	@RequestMapping(value = "/dowmLoadExl", method = RequestMethod.GET)
	public void downLoad(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		 System.out.println(request.getParameter("strZipPath"));
		 File file = new File("C:\\Users\\Administrator\\Desktop\\图片\\swk2.png"); 
	      //测试用户
	      ExportExcel<User> ex = new ExportExcel<User>();
	      String[] headers = { "用户id", "用户名", "用户密码","用户年龄"};
	      List<User> dataset = new ArrayList<User>();
	      FileInputStream in = null;
	      OutputStream outEnd = null;
	      java.io.File fileEnd = null;
	      try {
	         BufferedInputStream bis = new BufferedInputStream(
	               new FileInputStream(file));
	         byte[] buf = new byte[bis.available()];
	         while ((bis.read(buf)) != -1) {
	            //将图片数据存放到缓冲数组中
	         }
	         dataset = userService.selectAllStudent();

	         //得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
	         String savePath = request.getSession().getServletContext().getRealPath("/upload");
	         String fileName = UUID.randomUUID().toString().replace("-", "")+".xls";
	         /*savePath = savePath+"\\"+fileName;*/
	        //创建文件upload目录
	         File uploadfile = new File(savePath);
	         if(!uploadfile.exists()&&!uploadfile.isDirectory()){
	             System.out.println("upload及子目录或子文件不存在！");
	             uploadfile.mkdir();
	         }	        
	         OutputStream out = new FileOutputStream(savePath+"\\"+fileName);
	        // OutputStream out = response.getOutputStream();
	         System.out.println(out.toString());
	         //绘制excle
	         ex.exportExcel(headers, dataset, out);
	        
	         //下载excle
	         fileEnd = new  java.io.File(savePath+"\\"+fileName);
	         //如果文件不存在
	         if(!fileEnd.exists()){
	             request.setAttribute("message", "您要下载的资源已被删除！！"); 
	             try {
					request.getRequestDispatcher("/index.jsp").forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	             return;
	         }
	         //设置响应头，控制浏览器下载该文件
	         response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
	         //读取要下载的文件，保存到文件输入流
	         in = new FileInputStream(savePath+"\\"+fileName);
	         //创建输出流
	         outEnd = response.getOutputStream();
	         //创建缓冲区
	         byte buffer[] = new byte[1024];
	         int len = 0;
	         //循环将输入流中的内容读取到缓冲区当中
	         while((len=in.read(buffer))>0){ 
	             //输出缓冲区的内容到浏览器，实现文件下载
	        	 outEnd.write(buffer, 0, len);
	         }
	         //关闭文件输入流
	         in.close();
	         //关闭输出流
	         outEnd.close();	
	         System.out.println("excel导出成功！");
	      } catch (FileNotFoundException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      } catch (IOException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }finally {  
	            if(in != null) in.close();  
	            if(outEnd != null) outEnd.close();  
	            if(fileEnd != null) fileEnd.delete(); // 删除临时文件  
	        }  
		
	}
}
