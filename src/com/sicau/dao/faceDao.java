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
	 * FaceDao��face_info��Ĳ���
	 * ������
	 * 1��insert(FaceInfo faceInfo)			�������ͼƬ��Ϣ������booleanֵ 
	 * 2��findAll()							��ѯ��������ͼƬ����Ϣ������ArrayList<FaceInfo>
	 * 3��findByUserId(String user_id)		�����û�id��ѯͼƬ��Ϣ������ArrayList<FaceInfo>
	 * 4��findById(int id)					����ͼƬid��ѯͼƬ��Ϣ������FaceInfo 
	 * 5��deleteByUserId(String user_id)		�����û�idɾ��������booleanֵ 
	 * 6��deleteById(int id)					����ͼƬidɾ��������booleanֵ
	 * 7��update(FaceInfo faceInfo)			����ͼƬ����Ϣ������booleanֵ 
	 *
	 */

		//���������Ϣ�Ĳ���
		public boolean insert(jdbcFace faceInfo){
			Connection conn=null;
			Statement stm=null;
			ResultSet rs=null;

			try {
				//������ݿ������
				conn=JDBCUtils.getConnection();
				//���Statement����
				stm=(Statement) conn.createStatement();


				//����sql���
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

		//��ѯ��������ͼƬ��ַ
		public ArrayList<jdbcFace>findAll(){
			Connection conn=null;
			Statement stm=null;
			ResultSet rs=null;
			ArrayList<jdbcFace>list=new ArrayList<jdbcFace>();

			try {

				//�����������
				conn=JDBCUtils.getConnection();
				//���statement����
				stm=(Statement) conn.createStatement();
				//����sql���
				String sql="select * from face_info";
				rs=stm.executeQuery(sql);

				//��������
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


		//����user_id��ѯָ��user������ͼƬ
		public ArrayList<jdbcFace>findByUserId(String user_id){

			Connection conn=null;
			Statement stm=null;
			ResultSet rs=null;
			ArrayList<jdbcFace>list=new ArrayList<jdbcFace>();
			try {

				//�����������
				conn=JDBCUtils.getConnection();
				//���statement����
				stm=(Statement) conn.createStatement();
				//����sql���
				String sql="select * from face_info where user_id="+user_id;
				rs=stm.executeQuery(sql);

				//��������
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

		//����ͼƬid��ѯͼƬ��Ϣ
		public jdbcFace findById(int id){

			Connection conn=null;
			Statement stm=null;
			ResultSet rs=null;
			try {

				//�����������
				conn=JDBCUtils.getConnection();
				//���statement����
				stm=(Statement) conn.createStatement();
				//����sql���
				String sql="select * from face_info where id="+id;
				rs=stm.executeQuery(sql);

				//��������
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

		//�����û�user_idɾ���û�ͼƬ��Ϣ
		public boolean deleteByUserId(String user_id){
			Connection conn=null;
			Statement stm=null;
			ResultSet rs=null;

			try {

				//�����������
				conn=JDBCUtils.getConnection();
				//���statement����
				stm=(Statement) conn.createStatement();
				//����sql���
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

		//����ͼƬidɾ��ͼƬ��Ϣ
		public boolean deleteById(int id){
			Connection conn=null;
			Statement stm=null;
			ResultSet rs=null;

			try {

				//�����������
				conn=JDBCUtils.getConnection();
				//���statement����
				stm=(Statement) conn.createStatement();
				//����sql���
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


		//�޸�����ͼƬ��Ϣ
		public boolean update(jdbcFace faceInfo){

			Connection conn=null;
			Statement stm=null;
			ResultSet rs=null;

			try {
				//������ݿ������
				conn=JDBCUtils.getConnection();
				//���Statement����
				stm=(Statement) conn.createStatement();

				//����sql���
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
