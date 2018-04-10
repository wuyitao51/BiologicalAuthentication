package com.sicau.jdbc.domain;

public class jdbcFinger {
	int id;
	String user_id;
	String fingerprintImge_url;
	String fingerprint_flagvalue;
	
	public jdbcFinger(){
		
	}
	
		public jdbcFinger(int id, String user_id, String fingerprintImge_url,
			String fingerprint_flagvalue) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.fingerprintImge_url = fingerprintImge_url;
		this.fingerprint_flagvalue = fingerprint_flagvalue;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFingerprintImge_url() {
		return fingerprintImge_url;
	}
	public void setFingerprintImge_url(String fingerprintImge_url) {
		this.fingerprintImge_url = fingerprintImge_url;
	}
	public String getFingerprint_flagvalue() {
		return fingerprint_flagvalue;
	}
	public void setFingerprint_flagvalue(String fingerprint_flagvalue) {
		this.fingerprint_flagvalue = fingerprint_flagvalue;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
}
