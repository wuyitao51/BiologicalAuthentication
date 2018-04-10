package com.sicau.service;

import com.sicau.dao.faceDao;
import com.sicau.dao.fingerDao;
import com.sicau.dao.usersDao;
import com.sicau.javabean.Users;
import com.sicau.jdbc.domain.jdbcFace;
import com.sicau.jdbc.domain.jdbcFinger;
import com.sicau.jdbc.domain.jdbcUsers;


public class registerService {
		//保存用户信息
		public boolean saveUserInfo(Users u){
			//先插入用户表
			usersDao usDao = new usersDao();
			jdbcUsers jUser  = new jdbcUsers();
			
			jUser.setId(u.getId());
			jUser.setPassword(u.getPassword());
			jUser.setPermission_num(u.getuPermission());
			jUser.setName(u.getuName());
			jUser.setSex(u.getuSex());
			jUser.setAge(u.getuAge());
			jUser.setEmail(u.getuEmail());
			jUser.setTel(u.getuTel());
			jUser.setFingerprint_mix(u.getFingerInfo().toString());
			
			boolean Result_user = usDao.insert(jUser);
			
			//插入面部表
			faceDao fcDao = new faceDao();
			
			jdbcFace jFace1 = new jdbcFace();
			jFace1.setUser_id(u.getId());
			jFace1.setFaceImg_url(u.getFace1().getF_Img());
			boolean Result_face1 = fcDao.insert(jFace1);
			
			jdbcFace jFace2 = new jdbcFace();
			jFace2.setUser_id(u.getId());
			jFace2.setFaceImg_url(u.getFace2().getF_Img());
			boolean Result_face2 = fcDao.insert(jFace2);
			
			jdbcFace jFace3 = new jdbcFace();
			jFace3.setUser_id(u.getId());
			jFace3.setFaceImg_url(u.getFace3().getF_Img());
			boolean Result_face3 = fcDao.insert(jFace3);
			
			jdbcFace jFace4 = new jdbcFace();
			jFace4.setUser_id(u.getId());
			jFace4.setFaceImg_url(u.getFace4().getF_Img());
			boolean Result_face4 = fcDao.insert(jFace4);
			
			jdbcFace jFace5 = new jdbcFace();
			jFace5.setUser_id(u.getId());
			jFace5.setFaceImg_url(u.getFace5().getF_Img());
			boolean Result_face5 = fcDao.insert(jFace5);
			
			//插入指纹表;
			fingerDao fgDao = new fingerDao();
			
			jdbcFinger jFin1 = new jdbcFinger();
			jFin1.setUser_id(u.getId());
			jFin1.setFingerprintImge_url(u.getFinger1().getFinger_img());
			jFin1.setFingerprint_flagvalue(u.getFinger1().getFinger_info().toString());
			boolean Result_finger1 = fgDao.insert(jFin1);
			
			jdbcFinger jFin2 = new jdbcFinger();
			jFin2.setUser_id(u.getId());
			jFin2.setFingerprintImge_url(u.getFinger2().getFinger_img());
			jFin2.setFingerprint_flagvalue(u.getFinger2().getFinger_info().toString());
			boolean Result_finger2 = fgDao.insert(jFin2);
			
			jdbcFinger jFin3 = new jdbcFinger();
			jFin3.setUser_id(u.getId());
			jFin3.setFingerprintImge_url(u.getFinger3().getFinger_img());
			jFin3.setFingerprint_flagvalue(u.getFinger3().getFinger_info().toString());
			boolean Result_finger3 = fgDao.insert(jFin3);
			 
			//全部插入成功结果才成功
			return (Result_user && Result_face1 && Result_face2 && Result_face3 && Result_face4 && Result_face5
					     && Result_finger1 && Result_finger2 && Result_finger3);
		}
}
