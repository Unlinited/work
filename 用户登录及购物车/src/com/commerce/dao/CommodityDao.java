package com.commerce.dao;

import com.commerce.domain.Commodity;

import java.util.List;

public interface CommodityDao {
    /**
     * 获取表中所有商品信息并返回
     *
     * @return 对象集合
     */
    List<Commodity> findAll();

    /**
     * 通过商品编号更新商品库存信息
     *
     * @param number
     * @param amount
     */
    void update(int number, int amount);
}
