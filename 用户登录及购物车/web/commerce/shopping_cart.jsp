<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购物车</title>
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
<p class="headline">
    购物车
</p>
<hr>
<table class="table">
    <tr>
        <th>名称</th>
        <th>单价</th>
        <th>数量</th>
        <th>金额</th>
        <th>操作</th>
    </tr>
    <%--遍历获取Session中存储的购物车商品集合并展示在页面上--%>
    <c:forEach items="${cartListSession}" var="commodity">
        <%--累加获取总金额--%>
        <c:set var="money" scope="page" value="${money+(commodity.price*commodity.amount)}"></c:set>
        <tr>
            <td>
                    ${commodity.name}
            </td>
            <td>
                    ${commodity.price}
            </td>
            <td>
                    ${commodity.amount}
            </td>
            <td>
                    <%--单个商品金额--%>
                    ${commodity.price*commodity.amount}
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/RemoveServlet?number=${commodity.number}"
                      method="post">
                    <input type="number" name="removeNumber" placeholder="请输入移除的数量">
                    <input type="submit" value="移除商品">
                </form>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="5">
            <hr>
        </td>
    </tr>
    <tr>
        <td class>
            <a href="commodity_view.jsp"><span>继续购物</span></a>
        </td>
        <td>
            <a href="${pageContext.request.contextPath}/EmptyServlet"><span>清空购物车</span></a>
        </td>
        <td>应付金额：</td>
        <td>
            ${money}
        </td>
        <td>
            <a href="${pageContext.request.contextPath}/SettlementServlet"><span>结算</span></a>
        </td>
    </tr>
</table>
</body>
</html>
