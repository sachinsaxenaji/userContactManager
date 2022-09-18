package com.userContactManager.userContactManager.service;

import com.userContactManager.userContactManager.entity.Contact;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.security.Principal;

public interface UserService {

    public void passwordReset(String oldPassword, String newPassword, Principal principal, HttpSession session);

    public void processUpdate(Contact contact, MultipartFile file, Model m, HttpSession session, Principal principal);

    public void updateContact(int id, HttpSession session, Model model);

    public void deleteContact( int id, HttpSession session, Principal principal);

    public void viewParticulerContact( int id, Model model, Principal principal);

    public void getUserDetails(int page, Model model, Principal principal);

    public void processContact(Contact contact,MultipartFile file, Principal principal, HttpSession session);






}
