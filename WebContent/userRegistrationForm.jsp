<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action ='./TestRegister' method="post">
	<table>
		<tr> <td> FirstName: </td> <td> <input type = "text" name="fname" placeholder="first name"> </td> </tr>
		<tr> <td> LastName: </td> <td> <input type="text" name="lname" placeholder="last name"> </td> </tr>
		<tr> <td> Email id: </td> <td> <input type="text" name="email" placeholder="email"> </td> </tr>
		<tr> <td> Password: </td> <td> <input type="password" name="password" placeholder="password"></td></tr>
		<tr> <td> DOB: </td> <td> <input type="date" name="dob" placeholder="dob"> </td> </tr>
		<tr> <td> Mobile Number: </td> <td> <input type="text" name="mno" placeholder="mno"> </td> </tr>
		<tr> <td> Flat Number: </td> <td> <input type="text" name="flat" placeholder="Flat"> </td> </tr>
		<tr> <td> Apartment Number: </td> <td> <input type="text" name="apartment" placeholder="Apartment"> </td> </tr>
		<tr> <td> Landmark: </td> <td> <input type="text" name="landmark" placeholder="landmark"> </td> </tr>
		<tr> <td> pincode: </td> <td> <input type="text" name="pincode" placeholder="pincode"> </td> </tr>
		<tr> <td> City: </td> <td> <input type="text" name="city" placeholder="city"> </td> </tr>
		<tr> <td> State: </td> <td> <input type="text" name="state" placeholder="state"> </td> </tr>
		<tr> <td> Country: </td> <td> <input type="text" name="country" placeholder="country"> </td> </tr>
		<tr> <td> <input type ='submit' value ='submit'> </td> </tr>
	</table>
	</form>
</body>
</html>