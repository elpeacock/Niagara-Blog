package com.codeup.services;

import com.codeup.models.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2/8/17.
 */
@Service("postSvc")
public class PostSvc {
    private List<Post> posts = new ArrayList<>();

    public PostSvc() {
        createPosts();
    }

    public List<Post> findAll() {
        return posts;
    }

    public Post save(Post post) {
        post.setId(posts.size() + 1);
        posts.add(post);
        return post;
    }

    public Post findOne(int id) {
        return posts.get(id - 1);
    }

    private void createPosts() {
        for(int i = 0; i < 50; i++) {
            save(new Post("title " + (i + 1), "Hexagon keytar post-ironic heirloom gochujang quinoa brunch " +
                    "microdosing, whatever ethical locavore helvetica. Meh fingerstache la croix gentrify, readymade " +
                    "chia farm-to-table waistcoat hell of swag man braid polaroid freegan. Church-key beard offal, bitters freegan readymade chartreuse coloring book thundercats chambray photo booth ugh affogato."));
        }
    }
}
