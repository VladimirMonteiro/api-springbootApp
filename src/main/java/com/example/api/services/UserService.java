package com.example.api.services;

import com.example.api.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    public User findById(Integer id);
    public List<User> findAll();
}
