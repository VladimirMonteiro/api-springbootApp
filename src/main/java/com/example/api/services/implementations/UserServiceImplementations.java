package com.example.api.services.implementations;

import com.example.api.domain.User;
import com.example.api.domain.dto.UserDTO;
import com.example.api.repositories.UserRepository;
import com.example.api.services.UserService;
import com.example.api.services.exceptions.DataIntragratyViolationException;
import com.example.api.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImplementations implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public User findById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(()-> new ObjectNotFoundException("Object not found."));

    }

    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Override
    public User create(UserDTO obj){
        findByEmail(obj);
        return userRepository.save(mapper.map(obj, User.class));

    }

    @Override
    public User update(UserDTO obj){
        findByEmail(obj);
        return userRepository.save(mapper.map(obj, User.class));

    }

    private void findByEmail(UserDTO obj){
        Optional<User> user = userRepository.findByEmail(obj.getEmail());

        if(user.isPresent() && !user.get().getId().equals(obj.getId())){
            throw new DataIntragratyViolationException("User already exists.");
        }
    }


}
