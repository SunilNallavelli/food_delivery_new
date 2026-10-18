<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Online Food Delivery</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<!-- <link rel="stylesheet" type="text/css" href="css/mobile-style.css"> -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
</head>
<body>


<br>
	<!-- <h1>Online Food Delivery</h1> -->
	<header>
		<div class="container-fluid p-0">
			<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="navbar-brand">
						<a href="index.html"><h1>FooD</h1></a>
					</div>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
  	<div class="mr-auto"></div>
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link" href="#">menu</a>
        <div class="dropdown-content">
        	<a href="veg.jsp">veg</a>
        	<a href="nonveg.jsp">Non-veg</a>
        </div>
      </li>
       <li class="nav-item">
        <a class="nav-link" href="#">about</a>
      </li>
       <li class="nav-item">
        <a class="nav-link" href="#">services</a>
      </li>
       <li class="nav-item">
        <a class="nav-link" href="#">contact</a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link" href="userHomePage.jsp"><%= request.getSession().getAttribute("email") %></a>
        <div class="dropdown-content">
        	<a href="<%=request.getContextPath()%>/YourOrdersController">your orders</a>
        	
        	<a href="<%=request.getContextPath()%>/LogoutController">logout</a>
        </div>
      </li>
     
      
     
    </ul>
    <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
  </div>
</nav>
</div>
		<div class="container text-center">
			<div class="row">
				<div class="col-md-7 col-sm-12">
					
					<h1>Your Order is Successfully Placed Check In Your Orders Section</h1>
					
				</div>
				
			</div>
		</div>
</header>
<h1></h1>
</body>