package com.online.food.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.online.food.deligates.UserLoginDeligates;
import com.online.food.vo.UserLoginVo;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserLoginVo userlogVo = null;
		UserLoginDeligates userlogDeligate = null;
		RequestDispatcher rd=null;
		PrintWriter out=null;
		String email=null;
		String username = null;
		String password=null;
		System.out.println("hi");
		response.setContentType("text/html");
		email = request.getParameter("email");
		password = request.getParameter("password");
		out=response.getWriter();
		userlogVo=new UserLoginVo();
		userlogVo.setEmail(email);
		userlogVo.setPassword(password);
		userlogDeligate = new UserLoginDeligates();
		System.out.println("hi2");
		try {
		String userValidate = UserLoginDeligates.authenticateUser(userlogVo);
		System.out.println(userValidate);
		if(userValidate.equals("user role")){
			System.out.println(userValidate);
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(10*60);
			session.setAttribute("email", email);
			request.setAttribute("email", email);
			System.out.println(userValidate);
			request.getServletContext().getRequestDispatcher("/userHomepage.jsp").forward(request, response);
		}
		else if(userValidate.equals("admin role")) {
			System.out.println(userValidate);
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(10*60);
			session.setAttribute("admin", username);
			request.setAttribute("username", username);
			System.out.println(userValidate);
			request.getServletContext().getRequestDispatcher("/admin.jsp").forward(request, response);
		}
		else {
			System.out.println("error");
			request.setAttribute("errMessege", userValidate);
			System.out.println("error");
			request.getServletContext().getRequestDispatcher("/LoginForm.jsp").forward(request, response);
			System.out.println("error");
			/* ((RequestDispatcher) request).include(request, response); */
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
