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
		String fingerPath1 = "";
		String fingerPath2 = "";
		String fingerPath3 = "";
		String facePath1 = "";
		String facePath2 = "";
		String facePath3 = "";
		String facePath4 = "";
		String facePath5 = "";
		//��ȡ�������
		try {
			List<FileItem> fileItems = upload.parseRequest(request);
			for(FileItem F : fileItems){
				//ȷ�����ϴ����
				if(!F.isFormField()){
					String path=uploadFileItem(F);//�õ�ͼƬ������·��
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
		
		//�õ��˰˸�ͼƬ��Ϣ��·��
		System.out.println("ָ��1��"+fingerPath1+"\n"+"ָ��2��"+fingerPath2+"\n"+"ָ��3��"+fingerPath3+"\n"+
				"�沿1��"+facePath1+"\n"+"�沿2��"+facePath2+"\n"+"�沿3��"+facePath3+"\n"+"�沿4��"+facePath4+"\n"+"�沿5��"+facePath5+"\n");
		
		//Ϊָ��id���û�¼��ͼƬ��Ϣ
		String id = request.getSession().getAttribute("currentId").toString();
		System.out.println(id);
		
		/*
		 * �����Ѿ��õ��˰˸�·�����û���id
		 * �жϸ��û��Ƿ�����Ӧ����Ϣ
		 * �������ô���Ǹ���
		 */
		
		usersDao usd = new usersDao();
		doAddAuthenInfoService service = new doAddAuthenInfoService();
		
		boolean fingerRemoveResult = true;
		if(usd.selectInfo(id).getIsFingerExist().equals("��")){
			//��ָ����Ϣ�����£���ɾ�����е��ٲ���
			fingerDao fgd = new fingerDao();
			fingerRemoveResult = fgd.deleteByUserId(id);
		}
		boolean fingerResult = false;
		if(fingerRemoveResult){
			fingerResult = service.fingerInsert(id, fingerPath1)&&service.fingerInsert(id, fingerPath2)&&service.fingerInsert(id, fingerPath3);
		}
		
		boolean faceRemoveResult = true;
		if(usd.selectInfo(id).getIsFaceExist().equals("��")){
			//��������Ϣ
			faceDao fcd = new faceDao();
			faceRemoveResult = fcd.deleteByUserId(id);
		}
		boolean faceResult = false;
		if(faceRemoveResult){
			faceResult = service.faceInsert(id, facePath1)&&service.faceInsert(id, facePath2)&&service.faceInsert(id, facePath3)
								  &&service.faceInsert(id, facePath4)&&service.faceInsert(id, facePath5);
		}
		
		if(fingerResult&&faceResult){
			//������Ϣ�������³ɹ�
			request.setAttribute("addsuccess", "success");
		}else{
			//����Ϣ����ʧ��
			request.setAttribute("addfail", "fail");
		}
		request.getRequestDispatcher("admin.jsp").forward(request, response);
	}
	
	//����ͼƬ��·��
	private String uploadFileItem(FileItem f){
		/*
		 * 1.��ȡͼƬ����
		 * 2.�ڷ������д���ͼƬ���������(�ļ���)
		 * 3.����ͼƬ
		 */
		//�ļ��ĳ���·��
		String path = this.getServletContext().getRealPath("/WEB-INF/upload");//����·��
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
