package com.codeup.controllers;

import com.codeup.services.PostSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by user on 2/7/17.
 */
@Controller
public class PostsController {

    @Autowired
    PostSvc postSvc;

    @GetMapping("/posts")
    public String viewPosts(Model model) {

        model.addAttribute("listOfPosts", postSvc.findAll() );

        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String viewPostById(@PathVariable int id, Model model) {

        model.addAttribute("singlePost", postSvc.findOne(id) );

        return "posts/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String createPostForm() {
        return "view the form to create post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost() {
        return "create a new post";
    }

}
