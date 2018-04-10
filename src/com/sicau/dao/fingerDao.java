package com.sicau.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.sicau.jdbc.domain.jdbcFinger;
import com.sicau.utils.JDBCUtils;

public class fingerDao {
	/**
	 * FingerDao对fingerprint_info表进行操作
	 * 方法：
	 * 1、insert(FingerprintInfo fingerInfo)		添加指纹信息，返回boolean值 
	 * 2、findAll()								查询所有指纹信息，返回ArrayList<FingerprintInfo>
	 * 3、findByUserId(String user_id) 			查询给定用户id的指纹信息，返回ArrayList<FingerprintInfo>
	 * 4、 findById(int id)						根据图片id查询指纹信息，返回FingerprintInfo
	 * 5、deleteByUserId(String user_id)			根据用户id删除指纹信息，返回boolean值
	 * 6、deleteById(int id)						根据图片id删除图片信息，返回boolean值 
	 * 7、update(FingerprintInfo fingerInfo)		修改指纹信息，返回boolean值 
	 *
	 */

		//添加指纹信息
		public boolean insert(jdbcFinger fingerInfo){
			Connection conn=null;
			Statement stm=null;
			ResultSet rs=null;

			try {
				//获得数据库的连接
				conn=JDBCUtils.getConnection();
				//获得Statement对象
				stm=(Statement) conn.createStatement();


				//发送sql语句
				String sql="insert into fingerprint_info(user_id,fingerprintImg_url,fingerprint_flagvalue) " +
						"values('"+
						fingerInfo.getUser_id()+ 
						"','"+
						fingerInfo.getFingerprintImge_url()+
						"','"+
						fingerInfo.getFingerprint_flagvalue()+
						"')";
				int num=stm.executeUpdate(sql);

				if(num>0){
					return true;
				}
				return false;



			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				JDBCUtils.release(rs, stm, conn);
			}

			return false;

		}

		//查询所有指纹部图片信息
		public ArrayList<jdbcFinger>findAll(){
			Connection conn=null;
			Statement stm=null;
			ResultSet rs=null;
			ArrayList<jdbcFinger>list=new ArrayList<jdbcFinger>();

			try {

				//获得数据连接
				conn=JDBCUtils.getConnection();
				//获得statement对象
				stm=(Statement) conn.createStatement();
				//发送sql语句
				String sql="select * from fingerprint_info";
				rs=stm.executeQuery(sql);

				//处理结果集
				while(rs.next()){
					jdbcFinger fingerInfo=new jdbcFinger();
					fingerInfo.setId(rs.getInt("id"));
					fingerInfo.setUser_id(rs.getString("user_id"));
					fingerInfo.setFingerprintImge_url(rs.getString("fingerprintImg_url"));
					fingerInfo.setFingerprint_flagvalue(rs.getString("fingerprint_flagvalue"));
					list.add(fingerInfo);
				}
				return list;


			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				JDBCUtils.release(rs,stm, conn);
			}

			return null;

		}


		//根据user_id查询指定user的脸部图片
		public ArrayList<jdbcFinger>findByUserId(String user_id){

			Connection conn=null;
			Statement stm=null;
			ResultSet rs=null;
			ArrayList<jdbcFinger>list=new ArrayList<jdbcFinger>();
			try {

				//获得数据连接
				conn=JDBCUtils.getConnection();
				//获得statement对象
				stm=(Statement) conn.createStatement();
				//发送sql语句
				String sql="select * from fingerprint_info where user_id="+user_id;
				rs=stm.executeQuery(sql);

				//处理结果集
				while(rs.next()){
					jdbcFinger fingerInfo=new jdbcFinger();
					fingerInfo.setId(rs.getInt("id"));
					fingerInfo.setUser_id(rs.getString("user_id"));
					fingerInfo.setFingerprintImge_url(rs.getString("fingerprintImg_url"));
					fingerInfo.setFingerprint_flagvalue(rs.getString("fingerprint_flagvalue"));
					list.add(fingerInfo);
				}
				return list;


			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				JDBCUtils.release(rs,stm, conn);
			}

			return null;


		}

		//根据图片id查询图片信息
		public jdbcFinger findById(int id){

			Connection conn=null;
			Statement stm=null;
			ResultSet rs=null;
			try {

				//获得数据连接
				conn=JDBCUtils.getConnection();
				//获得statement对象
				stm=(Statement) conn.createStatement();
				//发送sql语句
				String sql="select * from fingerprint_info where id="+id;
				rs=stm.executeQuery(sql);

				//处理结果集
				while(rs.next()){
					jdbcFinger fingerInfo=new jdbcFinger();
					fingerInfo.setId(rs.getInt("id"));
					fingerInfo.setUser_id(rs.getString("user_id"));
					fingerInfo.setFingerprintImge_url(rs.getString("fingerprintImg_url"));
					fingerInfo.setFingerprint_flagvalue(rs.getString("fingerprint_flagvalue"));
					return fingerInfo;
				}
				return null;


			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				JDBCUtils.release(rs,stm, conn);
			}

			return null;


		}

		//根据用户user_id删除用户图片信息
		public boolean deleteByUserId(String user_id){
			Connection conn=null;
			Statement stm=null;
			ResultSet rs=null;

			try {

				//获得数据连接
				conn=JDBCUtils.getConnection();
				//获得statement对象
				stm=(Statement) conn.createStatement();
				//发送sql语句
				String sql="delete from fingerprint_info where user_id="+user_id;
				int num=stm.executeUpdate(sql);
				if(num>0){
					return true;
				}
				return false;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				JDBCUtils.release(rs,stm, conn);
			}
			return false;

		}

		//根据图片id删除图片信息
		public boolean deleteById(int id){
			Connection conn=null;
			Statement stm=null;
			ResultSet rs=null;

			try {

				//获得数据连接
				conn=JDBCUtils.getConnection();
				//获得statement对象
				stm=(Statement) conn.createStatement();
				//发送sql语句
				String sql="delete from fingerprint_info where id="+id;
				int num=stm.executeUpdate(sql);
				if(num>0){
					return true;
				}
				return false;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				JDBCUtils.release(rs,stm, conn);
			}
			return false;

		}


		//修改指纹图片信息
		public boolean update(jdbcFinger fingerInfo){

			Connection conn=null;
			Statement stm=null;
			ResultSet rs=null;

			try {
				//获得数据库的连接
				conn=JDBCUtils.getConnection();
				//获得Statement对象
				stm=(Statement) conn.createStatement();

				//发送sql语句
				String sql="update face_info set " +
						"user_id= '" + fingerInfo.getUser_id()+
						"',fingerprintImg_url='"+fingerInfo.getFingerprintImge_url() +
						"',fingerprint_flagvalue='"+fingerInfo.getFingerprint_flagvalue() +
						"'where id="+fingerInfo.getId();

				int num=stm.executeUpdate(sql);

				if(num>0){
					return true;
				}
				return false;



			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				JDBCUtils.release(rs, stm, conn);
			}

			return false;

		}
}
