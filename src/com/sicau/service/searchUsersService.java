package com.sicau.service;

import java.util.ArrayList;

import com.sicau.dao.usersDao;
import com.sicau.javabean.usersForSearch;
import com.sicau.jdbc.domain.jdbcUsers;

public class searchUsersService {
	//根据关键字返回集合
	//！！是usersForSearch的集合
	public ArrayList<usersForSearch> searchUsers(String keywords){
		usersDao usDao = new usersDao();
		ArrayList<usersForSearch> ufsList = new ArrayList<usersForSearch>();
		//获得了关键字匹配的jdbcUsers对象集合
		ArrayList<jdbcUsers> juList = usDao.selectInfoByName(keywords);
		//遍历集合
		int i = 0;
		for(jdbcUsers u : juList){
			//把jdbcUsers变成usersForSearch
			usersForSearch ufs = usDao.selectInfo(u.getId());
			//添上序号
			i++;
			ufs.setOrder(i);
			//加到list里
			ufsList.add(ufs);
		}
		return ufsList;
	}
}
