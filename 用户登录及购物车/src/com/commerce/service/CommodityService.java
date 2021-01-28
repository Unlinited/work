package com.commerce.service;

import com.commerce.domain.Commodity;

import java.util.List;

public interface CommodityService {
    /**
     * 获取数据库中所有商品
     *
     * @return 商品集合
     */
    List<Commodity> listCommodity();

    /**
     * 加入购物车
     *
     * @param cartList
     * @param commodityCart
     * @return
     */
    List<Commodity> addToCart(List<Commodity> cartList, Commodity commodityCart);

    /**
     * 移除商品
     *
     * @param cartList
     * @param number
     * @param removeNumber
     * @return
     */
    List<Commodity> removeTOCart(List<Commodity> cartList, int number, int removeNumber);

    /**
     * 移除商品后恢复库存
     *
     * @param commodityList
     * @param cartList
     * @param number
     * @param removeNumber
     * @return
     */
    List<Commodity> recoverAmount(List<Commodity> commodityList, List<Commodity> cartList, int number, int removeNumber);

    /**
     * 清空购物车
     *
     * @param cartList
     * @return
     */
    List<Commodity> cleanTheCart(List<Commodity> cartList);

    /**
     * 清空购物车后恢复库存
     *
     * @param cartList
     * @param commodityList
     * @return
     */
    List<Commodity> recoverAmountByCleanCart(List<Commodity> cartList, List<Commodity> commodityList);

    /**
     * 结算
     *
     * @param cartList
     * @return
     */
    List<Commodity> settle(List<Commodity> cartList);
}
