package com.sicau.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.sicau.dao.usersDao;
import com.sicau.javabean.Users;
import com.sicau.jdbc.domain.jdbcUsers;
import com.sicau.service.loginService;
import com.sicau.service.userAuthenService;


public class loginServlet extends HttpServlet {

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
		request.setCharacterEncoding("UTF-8");
		doPost( request, response);
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
     
		boolean authenResult = false;//��֤�Ƿ�ͨ���Ľ��
		boolean isSuperAdmin =false;//�Ƿ��ǳ�������Ա
		
		//��ȡ�˺������¼�ı�����

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		
		if(username != null && password != null){
			loginService service = new loginService();
			authenResult = service.getResult(username, password);
			usersDao usd = new usersDao();
			if(usd.find(username).getPermission_num()==0){
				isSuperAdmin =true;
			}
		}else{
			authenResult = false;
		}
		
		//������֤���������Ӧ�Ĵ���
		if (authenResult) {
			//�ض���
			if(isSuperAdmin){
				request.getSession().setAttribute("messagepermi", "issuperadmin");
				//��һ����Ϣ�Ǳ�����session��ģ��ڵ��λỰ��һֱ����
				System.out.println("�ǳ�������Ա������");
			}
			response.sendRedirect("admin.jsp");
		}else{
			//ת��
			request.setAttribute("message", "fail");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
		}
	}
	

}
