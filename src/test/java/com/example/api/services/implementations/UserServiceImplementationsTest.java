package com.example.api.services.implementations;

import com.example.api.domain.User;
import com.example.api.domain.dto.UserDTO;
import com.example.api.repositories.UserRepository;
import com.example.api.services.exceptions.DataIntragratyViolationException;
import com.example.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
class UserServiceImplementationsTest {

    public static final int ID = 1;
    public static final String NAME = "Vladimir";
    public static final String EMAIL = "email@teste.com";
    public static final String PASSWORD = "123";

    @InjectMocks
    private UserServiceImplementations service;

    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdReturnAnUserInstance() {
        Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(optionalUser);

        User response = service.findById(ID);

       assertNotNull(response);
       assertEquals(User.class, response.getClass());
       assertEquals(response.getId(), ID);
       assertEquals(response.getName(), NAME);
       assertEquals(response.getEmail(), EMAIL);

    }

    @Test
    void whenFindByIdReturnAnObjectNotFoundException(){
        Mockito.when(userRepository.findById(Mockito.anyInt())).thenThrow(new ObjectNotFoundException("Object not found."));

        try {

            service.findById(ID);

        }catch (ObjectNotFoundException exception){
            assertEquals(ObjectNotFoundException.class, exception.getClass());
            assertEquals("Object not found.", exception.getMessage());


        }

    }

    @Test
    void whenFindAllAndReturnAnListUsers() {
        Mockito.when(userRepository.findAll()).thenReturn(List.of(user));

        List<User> response = service.findAll();

        assertNotNull(response);
        assertEquals(List.of(user), response);
        assertEquals(User.class, response.get(0).getClass());


    }

    @Test
    void whenCreateAnUserAndReturnUser() {
        Mockito.when(userRepository.save(any())).thenReturn(user);

        User response = service.create(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(user.getName(), response.getName());
    }

    @Test
    void whenFindByEmailAndReturnDataIntragratyViolationException(){
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(optionalUser);
        try{
            optionalUser.get().setId(2);
            service.create(userDTO);
        }catch (Exception exception){
            assertEquals(DataIntragratyViolationException.class, exception.getClass());
            assertEquals("User already exists.", exception.getMessage());
        }


    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser(){
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}