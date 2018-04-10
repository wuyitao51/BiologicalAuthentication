package com.sicau.jdbc.domain;

public class jdbcFace {
	
	int id;
	String user_id;
	String faceImg_url;
	
	public jdbcFace(){
		
	}
	
	public jdbcFace(int id, String user_id, String faceImg_url) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.faceImg_url = faceImg_url;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFaceImg_url() {
		return faceImg_url;
	}
	public void setFaceImg_url(String faceImg_url) {
		this.faceImg_url = faceImg_url;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
}
