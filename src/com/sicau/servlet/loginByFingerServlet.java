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

public class loginByFingerServlet extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public loginByFingerServlet() {
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
		
		boolean authenResult = false;//��֤�Ƿ�ͨ���Ľ��
		boolean isSuperAdmin =false;//�Ƿ��ǳ�������Ա
		
		String id_face = request.getSession().getAttribute("idByFace").toString();//ͨ��face�õ���id
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
		//ͨ���ȶԵõ����
		if(path!=null){
			authenResult = true;//
			isSuperAdmin =true;
		}
		
		//������֤���������Ӧ�Ĵ���
		if (authenResult) {
			//�ض���
			if(isSuperAdmin){
				request.getSession().setAttribute("messagepermi", "issuperadmin");
				//��һ����Ϣ�Ǳ�����session��ģ��ڵ��λỰ��һֱ����
				System.out.println("�ǳ�������Ա������");
			}
			response.sendRedirect("admin.jsp");
		}else{
			//ת��
			request.setAttribute("message", "fail");
			request.getRequestDispatcher("login.jsp").forward(request, response);
					
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
