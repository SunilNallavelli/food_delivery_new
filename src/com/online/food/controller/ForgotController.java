package com.online.food.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.online.food.util.DBConnection;

/**
 * Servlet implementation class ForgotController
 */
@WebServlet("/ForgotController")
public class ForgotController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		try {
			Connection con=DBConnection.getDBConnection();
			String email=request.getParameter("email");
			String password=null;
			PreparedStatement ps=con.prepareStatement("select password from mydb.tab_login where email=?");
			ps.setString(1, email);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
	           password=rs.getString(1);
			}
			pw.print("<h3 style='color:red;margin-left:400px'> Your Password is:"+"<span style='color:green'> "+password+"</span></h3>");
	}catch (Exception e) {
		e.printStackTrace();
	}
	}
}
