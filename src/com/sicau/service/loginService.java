package com.sicau.service;

import com.sicau.dao.usersDao;
import com.sicau.jdbc.domain.jdbcUsers;

public class loginService {
	//�����˺�����	�ó��ȶԽ��
	public boolean getResult(String id , String pwd){
			usersDao usDao = new usersDao();
			jdbcUsers u = new jdbcUsers();
			u = usDao.find(id);
			if(u == null){
				//�˺Ų�����
				System.out.println("�˺Ų�����");
				return false;
			}else{
				if(!pwd.equals(u.getPassword())){
					//���벻��ȷ
					System.out.println("���벻��ȷ");
					System.out.println("���룺"+pwd);
					System.out.println("��ȷ��"+u.getPassword());
					return false;
					
				}else{
					System.out.println("��֤ͨ��");
					return true;
				}
			}
		}
	
}
