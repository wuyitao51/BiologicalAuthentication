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
	 * usersDao对user_info表进行操作
	 * 方法：
	 * 1、insert(User user)			插入用户信息，返回boolean值
	 * 2、 findAll()					查找所有用户信息，返回ArrayList<User>值
	 * 3、find(String id)			根据用户id查找用户信息，返回User对象
	 * 4、delete(String id) 			根据用户id删除用户信息，返回boolean
	 * 5、 update(User user)			修改用户信息，返回boolean
	 * 6、findByPermi(int permit) 	根据权限值返回用户信息，返回ArrayList<User>
	 * 7、updateNamebyId(String id,String name)     根据id更新姓名
	 * 8、updateAgebyId(String id,int age)     根据id更新年龄
	 * 9、updateSexbyId(String id,String sex)     根据id更新性别
	 * 10  updateTelbyId(String id,String tel)     根据id更新电话
	 * 11. updatePermibyId(String id,int permi)   根据id更新权限
	 *
	 */

		//添加用户的操作
		public boolean insert(jdbcUsers user){
			Connection conn=null;
			Statement stm=null;
			ResultSet rs=null;

			try {
				//获得数据库的连接
				conn=JDBCUtils.getConnection();
				//获得Statement对象
				stm=(Statement) conn.createStatement();


				//发送sql语句
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

		//查询所有user对象
		public ArrayList<jdbcUsers>findAll(){
			Connection conn=null;
			Statement stm=null;
			ResultSet rs=null;
			ArrayList<jdbcUsers>list=new ArrayList<jdbcUsers>();

			try {

				//获得数据连接
				conn=JDBCUtils.getConnection();
				//获得statement对象
				stm=(Statement) conn.createStatement();
				//发送sql语句
				String sql="select * from user_info";
				rs=stm.executeQuery(sql);

				//处理结果集
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


		//根据id查询指定user
		public jdbcUsers find(String id){

			Connection conn=null;
			Statement stm=null;
			ResultSet rs=null;

			try {

				//获得数据连接
				conn=JDBCUtils.getConnection();
				//获得statement对象
				stm=(Statement) conn.createStatement();
				//发送sql语句
				String sql="select * from user_info where id="+id;
				rs=stm.executeQuery(sql);

				//处理结果集
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

		//删除用户
		public boolean delete(String id){
			Connection conn=null;
			Statement stm=null;
			ResultSet rs=null;

			try {

				//获得数据连接
				conn=JDBCUtils.getConnection();
				//获得statement对象
				stm=(Statement) conn.createStatement();
				//发送sql语句
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


		//修改用户
		public boolean update(jdbcUsers user){

			Connection conn=null;
			Statement stm=null;
			ResultSet rs=null;

			try {
				//获得数据库的连接
				conn=JDBCUtils.getConnection();
				//获得Statement对象
				stm=(Statement) conn.createStatement();

				//发送sql语句
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
		
		//添加指纹混合信息
			public boolean addFingerprint(int id,String fingerprint){
				Connection conn=null;
				Statement stm=null;
				ResultSet rs=null;

				try {

					//获得数据连接
					conn=JDBCUtils.getConnection();
					//获得statement对象
					stm=(Statement) conn.createStatement();
					//发送sql语句
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
			
			//查询所有管理员user对象
			public ArrayList<jdbcUsers>findByPermi(int permi){
				Connection conn=null;
				Statement stm=null;
				ResultSet rs=null;
				ArrayList<jdbcUsers>list=new ArrayList<jdbcUsers>();

				try {

					//获得数据连接
					conn=JDBCUtils.getConnection();
					//获得statement对象
					stm=(Statement) conn.createStatement();
					//发送sql语句
					String sql="select * from user_info where permission_num="+permi;
					rs=stm.executeQuery(sql);

					//处理结果集
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
			
			//传入权限值，查询已录入指纹信息的对应权限用户
			public ArrayList<jdbcUsers>findFullFinger(int permi){
				Connection conn=null;
				Statement stm=null;
				ResultSet rs=null;
				ArrayList<jdbcUsers>list=new ArrayList<jdbcUsers>();

				try {

					//获得数据连接
					conn=JDBCUtils.getConnection();
					//获得statement对象
					stm=(Statement) conn.createStatement();
					//发送sql语句
					String sql="select * from user_info where id in " +
							"(select distinct user_id from fingerprint_info) " +
							"and permission_num="+permi;
					rs=stm.executeQuery(sql);

					//处理结果集
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

			//传入权限值，查询已录入脸部信息的对应权限用户
			public ArrayList<jdbcUsers>findFullFace(int permi){
				Connection conn=null;
				Statement stm=null;
				ResultSet rs=null;
				ArrayList<jdbcUsers>list=new ArrayList<jdbcUsers>();

				try {

					//获得数据连接
					conn=JDBCUtils.getConnection();
					//获得statement对象
					stm=(Statement) conn.createStatement();
					//发送sql语句
					String sql="select * from user_info where id in " +
							"(select distinct user_id from face_info) " +
							"and permission_num="+permi;
					rs=stm.executeQuery(sql);

					//处理结果集
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
			
			
			//根据传入的名字字段查询用户
			public ArrayList<jdbcUsers> selectInfoByName(String name){
				Connection conn=null;
				Statement stm=null;
				ResultSet rs=null;
				ArrayList<jdbcUsers> userList=new ArrayList<jdbcUsers>();
				try {	//获得数据连接
					conn=JDBCUtils.getConnection();
					//获得statement对象
					stm=(Statement) conn.createStatement();
					//发送sql语句
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
			
			//根据传入的id查询用户是否录入对应的信息
			public usersForSearch selectInfo(String id){
				Connection conn=null;
				Statement stm=null;
				ResultSet rs=null;
				String isFaceExist;
				String isFingerExist;
				usersForSearch us=new usersForSearch();
				try {	//获得数据连接
					conn=JDBCUtils.getConnection();
					//获得statement对象
					stm=(Statement) conn.createStatement();
					//发送sql语句
					String sql_1="select * from face_info where user_id="+id;
					rs=stm.executeQuery(sql_1);
					if(rs.next()){
						isFaceExist="是";
					}else{
						isFaceExist="否";
					}
					rs=null;
					String sql_2="select * from fingerprint_info where user_id="+id;
					rs=stm.executeQuery(sql_2);
					if(rs.next()){
						isFingerExist="是";
					}else{
						isFingerExist="否";
					}
					rs=null;
					String sql_3="select * from user_info where id="+id;
					rs=stm.executeQuery(sql_3);
					rs.next();
					us.setId(rs.getString("id"));
					us.setName(rs.getString("name"));
					us.setAge(rs.getInt("age"));
					if(rs.getInt("permission_num")==0){
						us.setPermi("超级管理员");
					}else if(rs.getInt("permission_num")==1){
						us.setPermi("管理员");
					}else if(rs.getInt("permission_num")==2){
						us.setPermi("普通用户");
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
			
			
			//根据id更新姓名
			public boolean updateNamebyId(String id,String name){
				
				Connection conn=null;
				Statement stm=null;
				try {
					//获得数据库的连接
					conn=JDBCUtils.getConnection();
					//获得Statement对象
					stm=(Statement) conn.createStatement();
					
					//发送sql语句
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
			
			
			//根据id更新年龄
			public boolean updateAgebyId(String id,int age){
					
					Connection conn=null;
					Statement stm=null;
					try {
						//获得数据库的连接
						conn=JDBCUtils.getConnection();
						//获得Statement对象
						stm=(Statement) conn.createStatement();
						
						//发送sql语句
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
				
				
				//根据id更新性别
				public boolean updateSexbyId(String id,String sex){
					
					Connection conn=null;
					Statement stm=null;
					try {
						//获得数据库的连接
						conn=JDBCUtils.getConnection();
						//获得Statement对象
						stm=(Statement) conn.createStatement();
						
						//发送sql语句
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
				
				
				//根据id更新电话
				public boolean updateTelbyId(String id,String tel){
					
					Connection conn=null;
					Statement stm=null;
					try {
						//获得数据库的连接
						conn=JDBCUtils.getConnection();
						//获得Statement对象
						stm=(Statement) conn.createStatement();
						
						//发送sql语句
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
				
				//根据id更新权限
				public boolean updatePermibyId(String id,int permi){
							
						Connection conn=null;
						Statement stm=null;
						try {
							//获得数据库的连接
							conn=JDBCUtils.getConnection();
							//获得Statement对象
							stm=(Statement) conn.createStatement();
								
							//发送sql语句
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
				
				//根据id删除用户 连同指纹和人脸信息一起删除
				public boolean deleteAllInfo(String id){
					Connection conn=null;
					Statement stm=null;
					ResultSet rs=null;

					try {

						//获得数据连接
						conn=JDBCUtils.getConnection();
						//获得statement对象
						stm=(Statement) conn.createStatement();
						//发送sql语句
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
