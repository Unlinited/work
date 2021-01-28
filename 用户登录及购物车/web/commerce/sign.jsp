<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新用户注册</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/SignServlet" method="post">
    <table>
        <tr>
            <td>
                <label for="setUsername">用户名</label>
            </td>
            <td>
                <input type="text" placeholder="请输入用户名" name="setUsername" id="setUsername">
            </td>
        </tr>
        <tr>
            <td>
                <label for="setPassword">密码</label>
            </td>
            <td>
                <input type="password" placeholder="请输入密码" name="setPassword" id="setPassword">
            </td>
        </tr>
        <tr>
            <td>
                <label for="confirmPassword">确认密码</label>
            </td>
            <td>
                <input type="password" placeholder="请再次输入密码" name="confirmPassword" id="confirmPassword">
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="注册" class="input_submit">
            </td>
        </tr>
    </table>
    <a href="login.jsp">已有账号？立即登录</a>
</form>
</body>
</html>
