package com.syn.projectsyn2.bugtracker.controller;

import com.syn.projectsyn2.bugtracker.domain.Project;
import com.syn.projectsyn2.bugtracker.dto.LoginDto;
import com.syn.projectsyn2.bugtracker.dto.RegDto;
import com.syn.projectsyn2.bugtracker.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class LandingController {

    @Autowired
    private UserService userService;

    // Removed HttpServletRequest from the parameter
    @GetMapping("/landing")
    public String showLandingPage(Model model) {
        model.addAttribute("user", new RegDto());
        model.addAttribute("loginDto", new LoginDto());
        return "landing";
    }

    // Removed HttpServletRequest from the parameter
    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute RegDto user) {
        userService.registerNewUser(user);
        return "redirect:/userDash";
    }

    // Removed HttpServletRequest from the parameter
    @PostMapping("/login")
    public String loginUser(@ModelAttribute LoginDto loginDto) {
        boolean isValidUser = userService.validateUser(loginDto);
        if (isValidUser) {
            return "redirect:/userDash";
    
        }
        return "redirect:/landing";
    }

    // Removed HttpServletRequest from the parameter and related code
    @GetMapping("/userDash")
    public String showUserDashboard(Model model) {
        // You can populate the model attributes as required
        return "userDash";
    }
}
