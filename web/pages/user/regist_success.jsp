<!DOCTYPE html>
<html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
<meta charset="UTF-8">
<title>当当书城注册页面</title>
	<!--写base标签，永远固定相对路径跳转的结果-->
	<base href="http://localhost:8080/book/">

<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
</style>
</head>
<body>
		<div id="header">
				<img class="logo_img" alt="" src="static/img/logo.gif" >
				<span class="wel_word"></span>
				<div>
					<span>欢迎<span class="um_span">${sessionScope.user}</span>光临尚硅谷书城</span>
					<a href="pages/order/order.html">我的订单</a>
					<a href="../../../../../../OneDrive/桌面/尚硅谷资料/javaweb资料/07-Servlet-2/代码/book/web/index.html">注销</a>&nbsp;&nbsp;
					<a href="pages/user/regist.jsp">返回</a>
				</div>
		</div>
		
		<div id="main">
		
			<h1>注册成功! <a href="index.jsp">转到主页</a></h1>
	
		</div>
</body>
</html>