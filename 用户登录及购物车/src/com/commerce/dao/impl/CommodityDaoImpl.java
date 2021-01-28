package com.commerce.dao.impl;

import com.commerce.dao.CommodityDao;
import com.commerce.domain.Commodity;
import com.commerce.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CommodityDaoImpl implements CommodityDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    // 获取表中所有商品信息并返回
    public List<Commodity> findAll() {
        try {
            String sql = "SELECT * FROM commodity";
            return template.query(sql, new BeanPropertyRowMapper<Commodity>(Commodity.class));
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    // 通过商品编号更新商品库存信息
    public void update(int number, int amount) {
        try {
            String sql = "UPDATE commodity SET amount = amount - ? WHERE number = ?";
            int update = template.update(sql, amount, number);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
}
