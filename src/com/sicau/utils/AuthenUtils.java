package com.sicau.utils;

import com.sicau.dao.faceDao;

public class AuthenUtils {
	
	//普通用户认证模式--人脸	
	//public jdbcUsers faceCompare(String face_img){
				/*
				 * 从人脸表中取出不重复的所有普通用户的id
				 * 遍历这些id，将参数路径图片的同这些id对应的所有图片比对
				 * 比对成功返回该用户的信息，不成功继续遍历直到最后返回null
				 */
		//}
	
	//管理员登录--人脸
	//public jdbcUsers adminfaceCompare(String face_img){
		/*
		 * 从人脸表中取出不重复的所有管理员用户的id
		 * 同上
		 */
	//}
	
	//普通用户认证模式--指纹
	//public jdbcUser fingerCompare(String finger_img){
		/*
		 * 从指纹表中取出不重复的所有普通用户的id
		 * 同上
		 */
	//}
	
	//管理员登录--指纹
	//public jdbcUser adminfingerCompare(String finger_img){
		/*
		 * 从指纹表中取出不重复的所有管理员用户的id
		 * 同上
		 */
	//}
}
