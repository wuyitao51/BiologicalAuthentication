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
		
		try {
			//��ȡ�������
			List <FileItem>fileItems = upload.parseRequest(request);
			//��װ�û�����Ϣ
			Users user = new Users();
			user.setuPermission(0);
			//System.out.println(fileItems.size());
			
			for(FileItem F : fileItems){

				if(F.isFormField()){
					//��ͨ���
					NormalFileItem(F,user);
				}else{
					//�ϴ����
					uploadFileItem(F,user);
				}
				
			}
			/*
			 * 
			 * 
			 * ���ﻹ��Ҫ��ͼƬ���ɻ������ֵ����װ���û�������
			 * 
			 * 
			 */
			//������ʱָ��һ������ֵ
			String s = "shakshwidhwncncskcnsasswsnikjhggf";
			user.setFingerInfo(s.getBytes());
			
			//�������û���Ϣ�Ѿ���װ����
			System.out.println(user.getId()+"\n"+user.getPassword()+"\n"+user.getuAge()+"\n"+user.getuEmail()+"\n"+
											user.getuName()+"\n"+user.getuSex()+"\n"+user.getuTel()+"\n"+user.getFinger1().getFinger_img()+"\n"+
											user.getFinger2().getFinger_img()+"\n"+user.getFinger3().getFinger_img()+"\n"+user.getFace1().getF_Img()+"\n"+
											user.getFace2().getF_Img()+"\n"+user.getFace3().getF_Img()+"\n"+user.getFace4().getF_Img()+"\n"+user.getFace5().getF_Img());
			/*
			 * Ӧ�ý���װ�õ��û������ύ����һ��
			 * ������һ�㷵�ص���Ϣ�ж�ע���Ƿ�ɹ�
			 */
			registerService service = new registerService();
			boolean finalReslut = service.saveUserInfo(user);
			if(finalReslut){
				//�ɹ�
				//�ض��򵽵�¼����
				response.sendRedirect("login.jsp");
			}else{
				//ʧ��
				request.setAttribute("message", "fail");
				request.getRequestDispatcher("superAdminRegister.jsp").forward(request, response);
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/*
	 * �ϴ����
	 * �����ϴ�����Ϣд���ٽ�·����װ
	 */
	private void uploadFileItem(FileItem f, Users u) {
		// TODO Auto-generated method stub
		/*
		 * 1.��ȡͼƬ����
		 * 2.�ڷ������д���ͼƬ���������(�ļ���)
		 * 3.����ͼƬ
		 */
		//�ļ��ĳ���·��
		String path = this.getServletContext().getRealPath("/WEB-INF/upload");//����·��
		//System.out.println(path);
		File photoDir = new File(path);
		//���Ŀ¼�Ƿ����
		if (!photoDir.exists()) {
			//���������   ����
			photoDir.mkdirs();
		}
 	    //��ȡͼƬ����
		String fileName = f.getName(); 
		//System.out.println(fileName);
		//ͨ���ļ����ֹ�����  ���ļ�����ͳһ   
		String imgName = FilenameUtils.getName(fileName);
		//����ͼƬ�ļ�����
		File img = new File(path, imgName);
		//�õ�ͼƬ��·��
		String imgPath = path+"\\"+imgName;
		
		//��·����װ���û���Ϣ��
		if(f.getFieldName().equals("finger1")){
			finger fg = new finger();
			fg.setFinger_img(imgPath);
			/*
			 * 
			 * ����Ӧ�õ���ָ��ģ�����ָ��ͼƬ·����ȡ����ֵ��װ��fg��
			 * 
			 */
			//��ʱָ��һ��
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
		
		//��ͼƬд��
		try {
			f.write(img);
			//�������
			f.delete();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * ��ͨ���
	 * ������д����Ϣ��װ
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
        	u.setuSex("��");
        }else if("female".equals(name)){
        	u.setuSex("Ů");
        }else if("age".equals(name)){
        	//����ת��
        	u.setuAge(Integer.parseInt(value));
        }else if("tel".equals(name)){
        	u.setuTel(value);
        }
        	
	}

}
