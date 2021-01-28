<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商品信息</title>
    <style>
        .headline {
            text-align: center;
            font-size: 25px;
        }

        .table {
            text-align: center;
            margin: auto;
            width: 70%;
            font-size: 20px;
        }
    </style>
</head>
<body>
<div class="entirety">
    <p class="headline">
        商品信息
    </p>
    <hr>
    <table class="table">
        <tr>
            <th>名称</th>
            <th>库存</th>
            <th>价格</th>
            <th>操作</th>
        </tr>
        <%--遍历Session中存储的商品集合获取信息进行展示--%>
        <c:forEach items="${commodityListSession}" var="commodity">
            <tr>
                <td>
                        ${commodity.name}
                </td>
                <td>
                        ${commodity.amount}
                </td>
                <td>
                        ${commodity.price}
                </td>
                <td class="td_right">
                        <%--get请求方式发送商品编号至服务器--%>
                    <form action="${pageContext.request.contextPath}/AdditionServlet?number=${commodity.number}"
                          method="post">
                        <input type="number" name="addNumber" placeholder="请输入选购的数量">
                        <input type="submit" value="加入购物车">
                    </form>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="4">
                <hr>
            </td>
        </tr>
        <tr>
            <td colspan="3"></td>
            <td>
                <a href="shopping_cart.jsp"><span>查看购物车</span></a>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
