package com.online.food.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteController
 */
@WebServlet("/DeleteController")
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		String id = request.getParameter("itemid");
		System.out.println(id);
		int itemid=Integer.parseInt(id);
		
		
		int flag=0;
		Connection con=null;
		PreparedStatement ps = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","root");
			String sql1="DELETE FROM mydb.tab_items WHERE itemid=?";			
			ps=con.prepareStatement(sql1);
			ps.setInt(1,itemid);
			/* System.out.println("image"+f.getAbsolutePath()); */
			flag=ps.executeUpdate();
			if (flag==1) {
				
				request.getServletContext().getRequestDispatcher("/admin.jsp").forward(request, response);
				pw.println("Item data Updated");
			}
			else
				
				request.getServletContext().getRequestDispatcher("/delete.jsp").forward(request, response);
			pw.println("not Updated");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
