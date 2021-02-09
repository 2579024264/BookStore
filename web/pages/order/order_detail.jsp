<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 86185
  Date: 2021/2/8
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单详情</title>
    <base href="http://localhost:8080/book/">
    <link type="text/css" rel="stylesheet" href="static/css/style.css" >
    <script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
</head>
<body>
    <div id="header">
        <img class="logo_img" alt="" src="static/img/logo.gif" >
        <span class="wel_word">订单详情</span>

        <%--		&lt;%&ndash;静态包含，登录 成功之后的菜单 &ndash;%&gt;--%>
        <%--		<%@ include file="/pages/common/login_success_menu.jsp"%>--%>
<%--        <div>--%>
<%--            <a href=""></a>--%>
<%--        </div>--%>

    </div>
    <div id="main">
        <table>
            <tr>
                <td>名称</td>
                <td>数量</td>
                <td>单价</td>
                <td>总价</td>
            </tr>
    <c:forEach items="${sessionScope.orderItems}" var="orderItem">
        <tr>
            <td>${orderItem.name}</td>
            <td>${orderItem.count}</td>
            <td>${orderItem.price}</td>
            <td>${orderItem.total_price}</td>
        </tr>
    </c:forEach>
    </table>
</div>>
</body>
</html>
