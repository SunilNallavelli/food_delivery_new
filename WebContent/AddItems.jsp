<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
body{
background-color: #ccc;}
.container {
margin:100px 100px 100px 100px;
  width: 500px;
  clear: both;
}

.container input {
  width: 100%;
  clear: both;
}</style>
</head>
<body>
	
	
	


<div class="container">
<h2>Add Items </h2>
    <form action="./addItem" method="post" enctype="multipart/form-data">
      <label>Item Name</label>
      <input type="text" name="name"><br /><br />
      <label>Item Price</label>
      <input type="text" name="price"><br /><br />
      <label>Item Delivery Charge</label>
      <input type="text" name="dc"><br /><br />
      <label>Item Discount</label>
     <input type="text" name="dis"><br /><br />
      <label>Item image</label>
      <input type="file" name="img"><br /><br />
      <input type="submit" value="INSERT ITEM"><br />
    </form>
  </div>




</body>
</html>