package com.online.food.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SelectItemsController
 */
@WebServlet("/SelectItemsController")
public class SelectItemsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int flag=0;
		Connection con=null;
		Statement stmt = null;
		ResultSet rs = null;
		PrintWriter out = response.getWriter();  
        response.setContentType("text/html");  
        out.println("<html><body>");  
		
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","root");
			String sql1="select * from mydb.tab_items";			
			stmt = con.createStatement();  
            rs = stmt.executeQuery(sql1); 
            out.println("<table border=1 width=50% height=50%>");  
             out.println("<tr><th>ItemId</th><th>ItemName</th><th>Price</th><th>Deliverycharge</th><th>Discount</th><th>Image</th><tr>");  
             while (rs.next()) 
             {  
                 int itemid = rs.getInt("itemid");
                 String itemname = rs.getString("itemname"); 
                 double price =rs.getDouble("price");
                 int delcharge = rs.getInt("deliverycharge");
                 double discount = rs.getDouble("discount");
                 byte[] imageData = rs.getBytes("image");
                 out.println("<tr><td>" + itemid + "</td><td>" + itemname + "</td><td>" + price + "</td><td>"+ delcharge + "</td><td>" + discount + "</td><td>" + imageData + "</td></tr>" );   
             }
             
             
             out.println("</table>");  
             out.println("</html></body>");  
             con.close();  
            }  
             catch (Exception e) 
            {  
             out.println("error");  
         }  
             
             
             
             
			/* System.out.println("image"+f.getAbsolutePath()); */
			
	}

}
