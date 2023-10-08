package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.dto.UserDto;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.model.Mail;
import com.example.registrationlogindemo.service.EmailService;
import com.example.registrationlogindemo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AuthController {

    private UserService userService;
    private EmailService emailService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(){
        return "index.html";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login.html";
    }

    @GetMapping("/landing")
    public String landing(){
        return "landing.html";
    }

    // handler method to handle user registration request
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register.html";
    }

    // handler method to handle register user form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model){
        User existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register.html";
        }
        userService.saveUser(user);

        Mail registerMail = new Mail();
        registerMail.setMailTo("mike@hotstreakllc.com");
        registerMail.setMailCc("jason@comegaidea.com");
        registerMail.setMailSubject("New user just registered: " + user.getFirstName() + " " + user.getLastName());
        registerMail.setMailContent("Mike, \n\n\n A new user just registered: \n\n Name: " 
                + user.getFirstName() + " " + user.getLastName() + "\n Company: " + 
                user.getCompany() + "\n E-mail: " + user.getEmail());
        
        emailService.sendEmail(registerMail);
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String listRegisteredUsers(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users.html";
    }
}
