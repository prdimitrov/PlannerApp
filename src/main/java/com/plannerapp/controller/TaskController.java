package com.plannerapp.controller;

import com.plannerapp.model.bindingModels.AddTaskModel;
import com.plannerapp.service.TaskService;
import com.plannerapp.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final CurrentUser currentUser;

    public TaskController(TaskService taskService, CurrentUser currentUser) {
        this.taskService = taskService;
        this.currentUser = currentUser;
    }

    @ModelAttribute("taskModel")
    public AddTaskModel taskModel() {
        return new AddTaskModel();
    }

    @GetMapping("/add")
    public String getAddForm() {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }
        return "task-add";
    }

    @PostMapping("/add")
    public String addTask(@Valid AddTaskModel taskModel,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

        //IF USER IS NOT LOGGED IN!!!!!
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("taskModel", taskModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.taskModel", bindingResult);
            //redirect to tasks/add, if there are errors!
            return "redirect:/tasks/add";
        }
        // if everything's okay, add the task and redirect to the home page!
        taskService.addTask(taskModel);
        return "redirect:/home";
    }

    @GetMapping("/assign/{id}")
    public String assignTask(@PathVariable Long id) {
        // If user is not logged in!!!!!
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }
        //If everything's okay, assign the task and redirect to the home page!
        taskService.assignTask(id);
        return "redirect:/home";
    }

    @GetMapping("/remove/{id}")
    public String removeTask(@PathVariable Long id) {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }
        //If everything's okay, remove the task and redirect to the home page!
        taskService.removeTask(id);
        return "redirect:/home";
    }

    @GetMapping("/return/{id}")
    public String returnTask(@PathVariable Long id) {
        //If everything's okay, return the task and redirect to the home page!
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }
        taskService.returnTask(id);
        return "redirect:/home";
    }
}
