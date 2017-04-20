package com.zw.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zw.dao.UserDao;
import com.zw.pojo.User;
import com.zw.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
	@Resource
	private UserDao userDao;
	
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return this.userDao.selectByPrimaryKey(userId);
	}


}
