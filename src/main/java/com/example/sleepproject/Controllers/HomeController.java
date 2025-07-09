package com.example.sleepproject.Controllers;

import com.example.sleepproject.Services.AdviceService;
import com.example.sleepproject.Services.SleepService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final SleepService sleepService;
    private final AdviceService adviceService;

    public HomeController(SleepService sleepService, AdviceService adviceService) {
        this.sleepService = sleepService;
        this.adviceService = adviceService;
    }

    @GetMapping("/home")
    public String showHomePage(Authentication authentication, Model model) {
        model.addAttribute("username", authentication.getName());
        model.addAttribute("sleeps", sleepService.getLast5SleepsForCurrentUser());
        model.addAttribute("advice", adviceService.getAdviceForCurrentUser(authentication.getName()));
        return "homepage";
    }
}
