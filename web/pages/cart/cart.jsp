<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <base href="http://localhost:8080/book/">
    <link type="text/css" rel="stylesheet" href="static/css/style.css" >
    <script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
    <script type="text/javascript">
        $(function () {
        	//给删除购物车绑定单击事件
            $("a.deleteItem").click(function () {
                return confirm("你确定要删除【 "+$(this).parent().parent().find("td:first").text()+"】吗？");
            })
			//给清空购物车绑定单击事件
			$("#clearCart").click(function () {
				return confirm("你确定要清空购物车吗？");
			})
			//给输入框绑定change事件，当内容发生改变的时候触发事件
			$(".updateCount").change(function () {
				//获取商品的名称
				var name=$(this).parent().parent().find("td:first").text();
				//获取商品的数量
				var count=this.value;
				//下面进行判断，如果点击了取消，则恢复原先的商品数量，如果点击了确定则为新的商品数量
				if (confirm("你确定要将【"+name+"】的数量改为【"+count+"【吗")){
					var id=$(this).attr("bookId");
					//发起请求，给服务器保存修改
					location.href="http://localhost:8080/book/cartServlet?action=updateCount&count="+count+"&id="+id;

				}else{
					//点击了取消  this.defatultValut是该表单项Dom对象的属性。它表示默认的value属性值。
					this.value=this.defaultValue;
				}
			})
        })
    </script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<div>
				<span>欢迎<span class="um_span">${sessionScope.user}</span>光临当当书城</span>
				<a href="orderServlet?action=myorder">我的订单</a>
				<a href="userServlet?action=loginout">注销</a>&nbsp;&nbsp;
				<a style="color: black" href="index.jsp">返回</a>
			</div>
	</div>
	
	<div id="main">
	
		<table>

			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<c:if test="${empty sessionScope.cart.items}">
				<tr>
					<td colspan="5">
<%--						返回到主界面--%>
						<a href="index.jsp">亲，当前购物车为空！快快挑选您喜欢的商品吧！</a>
					</td>
				</tr>
			</c:if>
			<c:if test="${not empty sessionScope.cart.items}">
<%--				如果购物车为非空的情况--%>
<%--			遍历存储在session域中的购物车  显示在页面上--%>
			<c:forEach items="${sessionScope.cart.items}" var="entry">
				<tr>
					<td>${entry.value.name}</td>
					<td>
						<input
								class="updateCount" type="text"
								style="width: 80px" value="${entry.value.count}"
<%--								添加一个bookId属性，属性值为商品的id，修改商品数量的时候触发事件传递该参数--%>
								bookId="${entry.value.id}"
						>
					</td>
					<td>${entry.value.price}</td>
					<td>${entry.value.totalPrice}</td>
<%--					删除购物车中的商品项--%>
					<td><a class="deleteItem" href="cartServlet?action=deleteItem&id=${entry.value.id}">删除</a></td>
				</tr>
			</c:forEach>
			</c:if>
		</table>
<%--		判断当购物车为空的时候，下面的内容将不进行展示--%>
		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span"><a id="clearCart" href="cartServlet?action=clear">清空购物车</a></span>
				<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>
	</div>
</body>
</html>