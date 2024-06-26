<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>

<link rel="stylesheet" href="resources/styles/loginPageStyle.css"
	type="text/css" />
<link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css"
	type="text/css" />
</head>
<body>

	<div class="container mt-5">
		<div class="form-wrapper  border">
			<div class="h4">Login</div>
			<form action="authenticate" method="post" onsubmit="return validateLogin()">
				<div class="row">
					<div class="alert alert-danger px-1 py-1 my-3" id="msg" role="alert">UserId and Password length must be between 5 to 50 characters!</div>
				</div>
				<div class="row justify-content-center mb-3">
					<div class="col-md-2">User Id:</div>
					<div class="col-md-4">
						<input type="text" class="input-box" name="username" id="username">
					</div>
				</div>
				<div class="row justify-content-center">
					<div class="col-md-2">Password:</div>
					<div class="col-md-4">
						<input type="password" class="input-box" name="password" id="pass">
					</div>
				</div>	
				<div class="row mt-4 mb-4">
					<div class="offset-md-5">
						<input type="submit" value="Login" class="login-btn">
					</div>
				</div>
			</form>
		</div>
	</div>
	
	<script>
		const validateLogin = () =>{
			const userId = document.getElementById("username").value;
			const pass = document.getElementById("pass").value;
			const msg = document.getElementById("msg");
			if(userId.length < 5 || userId.length > 50 || pass.length < 5 || pass.length > 50){
				msg.style.display = "block";
				return false;
			}
			return true;	
		}
	
		
	</script>


</body>
</html>