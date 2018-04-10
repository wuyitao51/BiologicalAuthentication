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
		 * ɾ���û�
		 */
		String s = request.getParameter("checked");
		System.out.println(s);
		
		ArrayList<usersForSearch> currentUsers = (ArrayList<usersForSearch>) request.getSession().getAttribute("users");
		
		deleteUsersService service = new deleteUsersService();
		String msg = "���û���";
		System.out.println(s);
		//�����ַ���
		for(int i=0;i<s.length();i++){
			char c = s.charAt(i);
			int j = 9999;
			if(i<s.length()-1){
				//�������һλ
				char ch = s.charAt(i+1);
				if(c>='0'&&c<='9'){			
					if(ch>='0'&&ch<='9'){
						//��һλ����һλ��������---����һ��ʮλ��
						j = (c-'0')*10+(ch-'0')-1;//������ڼ����е�����
						i++;//������1��������λ
					}else{
						//ֻ����һλ�����֣�����һ����λ��
						j= c-'0'-1;
					}
				}
			}else{
				//�����һλ
				j= c-'0'-1;
			}
			if(j!=9999){
				String id = currentUsers.get(j).getId();//�õ��˴�ɾ����Ϣ���û���id
				System.out.println(id);
				if(service.deleteUserInfoById(id)){
					msg += id+"�û�ȫ����Ϣɾ���ɹ���";
				}else{
					msg += id+"�û���Ϣɾ��ʧ�ܻ򲿷�ɾ��ʧ��";
				}
			}
		}
		
		PrintWriter out = response.getWriter();
		out.print(msg);
	}

}
