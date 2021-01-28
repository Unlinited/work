<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<%--判断Cookie中是否存在usernameCookie和passwordCookie，若存在则直接跳转至LoginServlet使用Cookie中的数据进行验证，若不存在则正常展示页面--%>
<c:if test="${cookie.usernameCookie.value!=null}">
    <c:if test="${cookie.passwordCookie.value!=null}">
        <c:redirect url="/LoginServlet"></c:redirect>
    </c:if>
</c:if>
<%--使用${pageContext.request.contextPath}动态获取虚拟目录，将用户填写的注册信息通过post请求发送至LoginServlet进行验证--%>
<form action="${pageContext.request.contextPath}/LoginServlet" method="post">
    <table>
        <tr>
            <td>
                <label for="username">用户名</label>
            </td>
            <td>
                <input type="text" placeholder="请输入用户名" name="username" id="username">
            </td>
        </tr>
        <tr>
            <td>
                <label for="password">密码</label></td>
            <td>
                <input type="password" placeholder="请输入密码" name="password" id="password">
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="checkbox" name="rememberPassword" id="rememberPassword">
                <label class="checkbox_new" for="rememberPassword"></label>
                <span>记住密码</span>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="登录">
            </td>
        </tr>
    </table>
    <a href="sign.jsp">没有账号？注册一个</a>
</form>
</body>
</html>
