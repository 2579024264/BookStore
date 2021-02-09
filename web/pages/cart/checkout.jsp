<!DOCTYPE html>
<html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
<meta charset="UTF-8">
<title>结算页面</title>
	<base href="http://localhost:8080/book/">
<link type="text/css" rel="stylesheet" href="static/css/style.css">
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
			<span class="wel_word">结算</span>
			<div>
				<span>欢迎<span class="um_span">${sessionScope.user}</span>光临尚硅谷书城</span>
				<a href="orderServlet?action=myorder">我的订单</a>
				<a href="userServlet?action=loginout">注销</a>&nbsp;&nbsp;
				<a href="pages/cart/cart.jsp">返回</a>
			</div>
	</div>
	
	<div id="main">
		
		<h1>你的订单已结算，订单号为${sessionScope.orderId}</h1>
		
	
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>