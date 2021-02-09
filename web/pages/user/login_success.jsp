<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="UTF-8">
<title>账号注册页面</title>
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
				<div>
<%--				获取保存到session域中的用户名--%>
					<span>欢迎<span class="um_span"></span>${sessionScope.user}光临当当书城</span>
					<a href="orderServlet?action=myorder">我的订单</a>
					<a href="userServlet?action=loginout">注销</a>&nbsp;&nbsp;
					<a href="pages/user/login.jsp">返回</a>
				</div>
		</div>
		
		<div id="main">
		
			<h1>欢迎回来 <a href="index.jsp">转到主页</a></h1>
	
		</div>

</body>
</html>