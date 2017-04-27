package com.zw.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.zw.annotation.PrivilegeInfo;
import com.zw.annotation.SystemServiceLog;
import com.zw.dao.UserDao;
import com.zw.pojo.User;
import com.zw.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
	@Resource 
	private UserDao userDao;
     
	@Override
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return this.userDao.selectByPrimaryKey(userId); 
	}

	@Override
	public List<User> selectAllStudent() { 
		// TODO Auto-generated method stub
		return this.userDao.selectAll();
	}

	@Override	
	@SystemServiceLog(description = "����û�")
	public int insertUser(HttpServletRequest request) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setUserName(request.getParameter("userName"));
		user.setPassword(request.getParameter("password"));
		user.setAge(Integer.valueOf(request.getParameter("age")));
		int rs = this.userDao.insert(user);
		if(user.getUserName().length() < 3 || user.getUserName().length() > 5)  
        {  
            throw new IllegalArgumentException("name�����ĳ��ȱ������3��С��5��");  
        }  
		return rs;
	}

	@Override
	public int updateUser(HttpServletRequest request) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setId(Integer.valueOf(request.getParameter("id")));
		user.setUserName(request.getParameter("userName"));
		user.setPassword(request.getParameter("password"));
		user.setAge(Integer.valueOf(request.getParameter("age")));
		int rs = this.userDao.updateByPrimaryKey(user);
		return rs;
	}

	@Override
	@PrivilegeInfo("delete")
	public int deleteUser(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String idArr = request.getParameter("ids");
		int rs = this.userDao.deleteByIds(idArr.split(","));
		return rs;
	}
	



}
