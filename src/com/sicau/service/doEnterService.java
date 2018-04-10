package com.sicau.service;

import java.util.ArrayList;

import com.sicau.dao.usersDao;
import com.sicau.jdbc.domain.jdbcUsers;

public class doEnterService {
		
	public boolean isFirstUser(){
			usersDao usDao = new usersDao();
			ArrayList<jdbcUsers> allUsers = usDao.findAll();
			int len = allUsers.size();
			return len==0;
		}
}
