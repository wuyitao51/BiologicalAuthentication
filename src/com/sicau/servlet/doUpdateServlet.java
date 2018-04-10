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

import com.sicau.dao.usersDao;
import com.sicau.javabean.usersForSearch;

public class doUpdateServlet extends HttpServlet {

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
		doPost(request, response);
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
		
		//��ǰ�����ʾ���û���Ϣ
		ArrayList<usersForSearch> usersList = (ArrayList<usersForSearch>) request.getSession().getAttribute("users");
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		File f = new File("G://");
		factory.setRepository(f);
		ServletFileUpload upload = new ServletFileUpload(factory);
		usersDao usd = new usersDao();
		String s = "";
		int len = 0;
		try {
			List <FileItem>fileItems = upload.parseRequest(request);
			//���е����
			int i = 0, j = 0;//i����ѭ����j�������ڶԵڼ������ݽ��в���
			//�������е����
			//��-1Ҫ��Խ���쳣
			while(i<(fileItems.size()-1)){				
				//����һ��Ϊ��ѡ��
				if(fileItems.get(i).getFieldName().equals("choose")){
					String sex = "";
					String age = "";
					String tel = "";
					//��һ������Ҫ�ύ��
					//��һ����������
					String name = fileItems.get(i+1).getString("UTF-8");
					if(fileItems.get(i+2).getFieldName().equals("sex")){
						//��������Ա��
						sex = fileItems.get(i+2).getString("UTF-8");
						age = fileItems.get(i+3).getString("UTF-8");
						tel = fileItems.get(i+4).getString("UTF-8");
						i += 5;
					}else{
						//����Ĳ����Ա����ôӦ�������䣬�ٺ����ǵ绰
					    age = fileItems.get(i+2).getString("UTF-8");
						tel = fileItems.get(i+3).getString("UTF-8");
						i += 4;
					}
					String id = usersList.get(j).getId();
					s += "���û�"+id+"��";
					//System.out.println("id:"+id+"name:"+name+"sex:"+sex+"age:"+age+"tel:"+tel);
					if(!name.equals("")){
						if(usd.updateNamebyId(id, name)){
							s+="�ɹ�����������Ϊ"+name+"  ";
						}else{
							s += "��������ʧ��  ";
						}
					}
					if(!sex.equals("")){
						String uSex = "Ů";
						if(sex.equals("male")){
							uSex = "��";
						}
						if(usd.updateSexbyId(id, uSex)){
							s += "�ɹ����Ա����Ϊ"+uSex+"  ";
						}else{
							s += "�����Ա�ʧ��  ";
						}
					}
					if(!age.equals("")){
						int uAge = Integer.parseInt(age);
						if(usd.updateAgebyId(id, uAge)){
							s += "�ɹ����������Ϊ"+age+"  ";
						}else{
							s += "��������ʧ��  ";
						}
					}
					if(!tel.equals("")){
						if(usd.updateTelbyId(id, tel)){
							s += "�ɹ����绰����Ϊ"+tel+"  ";
						}else{
							s += "���µ绰ʧ��";
						}
					}
					s += "\n";
				}else{
				//��һ�����ǹ�ѡ����ô��һ��Ӧ�������������
				//������һ��
					//��һ���Ƿ�ѡ���Ա���
					if(fileItems.get(i+1).getFieldName().equals("sex")){
						//��һ���Ƿ�ѡ���Ա���
						//��������滹���Ա����䡢�绰
						i += 4;
					}else{
						//��һ��û�й�ѡ���Ա���
						//��������滹�����䡢�绰
						i += 3;
					}
				}
				j++;
			}
	
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(s);
		request.setAttribute("msg", "update");
		request.setAttribute("notify", s);
		request.getRequestDispatcher("admin.jsp").forward(request, response);
	}

}
