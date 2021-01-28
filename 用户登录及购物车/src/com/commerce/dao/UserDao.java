package com.commerce.dao;

import com.commerce.domain.User;

public interface UserDao {
    /**
     * 通过username和password查找用户信息并返回
     *
     * @param username
     * @param password
     * @return
     */
    User findUserByUsernameAndPassword(String username, String password);

    /**
     * 添加新用户
     *
     * @param user
     * @return
     */
    User addUser(User user);
}
