<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <meta name="Generator" content="EditPlus®">
  <meta name="Author" content="">
  <meta name="Keywords" content="">
  <meta name="Description" content="">
  <title>Document</title>
  <link rel="stylesheet" href="css/cust.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css">
 </head>
 <body>
 	<!---form area start-->
	<div class="form">
	<!---login form start-->
	<form class="login-form" action="./Login" method="post">
	<h2>login</h2>
	<div class="icons">
		<a href="#"><i class="fab fa-facebook"></i></a>
		<a href="#"><i class="fab fa-google"></i></a>
		<a href="#"><i class="fab fa-twitter"></i></a>
	</div>
	<input type="text" name="email" value=""placeholder="Email" required>
	<input type="password" name="password" value="" placeholder="Password" required><br>
	<span style="color:red">
				<%= (request.getAttribute("errMessege")==null) ? "" 
				: request.getAttribute("errMessege") %>
				</span>
				<br>
	<button type="submit" name="button">Login</button>
	<p class="options"><a href="forgotPassword.jsp">forgot Password? </a></p>
	<p class="options">Not Registered? <a href="userRegistrationForm.jsp">Create an Account</a></p>
	</form>
  </div>
 </body>
</html>
