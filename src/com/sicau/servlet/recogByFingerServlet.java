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

import com.sicau.jdbc.domain.jdbcUsers;

public class recogByFingerServlet extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public recogByFingerServlet() {
		super();
	}

	/**
		 * Destruction of the servlet. <br>
		 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

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
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String id_face = request.getSession().getAttribute("idByFace_recog").toString();//ͨ��face�õ���id
		System.out.println(id_face);
		
		/*
		 * �������ȡ����
		 * ��ͨ���+�ϴ����
		 * ������һ��������  ������һ���ϴ�ʵ��
		*/
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//���û���λ��
		File f = new File("C://");
		factory.setRepository(f);
		//����һ�� �ϴ���ʵ��
		ServletFileUpload upload = new ServletFileUpload(factory);
		String path = "";//ָ��ͼƬ��·��
		try {
			List<FileItem> fileItems = upload.parseRequest(request);
			for(FileItem F : fileItems){
				//ȷ�����ϴ����
				if(!F.isFormField()){
					if(F.getFieldName().equals("finger")){
						path=uploadFileItem(F);//�õ�ͼƬ������·��
					}		
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(path);
		//�õ���ͼƬ·��
		/*
		 * �����ݿ���ȡ������id��Ӧ��ָ����Ϣ
		 */
		boolean result = true;
		jdbcUsers u  = new jdbcUsers("123456","222222","wyt","13355556666","wuyitao@qwe.com","��",20,2,"sdfghj");
		if(result){
			//��֤�ɹ�
			request.setAttribute("messagesuccess", "success");
			String id = u.getId();
			String name = u.getName();
			String age = u.getAge()+"";
			String tel = u.getTel();
			String email = u.getEmail();
			request.setAttribute("id",id);
			request.setAttribute("name",name);
			request.setAttribute("age",age);
			request.setAttribute("tel",tel);
			request.setAttribute("email",email);
			request.getRequestDispatcher("admin.jsp").forward(request, response);
		}else{
			request.setAttribute("messagefail", "fail");
			request.getRequestDispatcher("admin.jsp").forward(request, response);
		}
	}

	/**
		 * Initialization of the servlet. <br>
		 *
		 * @throws ServletException if an error occurs
		 */
	public void init() throws ServletException {
		// Put your code here
	}

	//����ͼƬ��·��
		private String uploadFileItem(FileItem f){
			/*
			 * 1.��ȡͼƬ����
			 * 2.�ڷ������д���ͼƬ���������(�ļ���)
			 * 3.����ͼƬ
			 */
			//�ļ��ĳ���·��
			//String path = this.getServletContext().getRealPath("/WEB-INF/upload");//����·��
			String path = "C:\\upload";
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
