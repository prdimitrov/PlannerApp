package com.plannerapp.controller;

import com.plannerapp.service.TaskService;
import com.plannerapp.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final CurrentUser currentUser;
    private final TaskService taskService;

    public HomeController(CurrentUser currentUser, TaskService taskService) {
        this.currentUser = currentUser;
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String index() {
        if (currentUser.isLoggedIn()) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model) {
        //If use is not logged in!
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }
        //If use is logged in!
        model.addAttribute("tasks", taskService.findAll());
        model.addAttribute("taskCount", taskService.findAll().size());
        model.addAttribute("myTasks", taskService.getMyTasks());

        return "home";
    }
}
