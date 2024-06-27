package com.example.api.services.implementations;

import com.example.api.domain.User;
import com.example.api.repositories.UserRepository;
import com.example.api.services.UserService;
import com.example.api.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImplementations implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(()-> new ObjectNotFoundException("Object not found."));

    }

    public List<User> findAll(){
        return userRepository.findAll();
    }
}
