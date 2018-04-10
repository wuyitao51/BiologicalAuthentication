package com.sicau.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sicau.dao.usersDao;

public class changeWeightServlet extends HttpServlet {

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
		 * ����id���û��ᣨ����Ȩ
		 * �������ͨ�û�����ȨΪ����Ա
		 * ����ǹ���Ա����ȨΪ��ͨ�û�
		 * ��������Ա��Ψһ�����ܶԱ�������Ȩ��Ȩ����
		 * �����ͨ�û���û��¼����֤��Ϣ����֤��Ϣ��ȫ����ô�޷��Ը��û�������Ȩ
		 */
		String id = request.getParameter("id");
		String permi = request.getParameter("permission");
		String finger = request.getParameter("finger");
		String face = request.getParameter("face");
		System.out.println("id="+id+",permission="+permi+",fingerexist:"+finger+",faceexist"+face);
		
		usersDao usd = new usersDao();
		PrintWriter out = response.getWriter();

		if(permi.equals("��������Ա")){
			out.print("���޷��Գ�������Ա���в���!");
		}else if(permi.equals("����Ա")){
			//�����û�Ȩ��ֵ��Ϊ2������ͨ�û�
			if(usd.updatePermibyId(id, 2)){
				out.print("�ѳɹ�������Ա�û���"+id+"��ȨΪ��ͨ�û�");
			}else{
				out.print("�Թ���Ա�û���"+id+"��Ȩ����ʧ��");
			}
		}else{
			if(finger.equals("��") || face.equals("��")){
				out.print("���û���֤��Ϣ���������޷�������Ȩ����");
			}else{
				if(usd.updatePermibyId(id, 1)){
					out.print("�ѳɹ�����ͨ�û���"+id+"��ȨΪ����Ա");
				}else{
					out.print("����ͨ�û���"+id+"��Ȩ����ʧ��");
				}
			}
		}
	}

}
