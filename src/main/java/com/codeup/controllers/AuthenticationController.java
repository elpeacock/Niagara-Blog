package com.codeup.controllers;

import com.codeup.models.User;
import com.codeup.models.UserRole;
import com.codeup.repositories.Roles;
import com.codeup.repositories.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * Created by user on 2/13/17.
 */

@Controller
public class AuthenticationController {

    private Users usersDao;
    private PasswordEncoder encoder;

    @Autowired
    Roles userRoles;

    @Autowired
    public AuthenticationController(Users usersDao, PasswordEncoder encoder) {
        this.usersDao = usersDao;
        this.encoder = encoder;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "users/login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model viewModel) {
        viewModel.addAttribute("user", new User());
        return "/users/register";
    }

    @PostMapping("/users/register")
    public String registerUser(
            @Valid User user,
            Errors validation,
            Model viewModel,
            @RequestParam(name = "confirmPassword") String passwordConfirmation
    ) {
        if (!passwordConfirmation.equals(user.getPassword())) {
            validation.rejectValue("password", "user.password", "passwords do not match");
        }
        if (validation.hasErrors()){
            viewModel.addAttribute("errors", validation);
            viewModel.addAttribute("user", user);
            return "users/register";
        }

        String hash = encoder.encode(user.getPassword());
        user.setPassword(hash);
        User newUser = usersDao.save(user);

        UserRole ur = new UserRole();
        ur.setRole("ROLE_USER");
        ur.setUserId(newUser.getId());
        userRoles.save(ur);
        return "redirect:/login";
    }


}
