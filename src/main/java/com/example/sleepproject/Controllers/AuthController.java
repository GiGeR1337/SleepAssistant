package com.example.sleepproject.Controllers;

import com.example.sleepproject.DTOs.UserDto;
import com.example.sleepproject.Services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegisterPage(){
        return "register";
    }

    @GetMapping("/login")
    public String showLoginPage(){
        return "login-page";
    }

    @GetMapping("/home")
    public String showHomePage(Authentication authentication, Model model){
        model.addAttribute("username", authentication.getName());
        return "index";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserDto userDto, Model model){
        model.addAttribute("userDto", userDto);
        userService.registerUser(userDto);
        return "redirect::/login";
    }
}
