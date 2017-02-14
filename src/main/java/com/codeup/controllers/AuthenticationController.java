package com.codeup.controllers;

import com.codeup.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by user on 2/13/17.
 */

@Controller
public class AuthenticationController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "users/login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model viewModel) {
        viewModel.addAttribute("user", new User());
        return "users/register";
    }
}
