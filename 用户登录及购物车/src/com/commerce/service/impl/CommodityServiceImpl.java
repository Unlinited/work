package com.commerce.service.impl;

import com.commerce.dao.CommodityDao;
import com.commerce.dao.impl.CommodityDaoImpl;
import com.commerce.domain.Commodity;
import com.commerce.service.CommodityService;

import java.util.List;

public class CommodityServiceImpl implements CommodityService {
    private CommodityDao dao = new CommodityDaoImpl();

    @Override
    // 获取数据库中的商品信息
    public List<Commodity> listCommodity() {
        return dao.findAll();
    }

    @Override
    // 若购物车中无商品则直接加入商品信息，有商品则遍历集合查找同编号商品，若查找到则修改商品数量，未查找到则直接加入商品信息
    public List<Commodity> addToCart(List<Commodity> cartList, Commodity commodityCart) {
        if (cartList != null) {
            boolean flag = false;
            if (cartList.size() > 0) {
                for (Commodity commodity : cartList) {
                    if (commodityCart.getNumber() == commodity.getNumber()) {
                        flag = true;
                        commodity.setAmount(commodity.getAmount() + commodityCart.getAmount());
                        break;
                    }
                }
                if (!flag) {
                    cartList.add(commodityCart);
                }
            } else {
                cartList.add(commodityCart);
            }
        }
        return cartList;
    }

    @Override
    // 通过提交的编号找到购物车中对应的商品，若经计算后的商品数量大于0则修改商品信息，反之直接移除商品信息
    public List<Commodity> removeTOCart(List<Commodity> cartList, int number, int removeNumber) {
        if (cartList != null) {
            for (Commodity commodity : cartList) {
                if (number == commodity.getNumber()) {
                    if (removeNumber > commodity.getAmount()) {
                        removeNumber = commodity.getAmount();
                    }
                    if (commodity.getAmount() - removeNumber > 0) {
                        commodity.setAmount(commodity.getAmount() - removeNumber);
                        break;
                    } else {
                        cartList.remove(commodity);
                        break;
                    }
                }
            }
        }
        return cartList;
    }

    @Override
    // 将购物车中商品实际移除数量恢复到首页商品库存中
    public List<Commodity> recoverAmount(List<Commodity> commodityList, List<Commodity> cartList, int number, int removeNumber) {
        if (cartList != null && commodityList != null) {
            boolean flag = false;
            // 确定实际移除数量
            for (Commodity commodity : cartList) {
                if (number == commodity.getNumber()) {
                    flag = true;
                    if (removeNumber > commodity.getAmount()) {
                        removeNumber = commodity.getAmount();
                    }
                    break;
                }
            }
            if (flag) {
                for (Commodity commodity : commodityList) {
                    if (number == commodity.getNumber()) {
                        commodity.setAmount(commodity.getAmount() + removeNumber);
                        break;
                    }
                }
            }
        }
        return commodityList;
    }

    @Override
    // 直接清空集合
    public List<Commodity> cleanTheCart(List<Commodity> cartList) {
        if (cartList != null) {
            cartList.clear();
        }
        return cartList;
    }

    @Override
    // 嵌套遍历比对恢复商品库存
    public List<Commodity> recoverAmountByCleanCart(List<Commodity> cartList, List<Commodity> commodityList) {
        if (cartList != null && commodityList != null) {
            for (Commodity commodity : cartList) {
                for (Commodity commodity1 : commodityList) {
                    if (commodity.getNumber() == commodity1.getNumber()) {
                        commodity1.setAmount(commodity1.getAmount() + commodity.getAmount());
                        break;
                    }
                }
            }
        }
        return commodityList;
    }

    @Override
    // 遍历集合逐条修改数据库中与之匹配的商品库存并清空购物车
    public List<Commodity> settle(List<Commodity> cartList) {
        if (cartList != null) {
            for (Commodity commodity : cartList) {
                dao.update(commodity.getNumber(), commodity.getAmount());
            }
            cartList.clear();
        }
        return cartList;
    }
}