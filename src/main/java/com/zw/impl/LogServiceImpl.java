package com.zw.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zw.dao.LogDao;
import com.zw.pojo.Logxx;
import com.zw.service.LogService;
@Service
public class LogServiceImpl implements LogService{
    @Resource
	private LogDao logdao;
	@Override
	public void addlog(Logxx log) {
		// TODO Auto-generated method stub
		logdao.insert(log);
	}


}
