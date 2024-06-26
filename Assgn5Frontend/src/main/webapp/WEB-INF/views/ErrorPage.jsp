<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isErrorPage="true" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error!</title>
<link rel="stylesheet" href="resources/styles/errorPage.css" type="text/css" />
</head>
<body>
		<div class="container">
			<img src="resources/icons/laptop.png" class="error-img" >
			<div class="sorry">Sorry!</div>
			<div class="wrong">Something Went Wrong !</div>
			<div class="row"><input type="submit" value="Go to Home" class="home-btn col-6" onclick="location.href='home'">
			<input type="submit" value="Logout" class="home-btn col-6" style="margin-left:10px;" onclick="location.href='logout'">
			</div>
		</div>
	

</body>
</html>