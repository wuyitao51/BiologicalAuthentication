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
		 * ��ͨ�û���ӵĽ��涼����ͨ�����û���ϴ����
		 * ʮ�������
		 */
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		File f = new File("G://");
		factory.setRepository(f);
		ServletFileUpload upload = new ServletFileUpload(factory);
		ArrayList<Integer> checkedItems = new ArrayList<Integer>();//����ѡ��Ҫ�ύ���û������
		ArrayList<Boolean> saveResults = new ArrayList<Boolean>();//�ύ�Ľ����
		try {
			List <FileItem>fileItems = upload.parseRequest(request);
			
			for(FileItem F : fileItems){
				if(F.getFieldName().equals("choose") && !F.getString("UTF-8").equals("")){
					//˵������ѡ��
					checkedItems.add(Integer.parseInt(F.getString("UTF-8")));
					//��ȡ������ѡ��Ҫ�ύ���û�����ŵ���ż�
				}
			}
			for(int i : checkedItems){
				jdbcUsers u = new jdbcUsers();
				u.setPermission_num(2);
				//�ٴα���
				for(FileItem F : fileItems){
					if(F.getFieldName().equals("name"+i)){
						u.setName(F.getString("UTF-8"));
					}else if(F.getFieldName().equals("male"+i)){
						u.setSex("��");
					}else if(F.getFieldName().equals("female"+i)){
						u.setSex("Ů");
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
				//��װ���û���Ϣ����в���
				addUsersService service = new addUsersService();
				boolean result = service.saveUserInfo(u);
				saveResults.add(result);
			}
			
		
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//���뵽ǰ̨��������ʾ
		int success = 0, fail = 0; 
		String info = "��ѡ���ύ�����е��û���Ϣ��\n";
		for(int i = 0; i<checkedItems.size();i++){
			info += "���Ϊ"+checkedItems.get(i)+"���û�";
			if(saveResults.get(i)){
				info += "            ���������¼��ɹ���\n";
				success++;
			}else{
				info += "            ���������¼��ʧ�ܣ�\n";
				fail++;
			}
		}
		
		
		System.out.println(info);	
		if(saveResults.size()>0){
			//�ύ�ɹ�
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
