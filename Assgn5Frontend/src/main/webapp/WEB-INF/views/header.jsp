<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page errorPage="ErrorPage.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
<link rel="stylesheet" href="resources/styles/headerStyle.css"
	type="text/css" />
<link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css"
	type="text/css" />
</head>
<body>
	<%	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		if(session.getAttribute("user") == null){
			response.sendRedirect("login");
			return;
	}
		String user = session.getAttribute("user").toString();
	%>
	
	<div class="row header mb-4">
		<div class="col-md-8 h3">Library Management</div>
		<div class="col-md-4">
			<div class="row" >
				<div class="col h5">Welcome <%=user %> </div>
				<div class="col"><input type="submit" value="Logout" class="btn btn-outline-primary" onclick="location.href='logout'"></div>
			</div>
		</div>
		
	</div>
	
	

</body>
</html>