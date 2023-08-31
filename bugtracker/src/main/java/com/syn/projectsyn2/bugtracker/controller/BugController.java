package com.syn.projectsyn2.bugtracker.controller;

import com.syn.projectsyn2.bugtracker.service.BugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller  // Note that we are using @Controller instead of @RestController
public class BugController {

    @Autowired
    private BugService bugService;

    @GetMapping("/bugdashboard")  // This should match the URL you want to navigate to
    public String showBugDashboard(Model model) {
        model.addAttribute("bugs", bugService.getAllBugs());  // Fetch all bugs and add to model
        return "bugDash";  // Name of your Thymeleaf HTML file (without .html)
    }
}
