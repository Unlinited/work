<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆成功</title>
</head>
<body>
<p>
    <%--从Session中获取用户信息--%>
    &nbsp;&nbsp;${userSession.username}，
    <c:if test="${cookie.lastTime.value==null}">
        这是你的首次访问，
    </c:if>
    <c:if test="${cookie.lastTime.value!=null}">
        你的上次登录时间是${cookie.lastTime.value}，
    </c:if>
    3秒后将为你跳转至商品信息页面，
    <a href="${pageContext.request.contextPath}/ForgetPasswordServlet"><span>如需返回重新登录请点击这里...</span></a>
</p>
<%--3秒后跳转至ListCommodityFromDatabaseServlet获取商品信息--%>
<meta http-equiv="refresh" content="3;url=${pageContext.request.contextPath}/ListCommodityFromDatabaseServlet">
</body>
</html>
