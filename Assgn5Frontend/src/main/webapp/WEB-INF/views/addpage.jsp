<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.nagarro.assgn5frontend.service.AuthorService"%>
<%@page import="java.util.List"%>
<%@page import="com.nagarro.assgn5frontend.entity.Author"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page errorPage="ErrorPage.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Book</title>
<link rel="stylesheet" href="resources/styles/addpageStyle.css"
	type="text/css" />
<link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css"
	type="text/css" />
</head>
<body>

	<%List<Author> authors = (List<Author>)request.getAttribute("allauthors");
		Date currDate = new Date();
		String formattedDate = new SimpleDateFormat("dd MMMM yyyy").format(currDate);
	%>

	<%@ include file="header.jsp" %>
	
	<div class="container mt-4 px-5">
		<div class="row text-center ">
			<h3 class="mb-4">Add Book Details</h3>
		</div>
		<div class="form-wrapper">
			<form action="addbook" method="post">
				<div class="row">
					<div class="col-md-3">Book Code</div>
					<div class="col-md-6"><input type="text" class="input-box" name="code"></div>
				</div>
				<div class="row">
					<div class="col-md-3">Book Name</div>
					<div class="col-md-6"><input type="text" class="input-box" name="name"></div>
				</div>
				<div class="row">
					<div class="col-md-3">Author</div>
					<div class="col-md-6">
						<select class="select-box" name="authorId">
						<%for(Author author : authors){ %>
							<option value="<%=author.getId()%>"><%=author.getName() %></option>
						<%} %>
						</select>
					</div>
	
				</div>
				<div class="row">
					<div class="col-md-3">Added On</div>
					<div class="col-md-6">
						<input type="text" readonly class="form-control-plaintext" id="staticEmail" value="<%=formattedDate%>">
					</div>
				</div>
				
				<div class="row mt-5">
					<input type="submit" value="Submit" class="btn btn-primary col-1 me-4">
					<input type="button" value="Cancel" class="btn btn-danger col-1" onclick="location.href='home'">
				</div>
			</form>
		</div>
	</div>
	
	<footer class="bg-light text-center text-lg-start" style="margin-top: 68px;">
		<div class="text-center p-3"
			style="background-color: #E8F1FD; width: 100%">
			© 2020 Copyright: <a class="text-dark" href="home">Advance Java
				Assignment 5</a>
		</div>
	</footer>

</body>
</html>