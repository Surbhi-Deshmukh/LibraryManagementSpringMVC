<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="com.nagarro.assgn5frontend.service.BookService"%>
<%@page import="com.nagarro.assgn5frontend.entity.Book"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page errorPage="ErrorPage.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<link rel="stylesheet" href="resources/styles/homePageStyle.css"
	type="text/css" />
<link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css"
	type="text/css" />


</head>
<body>
	<%
		List<Book> books = (List<Book>) request.getAttribute("allbooks");
	%>


	<%@ include file="header.jsp"%>

	<div class="row px-3 py-3 mb-4">
		<div class="col-md-10 text-center">
			<h2>Books Listing</h2>
		</div>
		<div class="col-md-2">
			<input type="submit" value="Add a book" class="btn btn-primary"
				onclick="location.href='add'">
		</div>
	</div>


	<div class="container">
		<table>
			<thead>
				<tr>
					<th>Book Code</th>
					<th>Book Name</th>
					<th>Author</th>
					<th>Date Added</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<%
					for (Book book : books) {
				%>
				<tr>
					<td><%=book.getBookCode()%></td>
					<td><%=book.getBookName()%></td>
					<td><%=book.getAuthor().getName()%></td>
					<td><%=new SimpleDateFormat("dd MMMM yyyy").format(book.getAddedDate())%></td>
					<td class="action"><input type="submit"
						class="btn btn-outline-secondary btn-sm action-btn me-1"
						value="Edit"
						onclick="location.href='edit?id=<%=book.getBookCode()%>'">
						<input type="submit"
						class="btn btn-outline-danger btn-sm action-btn" value="Delete"
						onclick="location.href='delete/<%=book.getBookCode()%>'">
					</td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
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