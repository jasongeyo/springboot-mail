package com.jasonko.springbootmail.service;

import com.jasonko.springbootmail.dto.UserRegisterRequest;
import com.jasonko.springbootmail.model.User;

public interface UserService {

    User getUserById(Integer userId);

    Integer register(UserRegisterRequest userRegisterRequest);

}
