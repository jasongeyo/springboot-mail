package com.jasonko.springbootmail.dao.impl;

import com.jasonko.springbootmail.dao.UserDao;
import com.jasonko.springbootmail.dto.UserRegisterRequest;
import com.jasonko.springbootmail.model.User;
import com.jasonko.springbootmail.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public User getUserById(Integer userId) {
        String sql = " select user_id , email , password , created_date , last_modified_date "
                   + " from User where user_id = :userId "
                   ;

        Map<String , Object> map = new HashMap<>();
        map.put("userId", userId);

        List<User> userList = namedParameterJdbcTemplate.query(sql , map , new UserRowMapper());

        if (userList.size() > 0) {
            return userList.get(0);
        }
        else {
            return null;
        }
    }

    @Override
    public User getUserByEmail(String email) {
        String sql = " select user_id , email , password , created_date , last_modified_date "
                   + " from User where email = :email "
                   ;
        Map<String , Object>map = new HashMap<>();
        map.put("email", email);

        List<User> userList = namedParameterJdbcTemplate.query(sql , map , new UserRowMapper());
        if (userList.size() > 0) {
            return userList.get(0);
        }
        else {
            return null;
        }
    }

    @Override
    public Integer createUser(UserRegisterRequest userRegisterRequest) {

        String sql = " insert into user ("
                   + " email , password , created_date , last_modified_date "
                   + " ) values ( "
                   + " :email , :password , :createdDate , :lastModifiedDate "
                   + " ) "
                   ;

        Map<String , Object> map = new HashMap<>();
        map.put("email", userRegisterRequest.getEmail());
        map.put("password", userRegisterRequest.getPassword());

        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql , new MapSqlParameterSource(map) , keyHolder);

        int userId = keyHolder.getKey().intValue();

        return userId;
    }
}
