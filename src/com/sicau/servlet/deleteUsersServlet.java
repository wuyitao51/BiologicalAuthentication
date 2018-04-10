package com.sicau.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sicau.javabean.usersForSearch;
import com.sicau.service.deleteUsersService;

public class deleteUsersServlet extends HttpServlet {

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
		response.setCharacterEncoding("UTF-8");
		/*
		 * 删除用户
		 */
		String s = request.getParameter("checked");
		System.out.println(s);
		
		ArrayList<usersForSearch> currentUsers = (ArrayList<usersForSearch>) request.getSession().getAttribute("users");
		
		deleteUsersService service = new deleteUsersService();
		String msg = "对用户：";
		System.out.println(s);
		//遍历字符串
		for(int i=0;i<s.length();i++){
			char c = s.charAt(i);
			int j = 9999;
			if(i<s.length()-1){
				//不是最后一位
				char ch = s.charAt(i+1);
				if(c>='0'&&c<='9'){			
					if(ch>='0'&&ch<='9'){
						//这一位和下一位都是数字---这是一个十位数
						j = (c-'0')*10+(ch-'0')-1;//这就是在集合中的索引
						i++;//再自增1，跳过个位
					}else{
						//只有这一位是数字，这是一个个位数
						j= c-'0'-1;
					}
				}
			}else{
				//是最后一位
				j= c-'0'-1;
			}
			if(j!=9999){
				String id = currentUsers.get(j).getId();//得到了待删除信息的用户的id
				System.out.println(id);
				if(service.deleteUserInfoById(id)){
					msg += id+"用户全部信息删除成功，";
				}else{
					msg += id+"用户信息删除失败或部分删除失败";
				}
			}
		}
		
		PrintWriter out = response.getWriter();
		out.print(msg);
	}

}
