package com.userContactManager.userContactManager.service;

import com.userContactManager.userContactManager.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest

class UserServiceImplTest {


    private UserRepository userRepository;

    @Autowired
    private UserService userService;






}