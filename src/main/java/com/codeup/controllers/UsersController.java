package com.codeup.controllers;

import com.codeup.models.User;
import com.codeup.repositories.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by user on 2/10/17.
 */
@Controller
public class UsersController {
    @Autowired
    Users usersDao;


    @GetMapping("users/{id}")
    public String showUser (@PathVariable Long id, Model viewModel) {
        User user = usersDao.findById(id);
        viewModel.addAttribute("user", user);
        return "users/show";

    }
}
