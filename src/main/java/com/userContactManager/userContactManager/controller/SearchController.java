package com.userContactManager.userContactManager.controller;

import com.userContactManager.userContactManager.entity.Contact;
import com.userContactManager.userContactManager.entity.User;
import com.userContactManager.userContactManager.repository.ContactRepository;
import com.userContactManager.userContactManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController

public class SearchController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ContactRepository contactRepository;

    @GetMapping("/search/{query}")
    public ResponseEntity<?> search(@PathVariable("query") String query, Principal principal)
    {
     User user =  userRepository.getUserByUserName( principal.getName()) ;
     List<Contact> contacts = contactRepository.findByNameContainingAndUser(query, user);
     return ResponseEntity.ok(contacts);

    }

}

