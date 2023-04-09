<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="Beans.myConn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
*{
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	}
	
	body{
	height:100vh;
	width:100vw;
	background-image: url('https://img.freepik.com/premium-photo/colorful-grunge-abstract-minimal-background_250469-4315.jpg');
	background-repeat: no-repeat;
	background-position: center;
	background-size: cover;
	}
	
	.container{
	height: 20vh;
	width: 100vw;

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
		color: black;
		background-color: red;
		height: 25px;
		width: 90px;
		border-radius: .5rem;
	}
	
	
	.heading{
		font-size: 100px;
	}
	table,tr,td{
		font-size: 20px;
		color: black;
		background-color: white;
		border: 1px solid black;
		padding: .5rem;
	}
	</style>
</head>
<body>
	<div class="container">
		<div class="navbar">
			<img height="80px" alt="logo" src="https://cutewallpaper.org/24/admin-png/admin-png-images-transparent-free-download-pngmartcom.png" />
			<ul>
				<li><a href="<%=request.getContextPath() %>/accountantDashborad.jsp">List Student</a></li>
				<li><a href="<%=request.getContextPath() %>/addStudent.jsp">Add Student</a></li>
				<li><form action="<%=request.getContextPath() %>/loginAuthentication?action=logout" method="post">
						<input type="hidden" name="logout" />
						<button class="btn">logout</button>
					</form></li></ul>
		</div>
		<div class="main">
				<h1>List Of Students</h1>
				<form action="<%=request.getContextPath()%>/CRUDStudentServlet?action=listStudent" method="post">
				<button class="btn">Show data</button>
			</form>
		</div>
	</div>
</body>
</html>