package com.sicau.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.sicau.jdbc.domain.jdbcUsers;
import com.sicau.service.addUsersService;

public class addUsersServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		/*
		 * 普通用户添加的界面都是普通组件，没有上传组件
		 * 十组表单数据
		 */
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		File f = new File("G://");
		factory.setRepository(f);
		ServletFileUpload upload = new ServletFileUpload(factory);
		ArrayList<Integer> checkedItems = new ArrayList<Integer>();//所勾选的要提交的用户的序号
		ArrayList<Boolean> saveResults = new ArrayList<Boolean>();//提交的结果集
		try {
			List <FileItem>fileItems = upload.parseRequest(request);
			
			for(FileItem F : fileItems){
				if(F.getFieldName().equals("choose") && !F.getString("UTF-8").equals("")){
					//说明被勾选了
					checkedItems.add(Integer.parseInt(F.getString("UTF-8")));
					//获取了所勾选的要提交的用户的序号的序号集
				}
			}
			for(int i : checkedItems){
				jdbcUsers u = new jdbcUsers();
				u.setPermission_num(2);
				//再次遍历
				for(FileItem F : fileItems){
					if(F.getFieldName().equals("name"+i)){
						u.setName(F.getString("UTF-8"));
					}else if(F.getFieldName().equals("male"+i)){
						u.setSex("男");
					}else if(F.getFieldName().equals("female"+i)){
						u.setSex("女");
					}else if(F.getFieldName().equals("age"+i)){
						u.setAge(Integer.parseInt(F.getString("UTF-8")));
					}else if(F.getFieldName().equals("tel"+i)){
						u.setTel(F.getString("UTF-8"));
					}else if(F.getFieldName().equals("email"+i)){
						u.setEmail(F.getString("UTF-8"));
					}else if(F.getFieldName().equals("id"+i)){
						u.setId(F.getString("UTF-8"));
					}
				}
				//System.out.println(u.toString());
				//封装好用户信息后进行插入
				addUsersService service = new addUsersService();
				boolean result = service.saveUserInfo(u);
				saveResults.add(result);
			}
			
		
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//输入到前台跳出框显示
		int success = 0, fail = 0; 
		String info = "您选择提交以下行的用户信息：\n";
		for(int i = 0; i<checkedItems.size();i++){
			info += "序号为"+checkedItems.get(i)+"的用户";
			if(saveResults.get(i)){
				info += "            操作结果：录入成功！\n";
				success++;
			}else{
				info += "            操作结果：录入失败！\n";
				fail++;
			}
		}
		
		
		System.out.println(info);	
		if(saveResults.size()>0){
			//提交成功
			request.setAttribute("messageSuccess", "saved");
			request.getSession().setAttribute("success", success+"");
			request.getSession().setAttribute("fail", fail+"");
			request.getRequestDispatcher("addUsers.jsp").forward(request, response);
		}else{
			request.setAttribute("messageFailed", "unsaved");
			request.getRequestDispatcher("addUsers.jsp").forward(request, response);
		}

	}

}
