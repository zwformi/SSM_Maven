package com.zw.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zw.util.GetImageBase64;
import com.zw.util.WordGenerator;

@Controller
@RequestMapping("/file")
public class OrderDownController {
	private static final long serialVersionUID = 1L; 
	
	@RequestMapping(value = "/dowmLoadOrd", method = RequestMethod.POST)
	public void downLoad(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		    request.setCharacterEncoding("utf-8");
	        Map<String, Object> map = new HashMap<String, Object>();  
	        Enumeration<String> paramNames = request.getParameterNames();  
	        // ͨ��ѭ���������������ֵ��ӳ����  
	        while(paramNames.hasMoreElements()) {  
	            String key = paramNames.nextElement();  
	            String value = request.getParameter(key);  
	            map.put(key, value);  
	        }  
	      
	        String filename = "C:\\Users\\Administrator\\Desktop\\ͼƬ\\swk2.png";
	        String base64String = GetImageBase64.getImageString(filename);
	        map.put("photo", base64String);
	        // ��ʾ���ڵ��ù���������Word�ĵ�֮ǰӦ����������ֶ��Ƿ�����  
	        // ����Freemarker��ģ�������ڴ���ʱ���ܻ���Ϊ�Ҳ���ֵ������ ������ʱ�������������  
	        File file = null;  
	        InputStream fin = null;  
	        ServletOutputStream out = null;  
	        try {  
	            // ���ù�����WordGenerator��createDoc��������Word�ĵ�   
	            file = WordGenerator.createDoc(map, "resume1");  
	            fin = new FileInputStream(file);  
	              
	            response.setCharacterEncoding("utf-8");  
	            response.setContentType("application/msword");  
	            // ��������������صķ�ʽ������ļ�Ĭ����Ϊresume.doc  
	            response.addHeader("Content-Disposition", "attachment;filename=resume.doc");  
	              
	            out = response.getOutputStream();  
	            byte[] buffer = new byte[512];  // ������  
	            int bytesToRead = -1;  
	            // ͨ��ѭ���������Word�ļ�������������������  
	            while((bytesToRead = fin.read(buffer)) != -1) {  
	                out.write(buffer, 0, bytesToRead);  
	            }  
	        } finally {  
	            if(fin != null) fin.close();  
	            if(out != null) out.close();  
	            if(file != null) file.delete(); // ɾ����ʱ�ļ�  
	        }  
		
		
	}
	
	
	
}
