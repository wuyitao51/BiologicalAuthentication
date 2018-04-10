package com.sicau.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.sicau.dao.usersDao;
import com.sicau.javabean.usersForSearch;

public class doUpdateServlet extends HttpServlet {

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
		doPost(request, response);
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
		
		//当前表格显示的用户信息
		ArrayList<usersForSearch> usersList = (ArrayList<usersForSearch>) request.getSession().getAttribute("users");
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		File f = new File("G://");
		factory.setRepository(f);
		ServletFileUpload upload = new ServletFileUpload(factory);
		usersDao usd = new usersDao();
		String s = "";
		int len = 0;
		try {
			List <FileItem>fileItems = upload.parseRequest(request);
			//所有的组件
			int i = 0, j = 0;//i控制循环，j代表正在对第几行数据进行操作
			//遍历所有的组件
			//不-1要报越界异常
			while(i<(fileItems.size()-1)){				
				//当第一个为勾选框
				if(fileItems.get(i).getFieldName().equals("choose")){
					String sex = "";
					String age = "";
					String tel = "";
					//这一行是需要提交的
					//下一个是姓名框
					String name = fileItems.get(i+1).getString("UTF-8");
					if(fileItems.get(i+2).getFieldName().equals("sex")){
						//后面的是性别框
						sex = fileItems.get(i+2).getString("UTF-8");
						age = fileItems.get(i+3).getString("UTF-8");
						tel = fileItems.get(i+4).getString("UTF-8");
						i += 5;
					}else{
						//后面的不是性别框，那么应该是年龄，再后面是电话
					    age = fileItems.get(i+2).getString("UTF-8");
						tel = fileItems.get(i+3).getString("UTF-8");
						i += 4;
					}
					String id = usersList.get(j).getId();
					s += "对用户"+id+"：";
					//System.out.println("id:"+id+"name:"+name+"sex:"+sex+"age:"+age+"tel:"+tel);
					if(!name.equals("")){
						if(usd.updateNamebyId(id, name)){
							s+="成功将姓名更新为"+name+"  ";
						}else{
							s += "更新姓名失败  ";
						}
					}
					if(!sex.equals("")){
						String uSex = "女";
						if(sex.equals("male")){
							uSex = "男";
						}
						if(usd.updateSexbyId(id, uSex)){
							s += "成功将性别更新为"+uSex+"  ";
						}else{
							s += "更新性别失败  ";
						}
					}
					if(!age.equals("")){
						int uAge = Integer.parseInt(age);
						if(usd.updateAgebyId(id, uAge)){
							s += "成功将年龄更新为"+age+"  ";
						}else{
							s += "更新年龄失败  ";
						}
					}
					if(!tel.equals("")){
						if(usd.updateTelbyId(id, tel)){
							s += "成功将电话更新为"+tel+"  ";
						}else{
							s += "更新电话失败";
						}
					}
					s += "\n";
				}else{
				//第一个不是勾选框，那么这一个应当是姓名输入框
				//忽略这一行
					//这一行是否勾选了性别栏
					if(fileItems.get(i+1).getFieldName().equals("sex")){
						//这一行是否勾选了性别栏
						//姓名框后面还有性别、年龄、电话
						i += 4;
					}else{
						//这一行没有勾选了性别栏
						//姓名框后面还有年龄、电话
						i += 3;
					}
				}
				j++;
			}
	
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(s);
		request.setAttribute("msg", "update");
		request.setAttribute("notify", s);
		request.getRequestDispatcher("admin.jsp").forward(request, response);
	}

}
