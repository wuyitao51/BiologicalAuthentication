package com.sicau.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
import com.sicau.javabean.face;
import com.sicau.javabean.finger;
import com.sicau.service.registerService;


public class registerServlet extends HttpServlet {

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
		doPost(request,response);
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
		
		try {
			//获取所有组件
			List <FileItem>fileItems = upload.parseRequest(request);
			//封装用户的信息
			Users user = new Users();
			user.setuPermission(0);
			//System.out.println(fileItems.size());
			
			for(FileItem F : fileItems){

				if(F.isFormField()){
					//普通组件
					NormalFileItem(F,user);
				}else{
					//上传组件
					uploadFileItem(F,user);
				}
				
			}
			/*
			 * 
			 * 
			 * 这里还需要将图片生成混合特征值并封装到用户对象中
			 * 
			 * 
			 */
			//这里暂时指定一个特征值
			String s = "shakshwidhwncncskcnsasswsnikjhggf";
			user.setFingerInfo(s.getBytes());
			
			//到这里用户信息已经封装好了
			System.out.println(user.getId()+"\n"+user.getPassword()+"\n"+user.getuAge()+"\n"+user.getuEmail()+"\n"+
											user.getuName()+"\n"+user.getuSex()+"\n"+user.getuTel()+"\n"+user.getFinger1().getFinger_img()+"\n"+
											user.getFinger2().getFinger_img()+"\n"+user.getFinger3().getFinger_img()+"\n"+user.getFace1().getF_Img()+"\n"+
											user.getFace2().getF_Img()+"\n"+user.getFace3().getF_Img()+"\n"+user.getFace4().getF_Img()+"\n"+user.getFace5().getF_Img());
			/*
			 * 应该将封装好的用户对象提交到下一层
			 * 根据下一层返回的消息判断注册是否成功
			 */
			registerService service = new registerService();
			boolean finalReslut = service.saveUserInfo(user);
			if(finalReslut){
				//成功
				//重定向到登录界面
				response.sendRedirect("login.jsp");
			}else{
				//失败
				request.setAttribute("message", "fail");
				request.getRequestDispatcher("superAdminRegister.jsp").forward(request, response);
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/*
	 * 上传组件
	 * 将表单上传的信息写入再将路径封装
	 */
	private void uploadFileItem(FileItem f, Users u) {
		// TODO Auto-generated method stub
		/*
		 * 1.获取图片名字
		 * 2.在服务器中创建图片储存的区域(文件夹)
		 * 3.储存图片
		 */
		//文件的抽象路径
		String path = this.getServletContext().getRealPath("/WEB-INF/upload");//完整路径
		//System.out.println(path);
		File photoDir = new File(path);
		//这个目录是否存在
		if (!photoDir.exists()) {
			//如果不存在   创建
			photoDir.mkdirs();
		}
 	    //获取图片名字
		String fileName = f.getName(); 
		//System.out.println(fileName);
		//通过文件名字工具类  将文件名字统一   
		String imgName = FilenameUtils.getName(fileName);
		//创建图片文件对象
		File img = new File(path, imgName);
		//得到图片的路径
		String imgPath = path+"\\"+imgName;
		
		//将路径封装到用户信息中
		if(f.getFieldName().equals("finger1")){
			finger fg = new finger();
			fg.setFinger_img(imgPath);
			/*
			 * 
			 * 这里应该调用指纹模块根据指纹图片路径获取特征值封装到fg中
			 * 
			 */
			//暂时指定一个
			String s = "djwifhfbdsjkbcdskbvs";
			fg.setFinger_info(s.getBytes());
			
			u.setFinger1(fg);
		}else if(f.getFieldName().equals("finger2")){
			finger fg = new finger();
			fg.setFinger_img(imgPath);
			
			String s = "djwifhfbdsjkbcdskbvs";
			fg.setFinger_info(s.getBytes());
			
			u.setFinger2(fg);
			
		}else if(f.getFieldName().equals("finger3")){
			finger fg = new finger();
			fg.setFinger_img(imgPath);
			
			String s = "djwifhfbdsjkbcdskbvs";
			fg.setFinger_info(s.getBytes());
			
			u.setFinger3(fg);
		}else if(f.getFieldName().equals("face1")){
			face fc = new face();
			fc.setF_Img(imgPath);
			u.setFace1(fc);
		}else if(f.getFieldName().equals("face2")){
			face fc = new face();
			fc.setF_Img(imgPath);
			u.setFace2(fc);
		}else if(f.getFieldName().equals("face3")){
			face fc = new face();
			fc.setF_Img(imgPath);
			u.setFace3(fc);
		}else if(f.getFieldName().equals("face4")){
			face fc = new face();
			fc.setF_Img(imgPath);
			u.setFace4(fc);
		}else if(f.getFieldName().equals("face5")){
			face fc = new face();
			fc.setF_Img(imgPath);
			u.setFace5(fc);
		}
		
		//将图片写入
		try {
			f.write(img);
			//清除缓存
			f.delete();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 普通组件
	 * 将表单填写的信息封装
	 */
	private void NormalFileItem(FileItem f, Users u) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		String name= f.getFieldName();
		String value = f.getString("UTF-8");
        if("idNum".equals(name)){
        	u.setId(value);
        }else if("password".equals(name)){
        	u.setPassword(value);
        }else if("name".equals(name)){
        	u.setuName(value);
        }else if("email".equals(name)){
        	u.setuEmail(value);
        }else if("male".equals(name)){
        	u.setuSex("男");
        }else if("female".equals(name)){
        	u.setuSex("女");
        }else if("age".equals(name)){
        	//类型转换
        	u.setuAge(Integer.parseInt(value));
        }else if("tel".equals(name)){
        	u.setuTel(value);
        }
        	
	}

}
