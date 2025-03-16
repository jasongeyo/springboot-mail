package com.jasonko.springbootmail.dao;

import com.jasonko.springbootmail.dto.UserRegisterRequest;
import com.jasonko.springbootmail.model.User;

public interface UserDao {

    User getUserById(Integer userId);

    User getUserByEmail(String email);

    Integer createUser(UserRegisterRequest userRegisterRequest);
}
