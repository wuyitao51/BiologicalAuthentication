package com.sicau.utils;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class JDBCUtils {
	static Connection conn;
	//������������������
	public static Connection getConnection(){
		
		//һ��ʹ�ü���������
		try {
			Class.forName("com.mysql.jdbc.Driver");
		
		
		
		
		//2��ͨ��DriverManager��ȡ���ݿ�����
		String uri="jdbc:mysql://111.230.144.70:3306/520Program";
		String username="root";
		String password="LiuYang2017@";
		conn = (Connection) DriverManager.getConnection(uri,username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
		
	}
	
	//�ر����ݿ����ӣ��ͷ���Դ
	public static void release(Statement stm,Connection conn){
		
		if(stm!=null){
			try {
				stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stm=null;
		}

		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn=null;
		}
		
	}

	public static void release(ResultSet rs,Statement stm,Connection conn){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			rs=null;
		}
		
		release(stm,conn);
	}
}
