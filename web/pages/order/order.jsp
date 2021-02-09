<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>

	<base href="http://localhost:8080/book/">
	<link type="text/css" rel="stylesheet" href="static/css/style.css" >
	<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
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
			<span class="wel_word">我的订单</span>
			<div>
				<a href="index.jsp">返回</a>
			</div>
<%--		&lt;%&ndash;静态包含，登录 成功之后的菜单 &ndash;%&gt;--%>
<%--		<%@ include file="/pages/common/login_success_menu.jsp"%>--%>


	</div>
	<div id="main">
<%--		判断订单是否为空--%>
		<c:choose>
			<c:when test="${not empty sessionScope.orders}">
				<table>
					<tr>
						<td>日期</td>
						<td>金额</td>
						<td>状态</td>
						<td>详情</td>
					</tr>
					<c:forEach items="${sessionScope.orders}" var="order">
						<tr>
							<td>${order.create_time}</td>
							<td>${order.price}</td>
							<c:if test="${order.status==0}">
								<td>未发货</td>
							</c:if>
							<c:if test="${order.status==1}">
								<td>已发货</td>
							</c:if>
							<c:if test="${order.status==2}">
								<td>已签收</td>
							</c:if>
								<%--					进入订单项页面--%>
							<td><a style="text-decoration: none" href="orderServlet?action=searchOrderItems&order_id=${order.order_Id}">查看详情</a></td>
						</tr>
					</c:forEach>
				</table>
			</c:when>
			<c:when test="${empty sessionScope.orders}">
				<a  href="pages/cart/cart.jsp">您的订单是空的，点击去购物车。</a>
			</c:when>
		</c:choose>
	</div>


<%--	&lt;%&ndash;静态包含页脚内容&ndash;%&gt;--%>
<%--	<%@include file="/pages/common/footer.jsp"%>--%>


</body>
</html>