<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书管理</title>
    <base href="http://localhost:8080/book/">
    <link type="text/css" rel="stylesheet" href="static/css/style.css">
    <script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
    <script type="text/javaScript">
            $(function () {
                //给删除的a标签绑定单击事件，用于删除的确认提示操作
                $("a.deleteClass").click(function () {
                    /**
                     * confirm是确认提示框函数
                     * 参数是它的提示内容
                     * 它有两个按钮，一个确认，一个是取消
                     *返回true表示点击了，确认，返回false表示点击取消。
                     */
                    return confirm("确定要删除"+$(this).parent().parent().find("td:first").text()+"吗？");
                })

                //跳转到指定的页码
                $("#searchPageBtn").click(function () {
                    let pageNo=$("#pn_input").val();
                    //javaScript中提供了一个location地址栏对象
                    //它有一个属性名叫做href,他可以获取浏览器地址栏中的地址
                    //href属性可读，可写
                     window.location.href="http://localhost:8080/book/${requestScope.page.url}e&pageNo="+pageNo;
                });
            });

    </script>
</head>
<body>

<div id="header">
<%--    <img class="logo_img" alt="" src="static/img/logo.gif">--%>
    <span class="wel_word">图书管理系统</span>
    <div>
        <a href="pages/manager/manager.jsp">返回</a>
    </div>
</div>

<div id="main">
    <table>
        <tr>
            <td>名称</td>
            <td>价格</td>
            <td>作者</td>
            <td>销量</td>
            <td>库存</td>
            <td colspan="2">操作</td>
        </tr>
        <%--			requestScope.books是request域中获取的一个数据集合 ，var是遍历的每个元素--%>
        <c:forEach items="${requestScope.page.items}" var="book">
            <tr>
                <td>${book.name}</td>
                <td>${book.price}</td>
                <td>${book.author}</td>
                <td>${book.sales}</td>
                <td>${book.stock}</td>
                <td><a style="text-decoration: none" href="manager/bookServlet?action=getBook&id=${book.id}&pageNo=${requestScope.page.pageNo}">修改</a></td>
                <td><a style="text-decoration: none" class="deleteClass" href="manager/bookServlet?action=delete&id=${book.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
            </tr>
        </c:forEach>

        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td><a style="text-decoration: none" href="pages/manager/book_edit.jsp?pageNo=${requestScope.page.pageTotal}">添加图书信息</a></td>
        </tr>
    </table>
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
