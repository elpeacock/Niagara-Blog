package com.codeup.controllers;

import com.codeup.models.Post;
import com.codeup.models.User;
import com.codeup.repositories.Posts;
import com.codeup.services.PostSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

//        for(Post post: postsDao.findByTitleLike("%hipster%")) {
//            System.out.println(post.getTitle());
//        }


        return "posts/index";
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
    public String createPost(@ModelAttribute Post post) {
        //this will change when we pull the user id from the session.
        //for now, i have hard coded the user id.
        User user = new User();
        user.setId(1);
        post.setUser(user);

        postSvc.save(post);
        return "redirect:/posts";
    }

    @PostMapping("/posts/delete")
    public String deletePost(@ModelAttribute Post post){
        postSvc.delete(post.getId());
        return "redirect:/posts";
    }

}
