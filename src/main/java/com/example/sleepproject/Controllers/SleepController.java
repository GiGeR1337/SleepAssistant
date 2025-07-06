package com.example.sleepproject.Controllers;

import com.example.sleepproject.DTOs.SleepDto;
import com.example.sleepproject.Services.SleepQualityService;
import com.example.sleepproject.Services.SleepService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.script.Bindings;

@Controller
public class SleepController {
    private final SleepService sleepService;
    private final SleepQualityService sleepQualityService;

    public SleepController(SleepService sleepService, SleepQualityService sleepQualityService) {
        this.sleepService = sleepService;
        this.sleepQualityService = sleepQualityService;
    }

    @GetMapping("/sleeps")
    public String getAllSleeps(Authentication authentication, Model model){
        model.addAttribute("sleeps", sleepService.getAllSleeps());
        return "sleeps-page";
    }

    @GetMapping("/addSleep")
    public String showAddSleepForm(Model model) {
        model.addAttribute("sleepDto", new SleepDto());
        model.addAttribute("sleepQualities", sleepQualityService.findAll());
        return "create-sleep";
    }

    @PostMapping("/addSleep")
    public String submitSleepForm(@ModelAttribute @Valid SleepDto sleepDto,
                                  BindingResult bindingResult,
                                  Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("sleepQualities", sleepQualityService.findAll());
            return "create-sleep";
        }

        sleepService.addSleep(sleepDto);
        return "redirect:/home";
    }

    @GetMapping("/sleeps/edit/{id}")
    public String showEditSleepForm(@PathVariable Long id, Model model){
        SleepDto sleepDto = sleepService.getSleepDtoById(id);

        model.addAttribute("sleepDto", sleepDto);
        model.addAttribute("idSleep", id);
        model.addAttribute("sleepQualities", sleepQualityService.findAll());
        return "edit-sleep";
    }

    @PostMapping("/sleeps/edit")
    public String submitEditSleepForm(@RequestParam("idSleep") Long id,
                                      @ModelAttribute @Valid SleepDto sleepDto,
                                      BindingResult bindingResult,
                                      Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("idSleep", id);
            model.addAttribute("sleepQualities", sleepQualityService.findAll());

            return "edit-sleep";
        }

        sleepService.editSleep(id, sleepDto);
        return "redirect:/sleeps";
    }

    @PostMapping("/sleeps/delete/{id}")
    public String deleteSleep(@PathVariable Long id){
        sleepService.deleteSleepById(id);
        return "redirect:/sleeps";
    }

}
