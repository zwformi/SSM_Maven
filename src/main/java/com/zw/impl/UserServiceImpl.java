package com.zw.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

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

}
