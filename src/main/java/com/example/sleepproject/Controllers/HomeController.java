package com.example.sleepproject.Controllers;

import com.example.sleepproject.Services.SleepService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final SleepService sleepService;

    public HomeController(SleepService sleepService) {
        this.sleepService = sleepService;
    }

    @GetMapping("/home")
    public String showHomePage(Authentication authentication, Model model) {
        model.addAttribute("username", authentication.getName());
        model.addAttribute("sleeps", sleepService.getLast5SleepsForCurrentUser());
        return "homepage";
    }
}
