package com.userContactManager.userContactManager.controller;
import com.userContactManager.userContactManager.entity.User;
import com.userContactManager.userContactManager.helper.Messeges;
import com.userContactManager.userContactManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller

public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @RequestMapping("/")
    public String show()
    {
        return "home";
    }

    @RequestMapping("/signin")
    public String login(Model model)
    {

        model.addAttribute("title","Register- Contact Manager");
        model.addAttribute("user", new User());
        return "login";
    }


    @RequestMapping("/signup")
    public String signup(Model model)
    {

        model.addAttribute("title","Register- Contact Manager");
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/do_register")
    public String do_register(@Valid @ModelAttribute("user") User user, BindingResult bresult, @RequestParam (value = "agreement", defaultValue = "false") boolean agreement, Model model, HttpSession session)
    {

        try
        {


            if (bresult.hasErrors())
            {
                System.out.println("ERROR"+bresult.toString());
                model.addAttribute("User", user);

            }


            user.setRole("ROLE_USER");
            user.setEnabled(true);
            user.setimageUrl("default.png");
            user.setPassword(passwordEncoder.encode(user.getPassword()));


            System.out.println("Agreement"+agreement);


            User result = this.userRepository.save(user);
            System.out.println("user"+result);


            model.addAttribute("user", new User());
            session.setAttribute("message", new Messeges("success", "alert-success"));

            }

        catch (Exception e)
        {
            e.printStackTrace();
            model.addAttribute("user",user);
            session.setAttribute("message", new Messeges("something went wrong!", "alert-error"));

        }
        return "redirect:/signup";

       }

}
