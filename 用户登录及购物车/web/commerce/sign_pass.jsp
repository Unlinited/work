<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册成功</title>
</head>
<body>
<p>
    <%--回显用户注册信息--%>
    &nbsp;&nbsp;${userSession.username}，你的密码为${userSession.password}，3秒后将为你跳转至登录页面...
</p>
<meta http-equiv="refresh" content="3;url=${pageContext.request.contextPath}/ForgetPasswordServlet">
</body>
</html>
