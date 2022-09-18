package com.userContactManager.userContactManager.controller;


import com.userContactManager.userContactManager.entity.User;
import com.userContactManager.userContactManager.repository.UserRepository;
import com.userContactManager.userContactManager.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Random;


@Controller
public class ForgotPassword {


    @Autowired
    EmailService emailService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    Random random = new Random(1000);

    boolean bo;

    @RequestMapping("/forgot_password")
    public String forgotPassword()
    {

        return "forgot_password-form";
    }

    @PostMapping("/send-otp")
    public String sendOTP(@RequestParam("email") String email, HttpSession session)


    {

        int otp = random.nextInt(99999);
        String myotp = Integer.toString(otp);

        String subject = "OTP to verify SCM";

        bo = this.emailService.sendAttach(myotp, subject, email);



        if (bo)
        {

            session.setAttribute("email", email);
            session.setAttribute("otp", otp);
            session.setAttribute("success", "otp sent success");


            return "otp-verify-form";
        }
        else
        {
            session.setAttribute("danger", "something went wrong");
            return "forgot_password-form";

        }

    }
    @PostMapping("/verify-otp")
    public String verifyOTP(@RequestParam("otp") int myOtp, HttpSession session)


    {

        int otp = (int)session.getAttribute("otp");

        System.out.println(otp);



        if (otp == myOtp)
        {


            return "reset-password-form";
        }
        else
        {
            session.setAttribute("danger", "something went wrong");
            return "otp-verify-form";
        }

    }

    @PostMapping("/reset-pwd")
    public String resetPassword(@RequestParam("password") String pwd, HttpSession session)


    {


        String email = (String) session.getAttribute("email");
        System.out.println(email);




        User username = this.userRepository.getUserByUserName(email);
        username.setPassword(this.passwordEncoder.encode(pwd));

            userRepository.save(username);

            return "redirect:/signin";



    }
}
