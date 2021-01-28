package com.commerce.service;

import com.commerce.domain.User;

public interface UserService {
    /**
     * 登录
     *
     * @param loginUser
     * @return
     */
    User login(User loginUser);

    /**
     * 注册
     *
     * @param signUser
     * @return
     */
    User sign(User signUser);
}
