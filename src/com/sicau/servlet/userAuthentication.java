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
		 * 多组件获取数据
		 * 普通组件+上传组件
		 * 创建出一个工厂类  创建出一个上传实现
		*/
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//设置缓存位置
		File f = new File("G://");
		factory.setRepository(f);
		//创建一个 上传的实现
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		//指纹图片的路径
		String fingerPath = null;
		//面部图片的路径
		String facePath = null;
		//获取所有组件
		try {
			List<FileItem> fileItems = upload.parseRequest(request);
			for(FileItem F : fileItems){
				//确认是上传组件
				if(!F.isFormField()){
					String path=uploadFileItem(F);//得到图片的完整路径
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
		//得到了待认证的图片信息的路径
		
		/*
		 * 调用userAuthenService得到符合条件的jdbcUsers对象（没有则为null）
		 */
		userAuthenService service = new userAuthenService();
		//jdbcUsers u_face = service.faceCompare(facePath);//与脸部信息匹配的用户
		//jdbcUsers u_finger = service.faceCompare(facePath);//与指纹信息匹配的用户
		jdbcUsers u  = new jdbcUsers("123456","222222","wyt","13355556666","wuyitao@qwe.com","男",20,2,"sdfghj");
		
		if(u==null){
		//if(u_face == null || u_finger==null || !u_face.equals(u_finger)){
			//认证失败
			request.setAttribute("messagefail", "fail");
			request.getRequestDispatcher("admin.jsp").forward(request, response);
		}else{
			//认证成功
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
	
	
	//上传组件
		//返回图片的路径
		private String uploadFileItem(FileItem f){
			/*
			 * 1.获取图片名字
			 * 2.在服务器中创建图片储存的区域(文件夹)
			 * 3.储存图片
			 */
			//文件的抽象路径
			String path = this.getServletContext().getRealPath("/WEB-INF/upload");//完整路径
			//String path = "F:/upload";
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
