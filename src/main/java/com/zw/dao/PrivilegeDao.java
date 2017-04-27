package com.zw.dao;

import java.util.List;

import com.zw.pojo.Privilege;

public interface PrivilegeDao {

	List<Privilege> selectPrivilege(Integer roleid);
}
