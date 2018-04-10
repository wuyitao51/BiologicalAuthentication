package com.sicau.javabean;

import java.util.Arrays;

public class Users {
		//认证信息
	    private String id;//用户名 及身份证号 登录用  同时也是用户的ID   唯一   用户表主码   指纹和脸部表外码
		private String password;//密码
		private face face1;//面部信息
		private face face2;
		private face face3;
		private face face4;
		private face face5;
		private finger finger1;//指纹信息
		private finger finger2;
		private finger finger3;
		private byte[] fingerInfo;//指纹综合特征值
		
		//基本信息
		private String uName;//用户姓名
		private String uSex;
		private String uTel;
		private String uEmail;
		private int uAge;
		private int uPermission;//用户的权限值
		
		public Users(){
			
		}

		public Users(String id, String password, face face1, face face2,
				face face3, face face4, face face5, finger finger1,
				finger finger2, finger finger3, byte[] fingerInfo,
				String uName, String uSex, String uTel, String uEmail,
				int uAge, int uPermission) {
			super();
			this.id = id;
			this.password = password;
			this.face1 = face1;
			this.face2 = face2;
			this.face3 = face3;
			this.face4 = face4;
			this.face5 = face5;
			this.finger1 = finger1;
			this.finger2 = finger2;
			this.finger3 = finger3;
			this.fingerInfo = fingerInfo;
			this.uName = uName;
			this.uSex = uSex;
			this.uTel = uTel;
			this.uEmail = uEmail;
			this.uAge = uAge;
			this.uPermission = uPermission;
		}



		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public face getFace1() {
			return face1;
		}

		public void setFace1(face face1) {
			this.face1 = face1;
		}

		public face getFace2() {
			return face2;
		}

		public void setFace2(face face2) {
			this.face2 = face2;
		}

		public face getFace3() {
			return face3;
		}

		public void setFace3(face face3) {
			this.face3 = face3;
		}

		public face getFace4() {
			return face4;
		}

		public void setFace4(face face4) {
			this.face4 = face4;
		}

		public face getFace5() {
			return face5;
		}

		public void setFace5(face face5) {
			this.face5 = face5;
		}

		public finger getFinger1() {
			return finger1;
		}

		public void setFinger1(finger finger1) {
			this.finger1 = finger1;
		}

		public finger getFinger2() {
			return finger2;
		}

		public void setFinger2(finger finger2) {
			this.finger2 = finger2;
		}

		public finger getFinger3() {
			return finger3;
		}

		public void setFinger3(finger finger3) {
			this.finger3 = finger3;
		}

		public byte[] getFingerInfo() {
			return fingerInfo;
		}

		public void setFingerInfo(byte[] fingerInfo) {
			this.fingerInfo = fingerInfo;
		}

		public String getuName() {
			return uName;
		}

		public void setuName(String uName) {
			this.uName = uName;
		}

		public String getuSex() {
			return uSex;
		}

		public void setuSex(String uSex) {
			this.uSex = uSex;
		}

		public String getuTel() {
			return uTel;
		}

		public void setuTel(String uTel) {
			this.uTel = uTel;
		}

		public String getuEmail() {
			return uEmail;
		}

		public void setuEmail(String uEmail) {
			this.uEmail = uEmail;
		}

		public int getuAge() {
			return uAge;
		}

		public void setuAge(int uAge) {
			this.uAge = uAge;
		}

		public int getuPermission() {
			return uPermission;
		}

		public void setuPermission(int uPermission) {
			this.uPermission = uPermission;
		}

		
		
}
