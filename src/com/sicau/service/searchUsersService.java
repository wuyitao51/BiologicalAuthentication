package com.sicau.service;

import java.util.ArrayList;

import com.sicau.dao.usersDao;
import com.sicau.javabean.usersForSearch;
import com.sicau.jdbc.domain.jdbcUsers;

public class searchUsersService {
	//���ݹؼ��ַ��ؼ���
	//������usersForSearch�ļ���
	public ArrayList<usersForSearch> searchUsers(String keywords){
		usersDao usDao = new usersDao();
		ArrayList<usersForSearch> ufsList = new ArrayList<usersForSearch>();
		//����˹ؼ���ƥ���jdbcUsers���󼯺�
		ArrayList<jdbcUsers> juList = usDao.selectInfoByName(keywords);
		//��������
		int i = 0;
		for(jdbcUsers u : juList){
			//��jdbcUsers���usersForSearch
			usersForSearch ufs = usDao.selectInfo(u.getId());
			//�������
			i++;
			ufs.setOrder(i);
			//�ӵ�list��
			ufsList.add(ufs);
		}
		return ufsList;
	}
}
