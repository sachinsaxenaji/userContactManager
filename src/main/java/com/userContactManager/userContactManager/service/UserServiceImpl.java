package com.userContactManager.userContactManager.service;

import com.userContactManager.userContactManager.entity.Contact;
import com.userContactManager.userContactManager.entity.User;
import com.userContactManager.userContactManager.helper.Messeges;
import com.userContactManager.userContactManager.repository.ContactRepository;
import com.userContactManager.userContactManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    ContactRepository contactRepository;



    @Override
    public void passwordReset(String oldPassword, String newPassword, Principal principal, HttpSession session) {

        User user = this.userRepository.getUserByUserName(principal.getName());
        if(this. passwordEncoder.matches(oldPassword, user.getPassword()))
        {
            user.setPassword(this.passwordEncoder.encode(newPassword));
            this.userRepository.save(user);
            session.setAttribute("message",new Messeges("update successful !", "alert-success"));
        }

        else {
            session.setAttribute("message",new Messeges("update fail !", "alert-danger"));
        }



    }

    @Override
    public void processUpdate(Contact contact, MultipartFile file, Model m, HttpSession session, Principal principal) {


        Contact c = this.contactRepository.findById(contact.getcId()).get();

        try {
            if (!file.isEmpty())

            {

                //delete old file
                File deleteFile = new ClassPathResource("static/img").getFile();
                File f =  new File(deleteFile, c.getImage());
                f.delete();


                File saveFile = new ClassPathResource("static/img").getFile();
                Path path  = Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                contact.setImage(file.getOriginalFilename());
            }

            else
            {
                contact.setImage(c.getImage());
            }

            User u =  this.userRepository.getUserByUserName(principal.getName());
            contact.setUser(u);
            this.contactRepository.save(contact);
            session.setAttribute("message",new Messeges("updation successful !", "alert-success"));


        }
        catch (Exception e)

        {

            e.printStackTrace();
        }

    }

    @Override
    public void updateContact(int id, HttpSession session, Model model) {
        Optional<Contact> contactOptional = contactRepository.findById(id);
        Contact contact = contactOptional.get();


        model.addAttribute("contact", contact);
    }

    @Override
    public void deleteContact(int id, HttpSession session, Principal principal) {
        Optional<Contact> contactOptional = contactRepository.findById(id);
        Contact contact = contactOptional.get();


        String u = principal.getName();
        User user = this.userRepository.getUserByUserName(u);
        user.getList().remove(contact);
        this.userRepository.save(user);


        session.setAttribute("message", new Messeges("success", "alert-success"));

    }

    @Override
    public void viewParticulerContact(int id, Model model, Principal principal) {
        String u = principal.getName();
        User user = this.userRepository.getUserByUserName(u);


        Optional<Contact> contactOptional  = contactRepository.findById(id);
        Contact contact = contactOptional.get();


        if(user.getId() == contact.getUser().getId())
        {
            model.addAttribute("contact", contact);
        }
        System.out.println(user.getId()+"This is user id");
        System.out.println( contact.getUser().getId()+"This is contact id");
    }

    @Override
    public void getUserDetails(int page, Model model, Principal principal) {
        String u = principal.getName();
        User user = this.userRepository.getUserByUserName(u);
        System.out.println("User Details"+user);

        Pageable pageable = PageRequest.of(page, 7);


        Page<Contact> con = this.contactRepository.getContactByUserId(user.getId(), pageable);

        model.addAttribute("contact", con);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", con.getTotalPages());

        model.addAttribute("title", "View Contacts Details");
    }

    @Override
    public void processContact(Contact contact, MultipartFile file, Principal principal, HttpSession session) {
        try{
            String name = principal.getName();
            User user = this.userRepository.getUserByUserName(name);
            if (file.isEmpty())
            {
                System.out.println("No file found");
                contact.setImage("contact.png");
            }
            else{
                contact.setImage(file.getOriginalFilename());

                File saveFile = new ClassPathResource("static/img").getFile();

                Path path  = Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Image is uploaded");

            }


            user.getList().add(contact);
            contact.setUser(user);
            this.userRepository.save(user);

            System.out.println("DATA" + contact);

            System.out.println("Data added to Database");
            session.setAttribute("message", new Messeges("Your contact is added", "success"));
        }
        catch (Exception e)
        {

            System.out.println("Error"+ e.getMessage());
            e.printStackTrace();
            session.setAttribute("message", new Messeges("Something went wrong", "danger"));
        }

    }

    public User testMethod()
    {
        return this.userRepository.getUserByUserName("sachin");
    }
}
