package com.sicau.service;

import java.util.ArrayList;

import org.json.JSONObject;

import com.sicau.dao.fingerDao;
import com.sicau.dao.usersDao;
import com.sicau.jdbc.domain.jdbcFinger;
import com.sicau.jdbc.domain.jdbcUsers;
import com.sicau.utils.faceUtils;

public class userAuthenService {
	
	/*
	 * ����ʶ��
	 * �ҵ����ϴ�ͼƬ�����id
	 */
	public String findByFace(String face_img){
		System.out.println(face_img);
		faceUtils face = new faceUtils();
		JSONObject obj = face.face_rec(face_img);//�õ����ص�JSON����;
		System.out.println(obj.toString());
		System.out.println();
			//System.out.println(obj.getString("score"));
		return "---";
	
	}
	
	//��ͨ�û���֤ģʽ--ָ��
	public jdbcUsers fingerCompare(String finger_img){
		/*
		 * ��ָ�Ʊ���ȡ�����ظ���������ͨ�û���id
		 * ͬ��
		 */
		usersDao usDao = new usersDao();
		fingerDao fgDao = new fingerDao();
		//�õ���ָ�Ʊ������е�Ȩ��ֵΪ��ͨ�û����û�����ļ���
		ArrayList<jdbcUsers> uList = usDao.findFullFace(2);
		//�����������
		for(jdbcUsers u : uList){
			//��������Ӧ��ͼƬ����ļ���
			ArrayList<jdbcFinger> fgList = fgDao.findByUserId(u.getId());
			//�ٱ���ͼ�񼯺�
			for(jdbcFinger f : fgList){
				//�õ�ͼ���·��
				String img = f.getFingerprintImge_url();
				/*
				 * ������·���ȶԣ��ȶԳ����
				 */
				boolean result = true;
				if(result){
					return u;//�ȶԳɹ�����u
				}
			}
		}
		//����������û�У�����null
		return null;
	}
	
	//����Ա��¼--ָ��
	public jdbcUsers adminfingerCompare(String finger_img){
		/*
		 * ��ָ�Ʊ���ȡ�����ظ������й���Ա�û���id
		 * ͬ��
		 */
		usersDao usDao = new usersDao();
		fingerDao fgDao = new fingerDao();
		//�õ���ָ�Ʊ������е�Ȩ��ֵΪ����Ա���û�����ļ���
		ArrayList<jdbcUsers> uList = usDao.findFullFace(1);
		//�����������
		for(jdbcUsers u : uList){
			//��������Ӧ��ͼƬ����ļ���
			ArrayList<jdbcFinger> fgList = fgDao.findByUserId(u.getId());
			//�ٱ���ͼ�񼯺�
			for(jdbcFinger f : fgList){
				//�õ�ͼ���·��
				String img = f.getFingerprintImge_url();
				/*
				 * ������·���ȶԣ��ȶԳ����
				 */
				boolean result = true;
				if(result){
					return u;//�ȶԳɹ�����u
				}
			}
		}
		//����������û�У�����null
		return null;
	}
}
