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
		
		boolean authenResult = false;//验证是否通过的结果
		boolean isSuperAdmin =false;//是否是超级管理员
		
		String id_face = request.getSession().getAttribute("idByFace").toString();//通过face得到的id
		System.out.println(id_face);
		
		/*
		 * 多组件获取数据
		 * 普通组件+上传组件
		 * 创建出一个工厂类  创建出一个上传实现
		*/
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//设置缓存位置
		File f = new File("C://");
		factory.setRepository(f);
		//创建一个 上传的实现
		ServletFileUpload upload = new ServletFileUpload(factory);
		String path = "";//指纹图片的路径
		try {
			List<FileItem> fileItems = upload.parseRequest(request);
			for(FileItem F : fileItems){
				//确认是上传组件
				if(!F.isFormField()){
					if(F.getFieldName().equals("finger")){
						path=uploadFileItem(F);//得到图片的完整路径
					}		
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(path);
		
		//拿到了图片路径
		//通过比对得到结果
		if(path!=null){
			authenResult = true;//
			isSuperAdmin =true;
		}
		
		//根据验证结果做出相应的处理
		if (authenResult) {
			//重定向
			if(isSuperAdmin){
				request.getSession().setAttribute("messagepermi", "issuperadmin");
				//这一条消息是保存在session里的，在当次会话中一直存在
				System.out.println("是超级管理员！！！");
			}
			response.sendRedirect("admin.jsp");
		}else{
			//转发
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

	//返回图片的路径
	private String uploadFileItem(FileItem f){
		/*
		 * 1.获取图片名字
		 * 2.在服务器中创建图片储存的区域(文件夹)
		 * 3.储存图片
		 */
		//文件的抽象路径
		//String path = this.getServletContext().getRealPath("/WEB-INF/upload");//完整路径
		String path = "C:\\upload";
		File photoDir = new File(path);
		//这个目录是否存在
		if (!photoDir.exists()) {
			//如果不存在   创建
			photoDir.mkdirs();
		}
	    //获取图片名字
		String fileName = f.getName(); 
		//通过文件名字工具类  将文件名字统一   
		String imgName = FilenameUtils.getName(fileName);
		//创建图片文件对象
		File img = new File(path, imgName);
		//得到图片的路径
		String imgPath = path+"\\"+imgName;
				
		//将图片写入
		try {
			f.write(img);
			//清除缓存
			f.delete();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imgPath;
	}
}
