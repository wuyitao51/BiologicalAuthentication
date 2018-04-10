package com.sicau.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.sicau.utils.faceUtils;

import sun.misc.BASE64Decoder;

public class recogByFaceServlet extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public recogByFaceServlet() {
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
		
		String uri = request.getParameter("img");
		System.out.println(uri);
		String img = uri.replaceAll("data:image/jpeg;base64,", "");//��ͷ
		String imgpath = GenerateImage(img);
		System.out.println(imgpath);
		faceUtils face = new faceUtils();
		JSONObject obj = face.face_rec(imgpath);//�õ����ص�JSON����;
		System.out.println(obj.toString());
		PrintWriter out = response.getWriter();
		
		try{
			String id = obj.getJSONArray("result").getJSONObject(0).getString("uid");//ͼƬ��Ӧ��id
			String score = obj.getJSONArray("result").getJSONObject(0).get("scores").toString();//ͼƬ��Ӧ�ķ���
			System.out.println(id);
			System.out.println(score);
			if(id!=null && score!=null){
				if(score.charAt(3)=='.'&&score.charAt(1)>=6){
					//���ִ��ڵ���60
					request.getSession().setAttribute("idByFace_recog", id);
					out.print(id);
				}else{
					out.print("failed");
				}
			}else{
				out.print("failed");
			}
		}catch(Exception e){
			out.print("failed");
		}finally{
			//ɾ���ļ�
			File f = new File(imgpath);
			f.delete();
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

	private String GenerateImage(String imgStr)  
    {   //���ֽ������ַ�������Base64���벢����ͼƬ  
        if (imgStr == null) //ͼ������Ϊ��  
            return null;  
        BASE64Decoder decoder = new BASE64Decoder();  
        try   
        {  
            //Base64����  
            byte[] b = decoder.decodeBuffer(imgStr);  
            for(int i=0;i<b.length;++i)  
            {  
                if(b[i]<0)  
                {//�����쳣����  
                    b[i]+=256;  
                }  
            }  
            //����JPEGͼƬ  
            String imgFilePath = "C:\\imgtest\\new.jpg";//�����ɵ�ͼƬ  
            OutputStream out = new FileOutputStream(imgFilePath);      
            out.write(b);  
            out.flush();  
            out.close();  
            return imgFilePath;  
        }   
        catch (Exception e)   
        {  
            return null;  
        }  
    }
}
