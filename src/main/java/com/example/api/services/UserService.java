package com.example.api.services;

import com.example.api.domain.User;
import org.springframework.stereotype.Service;


public interface UserService {

    public User findById(Integer id);
}
