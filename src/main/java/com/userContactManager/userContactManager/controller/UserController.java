package com.userContactManager.userContactManager.controller;
import com.userContactManager.userContactManager.entity.Contact;
import com.userContactManager.userContactManager.entity.User;
import com.userContactManager.userContactManager.repository.UserRepository;
import com.userContactManager.userContactManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import java.security.Principal;


@Controller
@RequestMapping("/user")


public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @ModelAttribute
    public void addCommonData(Model model, Principal principal)
    {

        String username = principal.getName();
        System.out.println("USERNAME"+ username);

        User user = userRepository.getUserByUserName(username);
        System.out.println("USER"+user);

        model.addAttribute("user", user);

    }

    @RequestMapping("/index")
    public String user_dashboard(Model model)
    {
    	model.addAttribute("title", "This is user dashboard");
    	return "normal/user_dashboard";
    }

    @RequestMapping("/add_contact")
    public String openAddContactForm(Model model)
    {
        model.addAttribute("title", "Add Contact Form");
        model.addAttribute("contact", new Contact());
        return "normal/add_contact_form";
    }
        //storing new contact information
    @PostMapping("/process-contact")
    public String processContact(@ModelAttribute Contact contact, @RequestParam("imageUrl") MultipartFile file, Principal principal, HttpSession session)


    {

       this.userService.processContact(contact, file, principal, session);


        return "redirect:/user/add_contact";
    }

    @GetMapping("/view-contacts/{page}")
    public String getUserDetails(@PathVariable("page") int page, Model model, Principal principal)
    {
       this.userService.getUserDetails(page, model, principal);
        return "normal/view_contacts";
    }

//    view a particular contact

    @GetMapping("/{id}/contact")
    public String viewParticulerContact(@PathVariable("id") int id, Model model, Principal principal)
    {
    	
    	this.userService.viewParticulerContact(id, model, principal);
        return "normal/view_p_contact";
    }


    @RequestMapping("/delete-contact/{id}")
    public String deleteContact(@PathVariable("id") int id, HttpSession session, Principal principal)
    {
      this.userService.deleteContact(id, session, principal);
        return "redirect:/user/view-contacts/0";
    }


    @RequestMapping("/update-contact/{id}")
    public String updateContact(@PathVariable("id") int id, HttpSession session, Model model)
    {
        this.userService.updateContact(id, session, model);

        return "normal/update_contact";
    }

    @PostMapping("/process-update")
    public String processUpdate(@ModelAttribute Contact contact, @RequestParam("imageUrl") MultipartFile file, Model m, HttpSession session, Principal principal)


    {
        this.userService.processUpdate(contact, file,m,session,principal);

        return "redirect:/user/"+contact.getcId()+"/contact";


    }

    @GetMapping("/settings")
    public String userSettings()
    {
        return "normal/user-settings";
    }
    @PostMapping("/password-reset")
    public String passwordReset(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, Principal principal, HttpSession session)
    {

        this.userService.passwordReset(oldPassword,newPassword,principal,session );


        return "redirect:/user/index";
    }
}


