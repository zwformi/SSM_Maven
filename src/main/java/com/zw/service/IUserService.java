package com.zw.service;

import java.util.List;

import com.zw.pojo.User;

public interface IUserService {
	
	User getUserById(int userId);
	
	List<User> selectAllStudent();
}
