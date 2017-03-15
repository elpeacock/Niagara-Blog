package com.codeup.controllers;

import com.codeup.models.Post;
import com.codeup.repositories.Posts;
import com.codeup.services.PostSvc;
import com.codeup.services.UserSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

/**
 * Created by user on 2/7/17.
 */
@Controller
public class PostsController {

    @Autowired
    PostSvc postSvc;

    @Autowired
    Posts postsDao;

    @Autowired
    UserSvc usersSvc;

    @Value("${uploads}")
    private String uploadsPath;

    @GetMapping("/posts")
    public String viewPosts(Model model) {

        model.addAttribute("listOfPosts", Collections.emptyList());

//        for(Post post: postsDao.findByTitleLike("%hipster%")) {
//            System.out.println(post.getTitle());
//        }


        return "posts/index";
    }

    @GetMapping("/posts.json")
    public @ResponseBody List<Post> retrieveAllPosts() {
        return (List<Post>) postSvc.findAll();
    }

    @GetMapping("/posts/{id}")
    public String viewPostById(@PathVariable long id, Model model) {

        model.addAttribute("singlePost", postSvc.findOne(id) );

        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String showCreatePostForm(@ModelAttribute Post post, Model viewModel) {
        viewModel.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(
            @Valid Post post,
            Errors validation,
            Model viewModel,
            @RequestParam(name = "image-file") MultipartFile uploadedFile
    ) {
        if (validation.hasErrors()) {
            viewModel.addAttribute("errors", validation);
            viewModel.addAttribute("post", post);
            return "posts/create";
        }
        String filename = uploadedFile.getOriginalFilename();
        System.out.println(filename);
        String filepath = Paths.get(uploadsPath, filename).toString();
        System.out.println(filepath);
        File destinationFile = new File(filepath);
        try {
            uploadedFile.transferTo(destinationFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        post.setUser(usersSvc.loggedInUser());
        post.setImage(filename);
        postSvc.save(post);

        return "redirect:/posts";
    }

    @PostMapping("/posts/delete")
    public String deletePost(@ModelAttribute Post post){
        postSvc.delete(post.getId());
        return "redirect:/posts";
    }


}
