package com.sicau.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sicau.dao.usersDao;

public class changeWeightServlet extends HttpServlet {

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
		response.setCharacterEncoding("UTF-8");
		/*
		 * 根据id对用户提（降）权
		 * 如果是普通用户，提权为管理员
		 * 如果是管理员，降权为普通用户
		 * 超级管理员（唯一）不能对本身作提权降权操作
		 * 如果普通用户还没有录入验证信息或验证信息不全，那么无法对该用户进行提权
		 */
		String id = request.getParameter("id");
		String permi = request.getParameter("permission");
		String finger = request.getParameter("finger");
		String face = request.getParameter("face");
		System.out.println("id="+id+",permission="+permi+",fingerexist:"+finger+",faceexist"+face);
		
		usersDao usd = new usersDao();
		PrintWriter out = response.getWriter();

		if(permi.equals("超级管理员")){
			out.print("您无法对超级管理员进行操作!");
		}else if(permi.equals("管理员")){
			//将该用户权限值设为2，即普通用户
			if(usd.updatePermibyId(id, 2)){
				out.print("已成功将管理员用户："+id+"降权为普通用户");
			}else{
				out.print("对管理员用户："+id+"降权操作失败");
			}
		}else{
			if(finger.equals("否") || face.equals("否")){
				out.print("该用户验证信息不完整，无法进行提权操作");
			}else{
				if(usd.updatePermibyId(id, 1)){
					out.print("已成功将普通用户："+id+"提权为管理员");
				}else{
					out.print("对普通用户："+id+"提权操作失败");
				}
			}
		}
	}

}
