package com.online.food.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.online.food.deligates.OrderDeligates;
import com.online.food.vo.OrdersPageVo;


@WebServlet("/UserOrdersPage")
public class UserOrdersPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		HttpSession s=request.getSession();
		String name=request.getParameter("name");
		String qty=request.getParameter("qty");
		String email= (String)s.getAttribute("email");	
		System.out.println(name);
		System.out.println(qty);
		System.out.println(email);
		
		OrdersPageVo vo= new OrdersPageVo();
		vo.setName(name);
		vo.setQuantity(qty);
		vo.setEmail(email);
		
		OrderDeligates od= new OrderDeligates();
		int x =od.parse(vo);
		
		if(x==1)
			request.getServletContext().getRequestDispatcher("/orderMessege.jsp").forward(request, response);
		else {
			System.out.println("not ok");
		request.getServletContext().getRequestDispatcher("/veg.jsp").forward(request, response);
		
		}
	}

}
