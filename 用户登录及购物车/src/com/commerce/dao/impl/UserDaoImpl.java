package com.commerce.dao.impl;

import com.commerce.dao.UserDao;
import com.commerce.domain.User;
import com.commerce.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    // 通过username和password查找用户信息并返回
    public User findUserByUsernameAndPassword(String username, String password) {
        try {
            String sql = "SELECT username, password FROM user WHERE username = ? AND password = ?";
            return template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    // 添加新用户
    public User addUser(User user) {
        try {
            String sql1 = "INSERT INTO user VALUES(?,?)";
            int i = template.update(sql1, user.getUsername(), user.getPassword());
            if (i > 0) {
                return this.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
            } else {
                return null;
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
