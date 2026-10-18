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
import javax.servlet.http.HttpSession;

import com.online.food.util.DBConnection;

/**
 * Servlet implementation class YourOrdersController
 */
@WebServlet("/YourOrdersController")
public class YourOrdersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public YourOrdersController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter();
		response.setContentType("text/html");
		
		HttpSession s=request.getSession();
		String email= (String)s.getAttribute("email");
		System.out.println(email);
		Connection con=null;
		PreparedStatement ps = null;
		ResultSet rs;
		int loginId=0;
		int regId=0;
		int itemid=0;
		int qty=0;
		String itemname=null;
		int price=0;
		int delivery=0;
		try {
			con=DBConnection.getDBConnection();
			
			//loginid
			ps=con.prepareStatement("select loginid from mydb.tab_login where email=?");
			ps.setString(1, email);
			rs=ps.executeQuery();
			while(rs.next()) {
	           loginId=rs.getInt(1);
			}
				
			//regid
			ps=con.prepareStatement("select registerid from mydb.tab_register where tab_login_loginid=?");
			ps.setInt(1, loginId);
			rs=ps.executeQuery();
			while(rs.next()) {
	            regId=rs.getInt(1);
			}
			String s1="select itemname,price,deliverycharge,quentity from mydb.tab_items,mydb.tab_orders where mydb.tab_items.itemid = mydb.tab_orders.tab_items_itemid AND mydb.tab_orders.tab_register_registerid=?";
			ps=con.prepareStatement(s1);
			ps.setInt(1, regId);
			rs=ps.executeQuery();
			int serialNumber=1;
			 pw.println("<table border=1 width=50% height=50%>");  
			pw.print("<tr><th>Serial number</th><th>Item Name</th><th>Price</th><th>delivrcharge</th><th>Qty</th><th>Total Amt. (Delivery charges added/-)</th></tr>");
			while(rs.next()) {
	            itemname=rs.getString(1);
	            price=rs.getInt(2);
	            qty=rs.getInt(4);
	            delivery = rs.getInt(3);
	            
	            pw.println("<tr style='nth-child(even) background-color: #CCC;'><td>"+(serialNumber++)+"</td><td>"+itemname+"</td><td>"+price+"</td><td>"+delivery+"</td><td>"+qty+"</td><td>"+(price*qty+delivery)+"</td></tr>");
	            
			}
						
		}catch(Exception e) {
			e.printStackTrace();
		}
		}
								
	}
	
