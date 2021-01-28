package com.commerce.servlet;

import com.commerce.domain.User;
import com.commerce.service.UserService;
import com.commerce.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/SignServlet")
public class SignServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String username = request.getParameter("setUsername");
        String password = request.getParameter("confirmPassword");
        User signUser = new User();
        signUser.setUsername(username);
        signUser.setPassword(password);
        // 调用方法返回注册信息并存入Session供页面调用回显信息
        UserService userService = new UserServiceImpl();
        User user = userService.sign(signUser);
        if (user != null) {
            session.setAttribute("userSession", user);
            response.sendRedirect(request.getContextPath() + "/commerce/sign_pass.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/commerce/sign_failure.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
