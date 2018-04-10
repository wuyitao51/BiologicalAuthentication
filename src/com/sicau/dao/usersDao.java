package com.sicau.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.sicau.javabean.usersForSearch;
import com.sicau.jdbc.domain.jdbcUsers;
import com.sicau.utils.JDBCUtils;

public class usersDao {
	/**
	 * usersDao��user_info����в���
	 * ������
	 * 1��insert(User user)			�����û���Ϣ������booleanֵ
	 * 2�� findAll()					���������û���Ϣ������ArrayList<User>ֵ
	 * 3��find(String id)			�����û�id�����û���Ϣ������User����
	 * 4��delete(String id) 			�����û�idɾ���û���Ϣ������boolean
	 * 5�� update(User user)			�޸��û���Ϣ������boolean
	 * 6��findByPermi(int permit) 	����Ȩ��ֵ�����û���Ϣ������ArrayList<User>
	 * 7��updateNamebyId(String id,String name)     ����id��������
	 * 8��updateAgebyId(String id,int age)     ����id��������
	 * 9��updateSexbyId(String id,String sex)     ����id�����Ա�
	 * 10  updateTelbyId(String id,String tel)     ����id���µ绰
	 * 11. updatePermibyId(String id,int permi)   ����id����Ȩ��
	 *
	 */

		//����û��Ĳ���
		public boolean insert(jdbcUsers user){
			Connection conn=null;
			Statement stm=null;
			ResultSet rs=null;

			try {
				//������ݿ������
				conn=JDBCUtils.getConnection();
				//���Statement����
				stm=(Statement) conn.createStatement();


				//����sql���
				String sql="insert into user_info(id,password,name,tel,email,sex,age,permission_num) " +
						"values('"+
						user.getId()+ 
						"','"+
						user.getPassword() +
						"','" +
						user.getName() +
						"','" +
						user.getTel() +
						"','" +
						user.getEmail() +
						"','" +
						user.getSex() +
						"','" +
						user.getAge() +
						"','" +
						user.getPermission_num()+"')";
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

		//��ѯ����user����
		public ArrayList<jdbcUsers>findAll(){
			Connection conn=null;
			Statement stm=null;
			ResultSet rs=null;
			ArrayList<jdbcUsers>list=new ArrayList<jdbcUsers>();

			try {

				//�����������
				conn=JDBCUtils.getConnection();
				//���statement����
				stm=(Statement) conn.createStatement();
				//����sql���
				String sql="select * from user_info";
				rs=stm.executeQuery(sql);

				//��������
				while(rs.next()){
					jdbcUsers user=new jdbcUsers();
					user.setId(rs.getString("id"));
					user.setPassword(rs.getString("password"));
					user.setName(rs.getString("name"));
					user.setTel(rs.getString("tel"));
					user.setEmail(rs.getString("email"));
					user.setSex(rs.getString("sex"));
					user.setAge(rs.getInt("age"));
					user.setPermission_num(rs.getInt("permission_num"));
					list.add(user);
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


		//����id��ѯָ��user
		public jdbcUsers find(String id){

			Connection conn=null;
			Statement stm=null;
			ResultSet rs=null;

			try {

				//�����������
				conn=JDBCUtils.getConnection();
				//���statement����
				stm=(Statement) conn.createStatement();
				//����sql���
				String sql="select * from user_info where id="+id;
				rs=stm.executeQuery(sql);

				//��������
				while(rs.next()){
					jdbcUsers user=new jdbcUsers();
					user.setId(rs.getString("id"));
					user.setPassword(rs.getString("password"));
					user.setName(rs.getString("name"));
					user.setTel(rs.getString("tel"));
					user.setEmail(rs.getString("email"));
					user.setSex(rs.getString("sex"));
					user.setAge(rs.getInt("age"));
					user.setPermission_num(rs.getInt("permission_num"));
					return user;
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

		//ɾ���û�
		public boolean delete(String id){
			Connection conn=null;
			Statement stm=null;
			ResultSet rs=null;

			try {

				//�����������
				conn=JDBCUtils.getConnection();
				//���statement����
				stm=(Statement) conn.createStatement();
				//����sql���
				String sql="delete from user_info where id="+id;
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


		//�޸��û�
		public boolean update(jdbcUsers user){

			Connection conn=null;
			Statement stm=null;
			ResultSet rs=null;

			try {
				//������ݿ������
				conn=JDBCUtils.getConnection();
				//���Statement����
				stm=(Statement) conn.createStatement();

				//����sql���
				String sql="update user_info set " +
						"password= '" +user.getPassword() +
						"',name='"+user.getName() +
						"',tel='"+user.getTel() +
						"',email='"+user.getEmail()+ 
						",sex= '" +user.getSex() +
						"',age='"+user.getAge() +
						"',permission_num='"+user.getPermission_num() +
						"'where id="+user.getId();

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
		
		//���ָ�ƻ����Ϣ
			public boolean addFingerprint(int id,String fingerprint){
				Connection conn=null;
				Statement stm=null;
				ResultSet rs=null;

				try {

					//�����������
					conn=JDBCUtils.getConnection();
					//���statement����
					stm=(Statement) conn.createStatement();
					//����sql���
					String sql="update user_info set " +
							"fingerprint_mix= '" +fingerprint +
							"'where id="+id;
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
			
			//��ѯ���й���Աuser����
			public ArrayList<jdbcUsers>findByPermi(int permi){
				Connection conn=null;
				Statement stm=null;
				ResultSet rs=null;
				ArrayList<jdbcUsers>list=new ArrayList<jdbcUsers>();

				try {

					//�����������
					conn=JDBCUtils.getConnection();
					//���statement����
					stm=(Statement) conn.createStatement();
					//����sql���
					String sql="select * from user_info where permission_num="+permi;
					rs=stm.executeQuery(sql);

					//��������
					while(rs.next()){
						jdbcUsers user=new jdbcUsers();
						user.setId(rs.getString("id"));
						user.setPassword(rs.getString("password"));
						user.setName(rs.getString("name"));
						user.setTel(rs.getString("tel"));
						user.setEmail(rs.getString("email"));
						user.setSex(rs.getString("sex"));
						user.setAge(rs.getInt("age"));
						user.setPermission_num(rs.getInt("permission_num"));
						user.setFingerprint_mix(rs.getString("fingerprint_mix"));
						list.add(user);
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
			
			//����Ȩ��ֵ����ѯ��¼��ָ����Ϣ�Ķ�ӦȨ���û�
			public ArrayList<jdbcUsers>findFullFinger(int permi){
				Connection conn=null;
				Statement stm=null;
				ResultSet rs=null;
				ArrayList<jdbcUsers>list=new ArrayList<jdbcUsers>();

				try {

					//�����������
					conn=JDBCUtils.getConnection();
					//���statement����
					stm=(Statement) conn.createStatement();
					//����sql���
					String sql="select * from user_info where id in " +
							"(select distinct user_id from fingerprint_info) " +
							"and permission_num="+permi;
					rs=stm.executeQuery(sql);

					//��������
					while(rs.next()){
						jdbcUsers user=new jdbcUsers();
						user.setId(rs.getString("id"));
						user.setPassword(rs.getString("password"));
						user.setName(rs.getString("name"));
						user.setTel(rs.getString("tel"));
						user.setEmail(rs.getString("email"));
						user.setSex(rs.getString("sex"));
						user.setAge(rs.getInt("age"));
						user.setPermission_num(rs.getInt("permission_num"));
						list.add(user);
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

			//����Ȩ��ֵ����ѯ��¼��������Ϣ�Ķ�ӦȨ���û�
			public ArrayList<jdbcUsers>findFullFace(int permi){
				Connection conn=null;
				Statement stm=null;
				ResultSet rs=null;
				ArrayList<jdbcUsers>list=new ArrayList<jdbcUsers>();

				try {

					//�����������
					conn=JDBCUtils.getConnection();
					//���statement����
					stm=(Statement) conn.createStatement();
					//����sql���
					String sql="select * from user_info where id in " +
							"(select distinct user_id from face_info) " +
							"and permission_num="+permi;
					rs=stm.executeQuery(sql);

					//��������
					while(rs.next()){
						jdbcUsers user=new jdbcUsers();
						user.setId(rs.getString("id"));
						user.setPassword(rs.getString("password"));
						user.setName(rs.getString("name"));
						user.setTel(rs.getString("tel"));
						user.setEmail(rs.getString("email"));
						user.setSex(rs.getString("sex"));
						user.setAge(rs.getInt("age"));
						user.setPermission_num(rs.getInt("permission_num"));
						list.add(user);
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
			
			
			//���ݴ���������ֶβ�ѯ�û�
			public ArrayList<jdbcUsers> selectInfoByName(String name){
				Connection conn=null;
				Statement stm=null;
				ResultSet rs=null;
				ArrayList<jdbcUsers> userList=new ArrayList<jdbcUsers>();
				try {	//�����������
					conn=JDBCUtils.getConnection();
					//���statement����
					stm=(Statement) conn.createStatement();
					//����sql���
					String sql="select * from user_info where name like '%"+name+"%'";
					rs=stm.executeQuery(sql);
					while(rs.next()){
						jdbcUsers user=new jdbcUsers();
						user.setId(rs.getString("id"));
						user.setPassword(rs.getString("password"));
						user.setName(rs.getString("name"));
						user.setTel(rs.getString("tel"));
						user.setEmail(rs.getString("email"));
						user.setSex(rs.getString("sex"));
						user.setAge(rs.getInt("age"));
						user.setPermission_num(rs.getInt("permission_num"));
						userList.add(user);
					}
					return userList;
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					JDBCUtils.release(rs,stm, conn);
				}
				return null;
				
			}
			
			//���ݴ����id��ѯ�û��Ƿ�¼���Ӧ����Ϣ
			public usersForSearch selectInfo(String id){
				Connection conn=null;
				Statement stm=null;
				ResultSet rs=null;
				String isFaceExist;
				String isFingerExist;
				usersForSearch us=new usersForSearch();
				try {	//�����������
					conn=JDBCUtils.getConnection();
					//���statement����
					stm=(Statement) conn.createStatement();
					//����sql���
					String sql_1="select * from face_info where user_id="+id;
					rs=stm.executeQuery(sql_1);
					if(rs.next()){
						isFaceExist="��";
					}else{
						isFaceExist="��";
					}
					rs=null;
					String sql_2="select * from fingerprint_info where user_id="+id;
					rs=stm.executeQuery(sql_2);
					if(rs.next()){
						isFingerExist="��";
					}else{
						isFingerExist="��";
					}
					rs=null;
					String sql_3="select * from user_info where id="+id;
					rs=stm.executeQuery(sql_3);
					rs.next();
					us.setId(rs.getString("id"));
					us.setName(rs.getString("name"));
					us.setAge(rs.getInt("age"));
					if(rs.getInt("permission_num")==0){
						us.setPermi("��������Ա");
					}else if(rs.getInt("permission_num")==1){
						us.setPermi("����Ա");
					}else if(rs.getInt("permission_num")==2){
						us.setPermi("��ͨ�û�");
					}
					us.setSex(rs.getString("sex"));
					us.setTel(rs.getString("tel"));
					us.setIsFaceExist(isFaceExist);
					us.setIsFingerExist(isFingerExist);
					return us;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					JDBCUtils.release(rs,stm, conn);
				}
				return null;
				
			}
			
			
			//����id��������
			public boolean updateNamebyId(String id,String name){
				
				Connection conn=null;
				Statement stm=null;
				try {
					//������ݿ������
					conn=JDBCUtils.getConnection();
					//���Statement����
					stm=(Statement) conn.createStatement();
					
					//����sql���
					String sql="update user_info set " +
							"name='"+name +
							"'where id="+id;

					int num=stm.executeUpdate(sql);

					if(num>0){
						return true;
					}
					return false;



				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					JDBCUtils.release( stm, conn);
				}

				return false;
			}
			
			
			//����id��������
			public boolean updateAgebyId(String id,int age){
					
					Connection conn=null;
					Statement stm=null;
					try {
						//������ݿ������
						conn=JDBCUtils.getConnection();
						//���Statement����
						stm=(Statement) conn.createStatement();
						
						//����sql���
						String sql="update user_info set " +
								"age='" +age +
								"'where id="+id;

						int num=stm.executeUpdate(sql);

						if(num>0){
							return true;
						}
						return false;



					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						JDBCUtils.release( stm, conn);
					}

					return false;
				}
				
				
				//����id�����Ա�
				public boolean updateSexbyId(String id,String sex){
					
					Connection conn=null;
					Statement stm=null;
					try {
						//������ݿ������
						conn=JDBCUtils.getConnection();
						//���Statement����
						stm=(Statement) conn.createStatement();
						
						//����sql���
						String sql="update user_info set " +
								"sex= '" +sex +
								"'where id="+id;

						int num=stm.executeUpdate(sql);

						if(num>0){
							return true;
						}
						return false;



					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						JDBCUtils.release( stm, conn);
					}

					return false;
				}
				
				
				//����id���µ绰
				public boolean updateTelbyId(String id,String tel){
					
					Connection conn=null;
					Statement stm=null;
					try {
						//������ݿ������
						conn=JDBCUtils.getConnection();
						//���Statement����
						stm=(Statement) conn.createStatement();
						
						//����sql���
						String sql="update user_info set " +
								"tel='"+tel +
								"'where id="+id;

						int num=stm.executeUpdate(sql);

						if(num>0){
							return true;
						}
						return false;



					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						JDBCUtils.release( stm, conn);
					}

					return false;
				}
				
				//����id����Ȩ��
				public boolean updatePermibyId(String id,int permi){
							
						Connection conn=null;
						Statement stm=null;
						try {
							//������ݿ������
							conn=JDBCUtils.getConnection();
							//���Statement����
							stm=(Statement) conn.createStatement();
								
							//����sql���
							String sql="update user_info set " +
										"permission_num='"+permi +
										"'where id="+id;

							int num=stm.executeUpdate(sql);

							if(num>0){
									return true;
							}
							return false;
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}finally{
								JDBCUtils.release( stm, conn);
							}

							return false;
						}
				
				//����idɾ���û� ��ָͬ�ƺ�������Ϣһ��ɾ��
				public boolean deleteAllInfo(String id){
					Connection conn=null;
					Statement stm=null;
					ResultSet rs=null;

					try {

						//�����������
						conn=JDBCUtils.getConnection();
						//���statement����
						stm=(Statement) conn.createStatement();
						//����sql���
						String sql_1="delete from fingerprint_info where user_id="+id;
						stm.executeUpdate(sql_1);
						
						String sql_2="delete from face_info where user_id="+id;
						stm.executeUpdate(sql_2);
						
						String sql_3="delete from user_info where id="+id;
						int num=stm.executeUpdate(sql_3);
						
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
}
