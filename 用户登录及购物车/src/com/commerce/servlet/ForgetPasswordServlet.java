package com.commerce.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ForgetPasswordServlet")
public class ForgetPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置同名Cookie并将其生命周期设置为0以清除Cookie
        Cookie usernameCookie = new Cookie("usernameCookie", null);
        Cookie passwordCookie = new Cookie("passwordCookie", null);
        usernameCookie.setMaxAge(0);
        passwordCookie.setMaxAge(0);
        response.addCookie(usernameCookie);
        response.addCookie(passwordCookie);
        response.sendRedirect(request.getContextPath() + "/commerce/login.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
