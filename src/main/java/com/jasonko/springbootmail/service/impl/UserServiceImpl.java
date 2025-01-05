package com.jasonko.springbootmail.service.impl;

import com.jasonko.springbootmail.dao.UserDao;
import com.jasonko.springbootmail.dto.UserRegisterRequest;
import com.jasonko.springbootmail.model.User;
import com.jasonko.springbootmail.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }
}
