package com.codeup.controllers;

import com.codeup.models.Post;
import com.codeup.repositories.Posts;
import com.codeup.services.PostSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by user on 2/7/17.
 */
@Controller
public class PostsController {

    @Autowired
    PostSvc postSvc;

    @Autowired
    Posts postsDao;

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
    public String showCreatePostForm(@ModelAttribute Post post, Model viewModel) {

        viewModel.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post) {

        postSvc.save(post);
        return "redirect:/posts";
    }

}
