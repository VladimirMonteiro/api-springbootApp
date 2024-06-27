package com.example.api.config;

import com.example.api.domain.User;
import com.example.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig implements CommandLineRunner {

    @Autowired
    private UserRepository repository;


    @Override
    public void run(String... args) throws Exception {
        User u1 = new User(null, "Valdir", "valdir@mail.com", "123");
        User u2 = new User(null, "Luiz", "luiz@mail.com", "123");

        repository.saveAll(List.of(u1, u2));

    }
}
