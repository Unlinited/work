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
import java.util.List;

@WebServlet("/EmptyServlet")
public class EmptyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommodityService commodityService = new CommodityServiceImpl();
        HttpSession session = request.getSession();
        List<Commodity> cartList = (List<Commodity>) session.getAttribute("cartListSession");
        List<Commodity> commodityList = (List<Commodity>) session.getAttribute("commodityListSession");
        // 恢复首页商品库存并清空购物车
        List<Commodity> newCommodityList = commodityService.recoverAmountByCleanCart(cartList, commodityList);
        List<Commodity> newCartList = commodityService.cleanTheCart(cartList);
        session.setAttribute("commodityListSession", newCommodityList);
        session.setAttribute("cartListSession", newCartList);
        response.sendRedirect(request.getContextPath() + "/commerce/shopping_cart.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
