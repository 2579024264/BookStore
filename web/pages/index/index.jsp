<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城首页</title>
    <base href="http://localhost:8080/book/">
    <link type="text/css" rel="stylesheet" href="static/css/style.css" >
    <script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
    <script type="text/javascript">
        $(function () {
            //给加入购物车按钮绑定单击事件
            $("button.addtoCart").click(function () {
                //获取元素的属性值使用attr
                var bookId=$(this).attr("bookId");
                //发ajax请求，添加商品到购物车
                $.getJSON("http://localhost:8080/book/cartServlet","action=ajaxItem&id="+bookId,function (data) {
                    $("#cartTotalCount").text("您的购物车中有"+data.totalCount+"件商品");
                    $("#cartLastName").text(data.lastName);

                })
            })
        })
    </script>
</head>
<body>

<div id="header">
    <span class="wel_word">当当书城</span>
<%--    如果用户还未登录--%>
    <div>
    <c:if test="${empty sessionScope.user}">
            <a href="pages/user/login.jsp">登录</a> |
            <a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
    </c:if>
<%--    如果用户已经登录--%>
    <c:if test="${not empty sessionScope.user}">
        <span>欢迎<span class="um_span"></span>${sessionScope.user}&nbsp;&nbsp;光临当当书城</span>
        <a href="orderServlet?action=myorder">我的订单</a>
        <a href="userServlet?action=loginout">注销</a>&nbsp;&nbsp;
    </c:if>
    <a href="pages/cart/cart.jsp">购物车</a>
    <a href="pages/manager/manager.jsp">后台管理</a>&nbsp&nbsp&nbsp
    </div>

</div>
<div id="main">
    <div id="book">
        <div class="book_cond">
            <form action="client/bookServlet" method="get">
                <input type="hidden" name="action" value="pageByPrice">
                价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
                <input id="max" type="text" name="max" value="${param.max}"> 元
                <input id="search" type="submit" value="查询" />
            </form>
        </div>
        <div style="text-align: center">
<%--            <c:choose>--%>
                <c:if test="${empty sessionScope.cart.items}">
                    <span id="cartTotalCount"></span>
                    <div>
                        <span id="cartLastName" style="color: red">当前购物车为空</span>
                    </div>
                </c:if>
                <c:if test="${not empty sessionScope.cart.items}">
                    <span id="cartTotalCount">您的购物车中有${sessionScope.cart.totalCount}件商品</span>
                    <div>
                        您刚刚将<span id="cartLastName" style="color: red">${sessionScope.lastName}</span>加入到了购物车中
                    </div>
                </c:if>
<%--            </c:choose>--%>
        </div>


        <c:forEach items="${requestScope.page.items}" var="book">
        <div class="b_list">
            <div class="img_div">
                <img class="book_img" alt="" src="static/img/default.jpg" />
            </div>
            <div class="book_info">
                <div class="book_name">
                    <span class="sp1">书名:</span>
                    <span class="sp2">${book.name}</span>
                </div>
                <div class="book_author">
                    <span class="sp1">作者:</span>
                    <span class="sp2">${book.author}</span>
                </div>
                <div class="book_price">
                    <span class="sp1">价格:</span>
                    <span class="sp2">${book.price}</span>
                </div>
                <div class="book_sales">
                    <span class="sp1">销量:</span>
                    <span class="sp2">${book.sales}</span>
                </div>
                <div class="book_amount">
                    <span class="sp1">库存:</span>
                    <span class="sp2">${book.stock}</span>
                </div>
                <div class="book_add">
                    <button bookId="${book.id}" class="addtoCart">加入购物车</button>
                </div>
            </div>
        </div>
        </c:forEach>
    </div>

    <div id="page_nav">
        <%--        大于首页才显示--%>
        <c:if test="${requestScope.page.pageNo>1}">
            <a href="${requestScope.page.url}&pageNo=1">首页</a>
            <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">上一页</a>
        </c:if>

        <%--      页码输出的开始--%>
        <c:choose>
            <%--        情况1.如果总页码小于等于5的情况，页码的范围是：1-总页码--%>
            <c:when test="${requestScope.page.pageTotal<=5}">
                <c:forEach begin="1" end="${requestScope.page.pageTotal}" var="i">
                    <c:if test="${i==requestScope.page.pageNo}">
                        【${i}】
                    </c:if>
                    <c:if test="${i!=requestScope.page.pageNo}">
                        <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                    </c:if>
                </c:forEach>
            </c:when>

            <%--            情况2.如果总页码大于5的情况，--%>
            <c:when test="${requestScope.page.pageTotal>5}">
                <c:choose>
                    <c:when test="${requestScope.page.pageNo<=3}">
                        <c:forEach begin="1" end="5" var="i">
                            <c:if test="${i==requestScope.page.pageNo}">
                                【${i}】
                            </c:if>
                            <c:if test="${i!=requestScope.page.pageNo}">
                                <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                            </c:if>
                        </c:forEach>
                    </c:when>
                    <c:when test="${requestScope.page.pageNo>requestScope.page.pageTotal-3}">
                        <c:forEach begin="${requestScope.page.pageTotal-4}" end="${requestScope.page.pageTotal}" var="i">
                            <c:if test="${i==requestScope.page.pageNo}">
                                【${i}】
                            </c:if>
                            <c:if test="${i!=requestScope.page.pageNo}">
                                <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                            </c:if>
                        </c:forEach>
                    </c:when>
                    <%--                    页码范围是：当前页码减2，当前页码加2--%>
                    <c:otherwise >
                        <c:forEach begin="${requestScope.page.pageNo-2}" end="${requestScope.page.pageNo+2}" var="i">
                            <c:if test="${i==requestScope.page.pageNo}">
                                【${i}】
                            </c:if>
                            <c:if test="${i!=requestScope.page.pageNo}">
                                <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
                            </c:if>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </c:when>
        </c:choose>
        <%--    页码输出的结束--%>
        <c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal}">
            <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">下一页</a>
            <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
        </c:if>
        共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
        到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
        <input id="searchPageBtn" type="button" value="确定">
    </div>

</div>


</body>
</html>