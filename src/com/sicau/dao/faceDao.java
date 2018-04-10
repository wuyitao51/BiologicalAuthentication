package com.sicau.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.sicau.jdbc.domain.jdbcFace;
import com.sicau.utils.JDBCUtils;

public class faceDao {
	/**
	 * FaceDao对face_info表的操作
	 * 方法：
	 * 1、insert(FaceInfo faceInfo)			添加脸部图片信息，返回boolean值 
	 * 2、findAll()							查询所有脸部图片的信息，返回ArrayList<FaceInfo>
	 * 3、findByUserId(String user_id)		根据用户id查询图片信息，返回ArrayList<FaceInfo>
	 * 4、findById(int id)					根据图片id查询图片信息，返回FaceInfo 
	 * 5、deleteByUserId(String user_id)		根据用户id删除，返回boolean值 
	 * 6、deleteById(int id)					根据图片id删除，返回boolean值
	 * 7、update(FaceInfo faceInfo)			更新图片的信息，返回boolean值 
	 *
	 */

		//添加脸部信息的操作
		public boolean insert(jdbcFace faceInfo){
			Connection conn=null;
			Statement stm=null;
			ResultSet rs=null;

			try {
				//获得数据库的连接
				conn=JDBCUtils.getConnection();
				//获得Statement对象
				stm=(Statement) conn.createStatement();


				//发送sql语句
				String sql="insert into face_info(user_id,faceImg_url) " +
						"values('"+
						faceInfo.getUser_id()+ 
						"','"+
						faceInfo.getFaceImg_url()+"')";
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

		//查询所有脸部图片地址
		public ArrayList<jdbcFace>findAll(){
			Connection conn=null;
			Statement stm=null;
			ResultSet rs=null;
			ArrayList<jdbcFace>list=new ArrayList<jdbcFace>();

			try {

				//获得数据连接
				conn=JDBCUtils.getConnection();
				//获得statement对象
				stm=(Statement) conn.createStatement();
				//发送sql语句
				String sql="select * from face_info";
				rs=stm.executeQuery(sql);

				//处理结果集
				while(rs.next()){
					jdbcFace faceInfo=new jdbcFace();
					faceInfo.setId(rs.getInt("id"));
					faceInfo.setUser_id(rs.getString("user_id"));
					faceInfo.setFaceImg_url(rs.getString("faceImg_url"));
					list.add(faceInfo);
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
		public ArrayList<jdbcFace>findByUserId(String user_id){

			Connection conn=null;
			Statement stm=null;
			ResultSet rs=null;
			ArrayList<jdbcFace>list=new ArrayList<jdbcFace>();
			try {

				//获得数据连接
				conn=JDBCUtils.getConnection();
				//获得statement对象
				stm=(Statement) conn.createStatement();
				//发送sql语句
				String sql="select * from face_info where user_id="+user_id;
				rs=stm.executeQuery(sql);

				//处理结果集
				while(rs.next()){
					jdbcFace faceInfo=new jdbcFace();
					faceInfo.setId(rs.getInt("id"));
					faceInfo.setUser_id(rs.getString("user_id"));
					faceInfo.setFaceImg_url(rs.getString("faceImg_url"));
					list.add(faceInfo);
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
		public jdbcFace findById(int id){

			Connection conn=null;
			Statement stm=null;
			ResultSet rs=null;
			try {

				//获得数据连接
				conn=JDBCUtils.getConnection();
				//获得statement对象
				stm=(Statement) conn.createStatement();
				//发送sql语句
				String sql="select * from face_info where id="+id;
				rs=stm.executeQuery(sql);

				//处理结果集
				while(rs.next()){
					jdbcFace faceInfo=new jdbcFace();
					faceInfo.setId(rs.getInt("id"));
					faceInfo.setUser_id(rs.getString("user_id"));
					faceInfo.setFaceImg_url(rs.getString("faceImg_url"));
					return faceInfo;
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
				String sql="delete from face_info where user_id="+user_id;
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
				String sql="delete from face_info where id="+id;
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


		//修改脸部图片信息
		public boolean update(jdbcFace faceInfo){

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
						"user_id= '" + faceInfo.getUser_id()+
						"',faceImg_url='"+faceInfo.getFaceImg_url() +
						"'where id="+faceInfo.getId();

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
