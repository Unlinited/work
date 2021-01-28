package com.commerce.service.impl;

import com.commerce.dao.UserDao;
import com.commerce.dao.impl.UserDaoImpl;
import com.commerce.domain.User;
import com.commerce.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();

    @Override
    // 直接返回数据库中查询的结果
    public User login(User loginUser) {
        return dao.findUserByUsernameAndPassword(loginUser.getUsername(), loginUser.getPassword());
    }

    @Override
    // 经查数据库中无同名用户后再添加用户信息至数据库中
    public User sign(User signUser) {
        if (dao.findUserByUsernameAndPassword(signUser.getUsername(), signUser.getPassword()) == null) {
            return dao.addUser(signUser);
        } else {
            return null;
        }
    }
}
