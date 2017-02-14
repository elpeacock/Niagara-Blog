package com.codeup.services;

import com.codeup.models.Post;
import com.codeup.repositories.Posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by user on 2/8/17.
 */
@Service("postSvc")
public class PostSvc {
    @Autowired
    Posts postsDao;

    public PostSvc() {
        createPosts();
    }

    public Iterable<Post> findAll() {

        return postsDao.findAll();
    }

    public Post save(Post post) {
        postsDao.save(post);
        return post;
    }

    public Post findOne(long id) {

        return postsDao.findOne(id);
    }

    private void createPosts() {

    }

    public void delete(long id) {

        postsDao.delete(findOne(id));
    }

}
