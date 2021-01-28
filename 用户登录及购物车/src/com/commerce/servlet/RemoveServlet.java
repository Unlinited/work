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

@WebServlet("/RemoveServlet")
public class RemoveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int number = Integer.parseInt(request.getParameter("number"));
        int removeNumber = 0;
        if (request.getParameter("removeNumber").length() > 0) {
            removeNumber = Integer.parseInt(request.getParameter("removeNumber"));
        }
        // 校验数据是否合法
        if (removeNumber > 0) {
            HttpSession session = request.getSession();
            List<Commodity> cartList = (List<Commodity>) session.getAttribute("cartListSession");
            List<Commodity> commodityList = (List<Commodity>) session.getAttribute("commodityListSession");
            CommodityService commodityService = new CommodityServiceImpl();
            // 恢复首页商品库存并修改购物车商品数量
            List<Commodity> newCommodityList = commodityService.recoverAmount(commodityList, cartList, number, removeNumber);
            List<Commodity> newCartList = commodityService.removeTOCart(cartList, number, removeNumber);
            session.setAttribute("commodityListSession", newCommodityList);
            session.setAttribute("cartListSession", newCartList);
            response.sendRedirect(request.getContextPath() + "/commerce/shopping_cart.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/commerce/shopping_cart.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
