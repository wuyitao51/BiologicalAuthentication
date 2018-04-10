package com.sicau.service;

import com.sicau.dao.usersDao;
import com.sicau.jdbc.domain.jdbcUsers;

public class loginService {
	//根据账号密码	得出比对结果
	public boolean getResult(String id , String pwd){
			usersDao usDao = new usersDao();
			jdbcUsers u = new jdbcUsers();
			u = usDao.find(id);
			if(u == null){
				//账号不存在
				System.out.println("账号不存在");
				return false;
			}else{
				if(!pwd.equals(u.getPassword())){
					//密码不正确
					System.out.println("密码不正确");
					System.out.println("输入："+pwd);
					System.out.println("正确："+u.getPassword());
					return false;
					
				}else{
					System.out.println("验证通过");
					return true;
				}
			}
		}
	
}
