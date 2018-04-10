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
	 * 人脸识别
	 * 找到与上传图片相符的id
	 */
	public String findByFace(String face_img){
		System.out.println(face_img);
		faceUtils face = new faceUtils();
		JSONObject obj = face.face_rec(face_img);//得到返回的JSON数据;
		System.out.println(obj.toString());
		System.out.println();
			//System.out.println(obj.getString("score"));
		return "---";
	
	}
	
	//普通用户认证模式--指纹
	public jdbcUsers fingerCompare(String finger_img){
		/*
		 * 从指纹表中取出不重复的所有普通用户的id
		 * 同上
		 */
		usersDao usDao = new usersDao();
		fingerDao fgDao = new fingerDao();
		//得到了指纹表中所有的权限值为普通用户的用户对象的集合
		ArrayList<jdbcUsers> uList = usDao.findFullFace(2);
		//遍历这个集合
		for(jdbcUsers u : uList){
			//这个对象对应的图片对象的集合
			ArrayList<jdbcFinger> fgList = fgDao.findByUserId(u.getId());
			//再遍历图像集合
			for(jdbcFinger f : fgList){
				//得到图像的路径
				String img = f.getFingerprintImge_url();
				/*
				 * 对两个路径比对，比对出结果
				 */
				boolean result = true;
				if(result){
					return u;//比对成功返回u
				}
			}
		}
		//遍历下来都没有，返回null
		return null;
	}
	
	//管理员登录--指纹
	public jdbcUsers adminfingerCompare(String finger_img){
		/*
		 * 从指纹表中取出不重复的所有管理员用户的id
		 * 同上
		 */
		usersDao usDao = new usersDao();
		fingerDao fgDao = new fingerDao();
		//得到了指纹表中所有的权限值为管理员的用户对象的集合
		ArrayList<jdbcUsers> uList = usDao.findFullFace(1);
		//遍历这个集合
		for(jdbcUsers u : uList){
			//这个对象对应的图片对象的集合
			ArrayList<jdbcFinger> fgList = fgDao.findByUserId(u.getId());
			//再遍历图像集合
			for(jdbcFinger f : fgList){
				//得到图像的路径
				String img = f.getFingerprintImge_url();
				/*
				 * 对两个路径比对，比对出结果
				 */
				boolean result = true;
				if(result){
					return u;//比对成功返回u
				}
			}
		}
		//遍历下来都没有，返回null
		return null;
	}
}
