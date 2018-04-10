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

import com.sicau.dao.faceDao;
import com.sicau.dao.fingerDao;
import com.sicau.dao.usersDao;
import com.sicau.service.doAddAuthenInfoService;

public class doAddAuthenInfoServlet extends HttpServlet {

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
		String fingerPath1 = "";
		String fingerPath2 = "";
		String fingerPath3 = "";
		String facePath1 = "";
		String facePath2 = "";
		String facePath3 = "";
		String facePath4 = "";
		String facePath5 = "";
		//获取所有组件
		try {
			List<FileItem> fileItems = upload.parseRequest(request);
			for(FileItem F : fileItems){
				//确认是上传组件
				if(!F.isFormField()){
					String path=uploadFileItem(F);//得到图片的完整路径
					if(F.getFieldName().equals("finger1")){
						fingerPath1 = path;
					}else if(F.getFieldName().equals("finger2")){
						fingerPath2 = path;
					}else if(F.getFieldName().equals("finger3")){
						fingerPath3 = path;
					}else if(F.getFieldName().equals("face1")){
						facePath1 = path;
					}else if(F.getFieldName().equals("face2")){
						facePath2 = path;
					}else if(F.getFieldName().equals("face3")){
						facePath3 = path;
					}else if(F.getFieldName().equals("face4")){
						facePath4 = path;
					}else if(F.getFieldName().equals("face5")){
						facePath5 = path;
					}
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//得到了八个图片信息的路径
		System.out.println("指纹1："+fingerPath1+"\n"+"指纹2："+fingerPath2+"\n"+"指纹3："+fingerPath3+"\n"+
				"面部1："+facePath1+"\n"+"面部2："+facePath2+"\n"+"面部3："+facePath3+"\n"+"面部4："+facePath4+"\n"+"面部5："+facePath5+"\n");
		
		//为指定id的用户录入图片信息
		String id = request.getSession().getAttribute("currentId").toString();
		System.out.println(id);
		
		/*
		 * 这里已经拿到了八个路径和用户的id
		 * 判断该用户是否有相应的信息
		 * 如果有那么则是更新
		 */
		
		usersDao usd = new usersDao();
		doAddAuthenInfoService service = new doAddAuthenInfoService();
		
		boolean fingerRemoveResult = true;
		if(usd.selectInfo(id).getIsFingerExist().equals("是")){
			//有指纹信息，更新，先删除现有的再插入
			fingerDao fgd = new fingerDao();
			fingerRemoveResult = fgd.deleteByUserId(id);
		}
		boolean fingerResult = false;
		if(fingerRemoveResult){
			fingerResult = service.fingerInsert(id, fingerPath1)&&service.fingerInsert(id, fingerPath2)&&service.fingerInsert(id, fingerPath3);
		}
		
		boolean faceRemoveResult = true;
		if(usd.selectInfo(id).getIsFaceExist().equals("是")){
			//有人脸信息
			faceDao fcd = new faceDao();
			faceRemoveResult = fcd.deleteByUserId(id);
		}
		boolean faceResult = false;
		if(faceRemoveResult){
			faceResult = service.faceInsert(id, facePath1)&&service.faceInsert(id, facePath2)&&service.faceInsert(id, facePath3)
								  &&service.faceInsert(id, facePath4)&&service.faceInsert(id, facePath5);
		}
		
		if(fingerResult&&faceResult){
			//所有信息插入或更新成功
			request.setAttribute("addsuccess", "success");
		}else{
			//有信息插入失败
			request.setAttribute("addfail", "fail");
		}
		request.getRequestDispatcher("admin.jsp").forward(request, response);
	}
	
	//返回图片的路径
	private String uploadFileItem(FileItem f){
		/*
		 * 1.获取图片名字
		 * 2.在服务器中创建图片储存的区域(文件夹)
		 * 3.储存图片
		 */
		//文件的抽象路径
		String path = this.getServletContext().getRealPath("/WEB-INF/upload");//完整路径
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
