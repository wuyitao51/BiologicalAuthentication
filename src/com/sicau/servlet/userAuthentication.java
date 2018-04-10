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
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.sicau.javabean.Users;
import com.sicau.jdbc.domain.jdbcUsers;
import com.sicau.service.userAuthenService;

public class userAuthentication extends HttpServlet {

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
		 * �������ȡ����
		 * ��ͨ���+�ϴ����
		 * ������һ��������  ������һ���ϴ�ʵ��
		*/
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//���û���λ��
		File f = new File("G://");
		factory.setRepository(f);
		//����һ�� �ϴ���ʵ��
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		//ָ��ͼƬ��·��
		String fingerPath = null;
		//�沿ͼƬ��·��
		String facePath = null;
		//��ȡ�������
		try {
			List<FileItem> fileItems = upload.parseRequest(request);
			for(FileItem F : fileItems){
				//ȷ�����ϴ����
				if(!F.isFormField()){
					String path=uploadFileItem(F);//�õ�ͼƬ������·��
					if(F.getFieldName().equals("finger")){
						fingerPath = path;
					}else if(F.getFieldName().equals("face")){
						facePath = path;
					}
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(fingerPath);
		System.out.println(facePath);
		//�õ��˴���֤��ͼƬ��Ϣ��·��
		
		/*
		 * ����userAuthenService�õ�����������jdbcUsers����û����Ϊnull��
		 */
		userAuthenService service = new userAuthenService();
		//jdbcUsers u_face = service.faceCompare(facePath);//��������Ϣƥ����û�
		//jdbcUsers u_finger = service.faceCompare(facePath);//��ָ����Ϣƥ����û�
		jdbcUsers u  = new jdbcUsers("123456","222222","wyt","13355556666","wuyitao@qwe.com","��",20,2,"sdfghj");
		
		if(u==null){
		//if(u_face == null || u_finger==null || !u_face.equals(u_finger)){
			//��֤ʧ��
			request.setAttribute("messagefail", "fail");
			request.getRequestDispatcher("admin.jsp").forward(request, response);
		}else{
			//��֤�ɹ�
			request.setAttribute("messagesuccess", "success");
			String id = u.getId();
			String name = u.getName();
			String age = u.getAge()+"";
			String tel = u.getTel();
			String email = u.getEmail();
			/*String id = u_face.getId();
			String name = u_face.getName();
			String age = u_face.getAge()+"";
			String tel = u_face.getTel();
			String email = u_face.getEmail();*/
			request.setAttribute("id",id);
			request.setAttribute("name",name);
			request.setAttribute("age",age);
			request.setAttribute("tel",tel);
			request.setAttribute("email",email);
			request.getRequestDispatcher("admin.jsp").forward(request, response);
		}
		
	}
	
	
	//�ϴ����
		//����ͼƬ��·��
		private String uploadFileItem(FileItem f){
			/*
			 * 1.��ȡͼƬ����
			 * 2.�ڷ������д���ͼƬ���������(�ļ���)
			 * 3.����ͼƬ
			 */
			//�ļ��ĳ���·��
			String path = this.getServletContext().getRealPath("/WEB-INF/upload");//����·��
			//String path = "F:/upload";
			File photoDir = new File(path);
			//���Ŀ¼�Ƿ����
			if (!photoDir.exists()) {
				//���������   ����
				photoDir.mkdirs();
			}
	 	    //��ȡͼƬ����
			String fileName = f.getName(); 
			//ͨ���ļ����ֹ�����  ���ļ�����ͳһ   
			String imgName = FilenameUtils.getName(fileName);
			//����ͼƬ�ļ�����
			File img = new File(path, imgName);
			//�õ�ͼƬ��·��
			String imgPath = path+"\\"+imgName;
			
			//��ͼƬд��
			try {
				f.write(img);
				//�������
				f.delete();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return imgPath;
		}

				

}
