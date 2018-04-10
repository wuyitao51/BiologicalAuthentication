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
		//搜索关键字
		String keywords = request.getParameter("search");
		System.out.println(keywords);
		/*
		 * 根据搜索的关键字返回用户对象
		 * 动态地显示表格
		 */
		/*
		 * 关于显示的序号的问题
		 * 给这里的users类添加一个成员--序号，在上一层传过来的对象列表之后通过一个迭代将他的用户对象转化为我的用户对象
		 * 迭代中给用户成员添加序号
		 * 其中我的用户成员除了序号和年龄都为String类型，成员的值都是直接显示在页面上，所以权限值和是否录入信息都应该直接显示
		 * 是管理员还是普通会员和是还是否
		 */
		searchUsersService service = new searchUsersService();
		ArrayList<usersForSearch> usersList = service.searchUsers(keywords);
		
		/*
		usersForSearch u1 = new usersForSearch(1,"allen","男",30,"421202200001011234","13965558877","普通用户","是","否");
		usersForSearch u2 = new usersForSearch(2,"lily","女",30,"421202200001011234","13965558877","普通用户","是","否");
		usersForSearch u3 = new usersForSearch(3,"tom","男",30,"421202200001011234","13965558877","普通用户","是","否");
		usersForSearch u4 = new usersForSearch(4,"lucy","女",32,"421202200001011234","13965558877","普通用户","是","否");
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
