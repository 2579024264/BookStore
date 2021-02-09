<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理</title>
	<base href="http://localhost:8080/book/">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">后台管理系统</span>
			<div>
<%--				不能直接跳转到图书管理界面，得先访问bookServlet。--%>
<%--				list表示要执行的反射方法--%>
				<a href="manager/bookServlet?action=page">图书管理</a>
				<a href="orderServlet?action=managerOrder">订单管理</a>
				<a href="index.jsp">返回商城</a>
			</div>
	</div>
	
	<div id="main">
		<h1>欢迎管理员进入后台管理系统</h1>
	</div>

</body>
</html>