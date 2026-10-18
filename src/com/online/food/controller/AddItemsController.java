package com.online.food.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class AddItemsController
 */
@WebServlet("/AddItemsController")
@MultipartConfig
public class AddItemsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static final String FILE_PARAMETER = "img";
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter pw=response.getWriter();
		String name=request.getParameter("name");
		Double price=Double.parseDouble(request.getParameter("price"));
		Part filePart = request.getPart(FILE_PARAMETER);
		int delcharge=Integer.parseInt(request.getParameter("dc"));
		Double dis=Double.parseDouble(request.getParameter("dis"));
		
		
		InputStream fileContent = filePart.getInputStream();
		int flag=0;
		Connection con=null;
		PreparedStatement ps = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","root");
			String sql1="INSERT INTO mydb.tab_items(itemname,price,deliverycharge,discount,image) VALUES (?,?,?,?,?)";			
			ps=con.prepareStatement(sql1);
			ps.setString(1, name);
			ps.setDouble(2, price);
			ps.setInt(3,delcharge );
			ps.setDouble(4, dis);
			ps.setBlob(5, fileContent);
			/* System.out.println("image"+f.getAbsolutePath()); */
			flag=ps.executeUpdate();
				
			if (flag==1)
				pw.println("Item data inserted");
			else
				pw.println("not inserted");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
