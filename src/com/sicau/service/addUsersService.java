package com.sicau.service;

import com.sicau.dao.usersDao;
import com.sicau.jdbc.domain.jdbcUsers;

public class addUsersService {
	public boolean saveUserInfo(jdbcUsers u){
		usersDao usDao = new usersDao();
		boolean Result_user = usDao.insert(u);
		return Result_user;
	}
}
