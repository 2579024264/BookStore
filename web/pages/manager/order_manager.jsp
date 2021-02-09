<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
	<base href="http://localhost:8080/book/">
 	<link type="text/css" rel="stylesheet" href="static/css/style.css" >
	<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
	<script type="text/javascript">
		$(function () {
			//给发货按钮绑定点击事件
			$("span.send").click(function () {
				var orderId=$(this).attr("orderId");
				//
				if (confirm("确定将订单【"+orderId+"】修改为发货吗？")){
					//发起Ajax请求，置为发货
					$.getJSON("http://localhost:8080/book/orderServlet","action=sendOrder&order_id="+orderId,function (data) {
						window.location.href="http://localhost:8080/book/orderServlet?action=managerOrder"
					});
				}
			})
		})
	</script>
</head>
<body>
	
	<div id="header">
			<span class="wel_word">订单管理系统</span>
			<div>
				<a href="manager/bookServlet?action=page">图书管理</a>
				<a href="index.jsp">返回商城</a>
			</div>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>详情</td>
				<td>发货</td>
				
			</tr>
			<c:forEach items="${sessionScope.orderList}" var="order">
				<tr>
					<td>${order.create_time}</td>
					<td>${order.price}</td>
					<td><a style="text-decoration: none" href="orderServlet?action=searchOrderItems&order_id=${order.order_Id}">查看详情</a></td>
					<c:choose>
						<c:when test="${order.status==0}">
							<td><span style="cursor: pointer" class="send" orderId="${order.order_Id}">点击发货</span></td>
						</c:when>
						<c:when test="${order.status==1}">
							<td><span style="cursor: pointer">已发货</span></td>
						</c:when>
					</c:choose>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>