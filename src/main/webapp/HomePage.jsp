<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome Page</title>

<style>
	*{
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	}
	
	.container{
	height: 100vh;
	width: 100vw;
	background-image: url('https://img.freepik.com/premium-photo/colorful-grunge-abstract-minimal-background_250469-4315.jpg');
	background-repeat: no-repeat;
	background-position: center;
	background-size: cover;
	}
	
	.navbar{
	height: 10vh;
	width: 100vw;
	display: flex;
	align-items: center;
	justify-content: space-between;
	}
	li{
	list-style: none;
	display: inline-block;
	margin-left: .6rem;
	font-size: 1.2rem;
	font-weight: bold;
	}
	
	.btn{
		color: white;
		background-color: blue;
		height: 30px;
		width: 90px;
		border-radius: .5rem;
	}
	
	.btn-switch{
	width: 180px;
	}
	.main{
		height: 90vh;
		width: 100vw;
		display: flex;
		align-items: center;
		justify-content: center;
	}
	
	.heading{
		font-size: 100px;
	}
</style>
</head>
<body>


	<div class="container">
		<div class="navbar">
			<img height="80px" alt="logo" src="https://cutewallpaper.org/24/admin-png/admin-png-images-transparent-free-download-pngmartcom.png" />
			<ul>
				<li><a href="<%=request.getContextPath() %>/HomePage.jsp">Home</a></li>
				<li><a href="<%=request.getContextPath() %>/addAccountant.jsp">Add Accountant</a></li>
				<li><a href="<%=request.getContextPath() %>/listAccountant.jsp">List Accountant</a></li>
				<li><form action="<%=request.getContextPath() %>/loginAuthentication?action=logout" method="post">
						<input type="hidden" name="logout" />
						<button class="btn">logout</button>
					</form></li>
			</ul>
		</div>
		<div class="main">
			<h1 class="heading">Welcome to homepage</h1>
		</div>
	</div>
	
</body>
</html>