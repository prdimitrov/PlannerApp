package com.plannerapp.controller;

import com.plannerapp.model.bindingModels.UserLoginModel;
import com.plannerapp.model.bindingModels.UserRegisterModel;
import com.plannerapp.service.UserService;
import com.plannerapp.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final CurrentUser currentUser;

    public UserController(CurrentUser currentUser, UserService userService) {
        this.currentUser = currentUser;
        this.userService = userService;
    }

    @ModelAttribute("userModel")
    public void initUserModel(Model model) {
        model.addAttribute("userModel", new UserRegisterModel());
    }

    @GetMapping("/register")
    public String getRegister() {
        if (currentUser.isLoggedIn()) {
            return "redirect:/home";
        }
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegisterModel userModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (currentUser.isLoggedIn()) {
            return "redirect:/home";
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userModel", userModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userModel", bindingResult);
            return "redirect:/users/register";
        }

        userService.registerUser(userModel);

        return "redirect:/users/login";
    }

    @ModelAttribute("loginModel")
    public void initLoginModel(Model model) {
        model.addAttribute("loginModel", new UserLoginModel());
    }

    @GetMapping("/login")
    public String getLogin() {
        if (currentUser.isLoggedIn()) {
            return "redirect:/home";
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid UserLoginModel loginModel,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {
        if (currentUser.isLoggedIn()) {
            return "redirect:/home";
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginModel", loginModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginModel", bindingResult);
            return "redirect:/users/login";
        }

        userService.login(loginModel);
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout() {
        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }
        userService.logout();
        return "redirect:/";
    }

}
