package com.zw.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zw.pojo.User;

public interface IUserService {
	
	User getUserById(int userId);
		
	List<User> selectAllStudent();
	
	int insertUser(HttpServletRequest request);
	
	int updateUser(HttpServletRequest request);
	
	int deleteUser(HttpServletRequest request);
}
