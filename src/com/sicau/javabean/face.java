package com.sicau.javabean;

public class face {
		private String f_Id;
		private String f_Img;//Í¼Æ¬Â·¾¶
		public face(){
			
		}
		public face(String f_Id, String f_Img) {
			super();
			this.f_Id = f_Id;
			this.f_Img = f_Img;
		}
		public String getF_Id() {
			return f_Id;
		}
		public void setF_Id(String f_Id) {
			this.f_Id = f_Id;
		}
		public String getF_Img() {
			return f_Img;
		}
		public void setF_Img(String f_Img) {
			this.f_Img = f_Img;
		}

}
