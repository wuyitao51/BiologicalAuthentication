package com.sicau.jdbc.domain;

public class jdbcUsers {
	String id;
	String password;
	String name;
	String tel;
	String email;
	String sex;
	int age;
	int permission_num;
	String fingerprint_mix;
	
	public jdbcUsers(){
		
	}
	
	public jdbcUsers(String id, String password, String name, String tel,
			String email, String sex, int age, int permission_num,
			String fingerprint_mix) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.tel = tel;
		this.email = email;
		this.sex = sex;
		this.age = age;
		this.permission_num = permission_num;
		this.fingerprint_mix = fingerprint_mix;
	}

	
	@Override
	public String toString() {
		return "jdbcUsers [id=" + id + ", password=" + password + ", name="
				+ name + ", tel=" + tel + ", email=" + email + ", sex=" + sex
				+ ", age=" + age + ", permission_num=" + permission_num
				+ ", fingerprint_mix=" + fingerprint_mix + "]";
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String c) {
		this.sex = c;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getPermission_num() {
		return permission_num;
	}
	public void setPermission_num(int permission_num) {
		this.permission_num = permission_num;
	}
	public String getFingerprint_mix() {
		return fingerprint_mix;
	}
	public void setFingerprint_mix(String fingerprint_mix) {
		this.fingerprint_mix = fingerprint_mix;
	}
	
}
