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
	 * FingerDao��fingerprint_info����в���
	 * ������
	 * 1��insert(FingerprintInfo fingerInfo)		���ָ����Ϣ������booleanֵ 
	 * 2��findAll()								��ѯ����ָ����Ϣ������ArrayList<FingerprintInfo>
	 * 3��findByUserId(String user_id) 			��ѯ�����û�id��ָ����Ϣ������ArrayList<FingerprintInfo>
	 * 4�� findById(int id)						����ͼƬid��ѯָ����Ϣ������FingerprintInfo
	 * 5��deleteByUserId(String user_id)			�����û�idɾ��ָ����Ϣ������booleanֵ
	 * 6��deleteById(int id)						����ͼƬidɾ��ͼƬ��Ϣ������booleanֵ 
	 * 7��update(FingerprintInfo fingerInfo)		�޸�ָ����Ϣ������booleanֵ 
	 *
	 */

		//���ָ����Ϣ
		public boolean insert(jdbcFinger fingerInfo){
			Connection conn=null;
			Statement stm=null;
			ResultSet rs=null;

			try {
				//������ݿ������
				conn=JDBCUtils.getConnection();
				//���Statement����
				stm=(Statement) conn.createStatement();


				//����sql���
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

		//��ѯ����ָ�Ʋ�ͼƬ��Ϣ
		public ArrayList<jdbcFinger>findAll(){
			Connection conn=null;
			Statement stm=null;
			ResultSet rs=null;
			ArrayList<jdbcFinger>list=new ArrayList<jdbcFinger>();

			try {

				//�����������
				conn=JDBCUtils.getConnection();
				//���statement����
				stm=(Statement) conn.createStatement();
				//����sql���
				String sql="select * from fingerprint_info";
				rs=stm.executeQuery(sql);

				//��������
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


		//����user_id��ѯָ��user������ͼƬ
		public ArrayList<jdbcFinger>findByUserId(String user_id){

			Connection conn=null;
			Statement stm=null;
			ResultSet rs=null;
			ArrayList<jdbcFinger>list=new ArrayList<jdbcFinger>();
			try {

				//�����������
				conn=JDBCUtils.getConnection();
				//���statement����
				stm=(Statement) conn.createStatement();
				//����sql���
				String sql="select * from fingerprint_info where user_id="+user_id;
				rs=stm.executeQuery(sql);

				//��������
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

		//����ͼƬid��ѯͼƬ��Ϣ
		public jdbcFinger findById(int id){

			Connection conn=null;
			Statement stm=null;
			ResultSet rs=null;
			try {

				//�����������
				conn=JDBCUtils.getConnection();
				//���statement����
				stm=(Statement) conn.createStatement();
				//����sql���
				String sql="select * from fingerprint_info where id="+id;
				rs=stm.executeQuery(sql);

				//��������
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


		//�޸�ָ��ͼƬ��Ϣ
		public boolean update(jdbcFinger fingerInfo){

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
