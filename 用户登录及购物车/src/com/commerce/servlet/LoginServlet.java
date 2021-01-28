package com.commerce.servlet;

import com.commerce.domain.User;
import com.commerce.service.UserService;
import com.commerce.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置请求参数编码为UTF-8，避免中文乱码
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        // 此处提前为username和password设置初值，一来避免空指针异常，二来提升其作用域，使其既能从请求参数中获取值又能从Cookie中获取值
        String username = "";
        String password = "";
        // 遍历Cookie查找usernameCookie和passwordCookie，若找到数据则将flag设置为true，方便后续进行操作
        Cookie[] cookies = request.getCookies();
        boolean flag = false;
        if (cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if ("usernameCookie".equals(cookie.getName())) {
                    flag = true;
                    username = cookie.getValue();
                }
                if ("passwordCookie".equals(cookie.getName())) {
                    flag = true;
                    password = cookie.getValue();
                }
            }
        }
        // Cookie中未找到数据则使用请求参数进行赋值
        if (!flag) {
            username = request.getParameter("username");
            password = request.getParameter("password");
        }
        // 获取复选框（记住密码）的状态
        String rememberPassword = request.getParameter("rememberPassword");
        // 封装User对象
        User loginUser = new User();
        loginUser.setUsername(username);
        loginUser.setPassword(password);
        // 调用方法返回数据库比对结果
        UserService userService = new UserServiceImpl();
        User user = userService.login(loginUser);
        // 数据库中有此用户信息
        if (user != null) {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
            // 注意Cook中写入的数据格式，必要时需进行URL转码
            Cookie lastTime = new Cookie("lastTime", simpleDateFormat.format(date));
            lastTime.setMaxAge(60 * 60 * 24 * 7 * 2);
            response.addCookie(lastTime);
            // 若勾选复选框（记住密码）则将username和password存入Cookie，生命周期为两周
            if ("on".equals(rememberPassword)) {
                Cookie usernameCookie = new Cookie("usernameCookie", user.getUsername());
                Cookie passwordCookie = new Cookie("passwordCookie", user.getPassword());
                usernameCookie.setMaxAge(60 * 60 * 24 * 7 * 2);
                passwordCookie.setMaxAge(60 * 60 * 24 * 7 * 2);
                response.addCookie(usernameCookie);
                response.addCookie(passwordCookie);
            }
            // 将用户信息存入Session方便login_pass.jsp获取用户信息
            session.setAttribute("userSession", user);
            response.sendRedirect(request.getContextPath() + "/commerce/login_pass.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/commerce/login_failure.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
