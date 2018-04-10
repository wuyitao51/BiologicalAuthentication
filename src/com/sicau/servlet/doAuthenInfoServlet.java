package com.sicau.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class doAuthenInfoServlet extends HttpServlet {

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
		response.setCharacterEncoding("UTF-8");
		
		//System.out.println("为用户添加识别信息");
		//System.out.println(request.getParameter("id"));
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
		response.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		System.out.println("为用户"+id+"添加识别信息");
		
		
		request.getSession().setAttribute("currentId", id);
		PrintWriter out = response.getWriter();
		out.print("success");//传到前台result
		/*
		 * 这里不管是以请求重定向方式还是请求转发都是不可行的
		 * 因为servlet拿到的是ajax发送过来的request,
		 * 也就是说这个请求不是浏览器请求的，而是ajax请求的
		 * 所以,servlet对request进行请求转发或重定向都不能影响浏览器的跳转
		 * 使用ajax本来目的是为了局部刷新网页,而不是跳转
		 */
		//request.getRequestDispatcher("authenInfoInput.jsp").forward(request, response);
		//response.sendRedirect("authenInfoInput.jsp");
	}

}
