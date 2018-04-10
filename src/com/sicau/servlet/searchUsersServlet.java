package com.sicau.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sicau.javabean.usersForSearch;
import com.sicau.jdbc.domain.jdbcUsers;
import com.sicau.service.searchUsersService;

public class searchUsersServlet extends HttpServlet {

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
		//�����ؼ���
		String keywords = request.getParameter("search");
		System.out.println(keywords);
		/*
		 * ���������Ĺؼ��ַ����û�����
		 * ��̬����ʾ���
		 */
		/*
		 * ������ʾ����ŵ�����
		 * �������users�����һ����Ա--��ţ�����һ�㴫�����Ķ����б�֮��ͨ��һ�������������û�����ת��Ϊ�ҵ��û�����
		 * �����и��û���Ա������
		 * �����ҵ��û���Ա������ź����䶼ΪString���ͣ���Ա��ֵ����ֱ����ʾ��ҳ���ϣ�����Ȩ��ֵ���Ƿ�¼����Ϣ��Ӧ��ֱ����ʾ
		 * �ǹ���Ա������ͨ��Ա���ǻ��Ƿ�
		 */
		searchUsersService service = new searchUsersService();
		ArrayList<usersForSearch> usersList = service.searchUsers(keywords);
		
		/*
		usersForSearch u1 = new usersForSearch(1,"allen","��",30,"421202200001011234","13965558877","��ͨ�û�","��","��");
		usersForSearch u2 = new usersForSearch(2,"lily","Ů",30,"421202200001011234","13965558877","��ͨ�û�","��","��");
		usersForSearch u3 = new usersForSearch(3,"tom","��",30,"421202200001011234","13965558877","��ͨ�û�","��","��");
		usersForSearch u4 = new usersForSearch(4,"lucy","Ů",32,"421202200001011234","13965558877","��ͨ�û�","��","��");
		usersList.add(u1);
		usersList.add(u2);
		usersList.add(u3);
		usersList.add(u4);
		*/
		request.getSession().setAttribute("users", usersList);
		response.sendRedirect("admin.jsp");
		//request.setAttribute("users", usersList);
		//request.getRequestDispatcher("admin.jsp").forward(request, response);
	}

}
