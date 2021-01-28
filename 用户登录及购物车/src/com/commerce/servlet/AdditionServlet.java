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

@WebServlet("/AdditionServlet")
public class AdditionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int number = Integer.parseInt(request.getParameter("number"));
        int addNumber = 0;
        if (request.getParameter("addNumber").length() > 0) {
            addNumber = Integer.parseInt(request.getParameter("addNumber"));
        }
        // 校验其为有效数据后再进行操作
        if (addNumber > 0) {
            HttpSession session = request.getSession();
            List<Commodity> commodityList = (List<Commodity>) session.getAttribute("commodityListSession");
            List<Commodity> cartList = (List<Commodity>) session.getAttribute("cartListSession");
            Commodity commodityCart = new Commodity();
            int amount = 0;
            // 获取需要添加的商品对象
            for (Commodity commodity : commodityList) {
                if (number == commodity.getNumber()) {
                    // 获取商品库存信息
                    amount = commodity.getAmount();
                    // 添加的商品数量不能超过商品库存
                    if (addNumber > commodity.getAmount()) {
                        addNumber = commodity.getAmount();
                    }
                    // 修改商品库存
                    commodity.setAmount(commodity.getAmount() - addNumber);
                    // 封装Commodity对象
                    commodityCart.setNumber(commodity.getNumber());
                    commodityCart.setName(commodity.getName());
                    commodityCart.setAmount(addNumber);
                    commodityCart.setPrice(commodity.getPrice());
                }
            }
            // 商品库存大于0才能进行操作
            if (amount > 0) {
                CommodityService commodityService = new CommodityServiceImpl();
                List<Commodity> newCartList = commodityService.addToCart(cartList, commodityCart);
                session.setAttribute("carListSession", newCartList);
                response.sendRedirect(request.getContextPath() + "/commerce/shopping_cart.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/commerce/commodity_view.jsp");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/commerce/commodity_view.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
