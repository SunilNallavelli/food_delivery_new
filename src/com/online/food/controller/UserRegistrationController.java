package com.online.food.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.online.food.deligates.UserRegistrationDeligates;
import com.online.food.vo.UserRegisterVo;

@WebServlet("/UserRegistrationController")
public class UserRegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* String role = "customer"; */
		String fName=null;
		String lName=null;
		String email=null;
		String pass=null;
		String dob =null;
		String mno =null;
		String flatNum =null;
		String apartmentNum = null;
		String landmark = null;
		String pincode = null;
		String city =null;
		String state = null;
		String country = null;
		UserRegisterVo userRegVo = null;
		UserRegistrationDeligates userRegDeligate = null;
		boolean flag=false;
		PrintWriter out=null;
		
		
		response.setContentType("text/html");
		fName=request.getParameter("fname");
		lName=request.getParameter("lname");
		email=request.getParameter("email");
		pass=request.getParameter("password");
		dob = request.getParameter("dob");
		mno=request.getParameter("mno");
		flatNum=request.getParameter("flat");
		apartmentNum=request.getParameter("apartment");
		landmark=request.getParameter("landmark");
		pincode=request.getParameter("pincode");
		city=request.getParameter("city");
		state=request.getParameter("state");
		country=request.getParameter("country");
		out=response.getWriter();
		userRegVo=new UserRegisterVo();
		/* userRegVo.setRole(role); */
		userRegVo.setfName(fName);
		userRegVo.setlName(lName);
		userRegVo.setEmail(email);
		userRegVo.setPass(pass);
		userRegVo.setDob(dob);
		userRegVo.setMno(mno);
		userRegVo.setFlatNum(flatNum);
		userRegVo.setApartmentNum(apartmentNum);
		userRegVo.setLandmark(landmark);
		userRegVo.setPincode(pincode);
		userRegVo.setCity(city);
		userRegVo.setState(state);
		userRegVo.setCountry(country);
		userRegDeligate = new UserRegistrationDeligates();
		flag=UserRegistrationDeligates.parse(userRegVo);
		out.println("<html>");
		out.println("<body>");
		if(flag==true){
			 out.println("<h3>registration successfull..<h3>"); 
			RequestDispatcher rd=getServletContext().getRequestDispatcher("/LoginForm.jsp");
			rd.include(request, response);
		}else{
			
			out.println("<h3>registration fail...<h3>");
		}
		out.println("</body>");
		out.println("</html>");
		
	}

}
