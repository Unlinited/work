package com.commerce.servlet;

import com.commerce.domain.Commodity;
import com.commerce.service.CommodityService;
import com.commerce.service.impl.CommodityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ListCommodityFromDatabaseServlet")
public class ListCommodityFromDatabaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommodityService commodityService = new CommodityServiceImpl();
        HttpSession session = request.getSession();
        // 调用方法从数据库中获取信息返回一个商品类的集合
        List<Commodity> commodityList = commodityService.listCommodity();
        // 将获取到的商品信息存入Session中
        session.setAttribute("commodityListSession", commodityList);
        // 初始化一个为空的购物车商品信息集合存入Session中
        List<Commodity> cartList = new ArrayList<Commodity>();
        session.setAttribute("cartListSession", cartList);
        response.sendRedirect(request.getContextPath() + "/commerce/commodity_view.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
