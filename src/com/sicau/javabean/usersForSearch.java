package com.sicau.javabean;

public class usersForSearch {
		private int order;//ÐòºÅ
		private String name;
		private String sex;
		private int age;
		private String id;
		private String tel;
		private String permi;
		private String isFingerExist;
		private String isFaceExist;
		
		public usersForSearch(){
			
		}

		public usersForSearch(int order, String name, String sex, int age,
				String id, String tel, String permi, String isFingerExist,
				String isFaceExist) {
			super();
			this.order = order;
			this.name = name;
			this.sex = sex;
			this.age = age;
			this.id = id;
			this.tel = tel;
			this.permi = permi;
			this.isFingerExist = isFingerExist;
			this.isFaceExist = isFaceExist;
		}

		public int getOrder() {
			return order;
		}

		public void setOrder(int order) {
			this.order = order;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSex() {
			return sex;
		}

		public void setSex(String sex) {
			this.sex = sex;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getTel() {
			return tel;
		}

		public void setTel(String tel) {
			this.tel = tel;
		}

		public String getPermi() {
			return permi;
		}

		public void setPermi(String permi) {
			this.permi = permi;
		}

		public String getIsFingerExist() {
			return isFingerExist;
		}

		public void setIsFingerExist(String isFingerExist) {
			this.isFingerExist = isFingerExist;
		}

		public String getIsFaceExist() {
			return isFaceExist;
		}

		public void setIsFaceExist(String isFaceExist) {
			this.isFaceExist = isFaceExist;
		}

		@Override
		public String toString() {
			return "usersForSearch [order=" + order + ", name=" + name
					+ ", sex=" + sex + ", age=" + age + ", id=" + id + ", tel="
					+ tel + ", permi=" + permi + ", isFingerExist="
					+ isFingerExist + ", isFaceExist=" + isFaceExist + "]";
		}
		
}
