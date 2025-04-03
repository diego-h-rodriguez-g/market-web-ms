package com.example.market_web.users.service;

import com.example.market_web.users.entity.UserEntity;
import com.example.market_web.users.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity findUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User does not exist"));
    }
}
