package com.sicau.service;

import java.util.UUID;

import com.sicau.dao.faceDao;
import com.sicau.dao.fingerDao;
import com.sicau.jdbc.domain.jdbcFace;
import com.sicau.jdbc.domain.jdbcFinger;

public class doAddAuthenInfoService {
	public boolean fingerInsert(String id,String path){
		/*
		 * ������Ҫ�õ�����ֵ
		 * ��ʱָ��һ��
		 */
		String flagValue = "xsdsdhkasdhwjcbssaad";//����ֵ
		//UUID MyUuid = UUID.randomUUID();
		//String fgId = MyUuid.replaceAll("-", "");//ָ����Ϣ��id
		jdbcFinger fg = new jdbcFinger();
		fg.setUser_id(id);
		//fg.setId();
		fg.setFingerprintImge_url(path);
		fg.setFingerprint_flagvalue(flagValue);
		fingerDao fgDao = new fingerDao();
		boolean result = fgDao.insert(fg);
		return result;
	}
	
	public boolean faceInsert(String id ,String path){
		jdbcFace fc = new jdbcFace();
		fc.setUser_id(id);
		fc.setFaceImg_url(path);
		faceDao fcDao = new faceDao();
		return fcDao.insert(fc);
	}
}
