<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="UTF-8">
<title>编辑图书</title>
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
	
	input {
		text-align: center;
	}
</style>
</head>
<body>
		<div id="header">
<%--			<img class="logo_img" alt="" src="static/img/logo.gif" >--%>
			<span class="wel_word">编辑图书</span>
			<div>
				<a href="book_manager.jsp">图书管理</a>
			</div>
		</div>
		
		<div id="main">
			<form action="manager/bookServlet" method="get">
<%--				添加一个学生信息到末页的最后，此时，pageNo的值是最后一页，pageTotal的值--%>
				<input type="hidden" name="pageNo" value="${param.pageNo}">
<%--	修改一个学生信息，保存当前的页码，修改完之后跳回到修改时的页面--%>

<%--				添加一个隐藏域，告诉服务器使用哪个方法--%>
				<input type="hidden" name="action" value="${empty param.id? "add":"update"}"/>
				<input type="hidden" name="id" value="${requestScope.book.id}">
				<table>
					<tr>
						<td>名称</td>
						<td>价格</td>
						<td>作者</td>
						<td>销量</td>
						<td>库存</td>
						<td colspan="2">操作</td>
					</tr>		
					<tr>
						<td><input name="name" type="text" value="${requestScope.book.name}"/></td>
						<td><input name="price" type="text" value="${requestScope.book.price}"/></td>
						<td><input name="author" type="text" value="${requestScope.book.author}"/></td>
						<td><input name="sales" type="text" value="${requestScope.book.sales}"/></td>
						<td><input name="stock" type="text" value="${requestScope.book.stock}"/></td>
						<td><input type="submit" value="提交"/></td>
					</tr>	
				</table>
			</form>
			
	
		</div>
</body>
</html>