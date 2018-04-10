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
		
		//System.out.println("Ϊ�û����ʶ����Ϣ");
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
		System.out.println("Ϊ�û�"+id+"���ʶ����Ϣ");
		
		
		request.getSession().setAttribute("currentId", id);
		PrintWriter out = response.getWriter();
		out.print("success");//����ǰ̨result
		/*
		 * ���ﲻ�����������ض���ʽ��������ת�����ǲ����е�
		 * ��Ϊservlet�õ�����ajax���͹�����request,
		 * Ҳ����˵������������������ģ�����ajax�����
		 * ����,servlet��request��������ת�����ض��򶼲���Ӱ�����������ת
		 * ʹ��ajax����Ŀ����Ϊ�˾ֲ�ˢ����ҳ,��������ת
		 */
		//request.getRequestDispatcher("authenInfoInput.jsp").forward(request, response);
		//response.sendRedirect("authenInfoInput.jsp");
	}

}
