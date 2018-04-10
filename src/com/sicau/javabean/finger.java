package com.sicau.javabean;

public class finger {
	private String finger_Id;	
	private String finger_img;//指纹图片路径
	private byte[] finger_info;//指纹图片特征值
	
	public finger(){
		
	}

	public finger(String finger_Id, String finger_img, byte[] finger_info) {
		super();
		this.finger_Id = finger_Id;
		this.finger_img = finger_img;
		this.finger_info = finger_info;
	}

	public String getFinger_Id() {
		return finger_Id;
	}

	public void setFinger_Id(String finger_Id) {
		this.finger_Id = finger_Id;
	}

	public String getFinger_img() {
		return finger_img;
	}

	public void setFinger_img(String finger_img) {
		this.finger_img = finger_img;
	}

	public byte[] getFinger_info() {
		return finger_info;
	}

	public void setFinger_info(byte[] finger_info) {
		this.finger_info = finger_info;
	}
	
}
