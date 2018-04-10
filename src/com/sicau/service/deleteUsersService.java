package com.sicau.service;

import com.sicau.dao.usersDao;

public class deleteUsersService {
	public boolean deleteUserInfoById(String id){
		
		usersDao usd = new usersDao();
		boolean result = usd.deleteAllInfo(id);
		System.out.println(result);

		return result;
	}
}
