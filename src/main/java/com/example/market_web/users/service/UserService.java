package com.example.market_web.users.service;

import com.example.market_web.users.entity.UserEntity;

public interface UserService {

    UserEntity findUserById(Integer id);
}
