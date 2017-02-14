package com.codeup.repositories;

import com.codeup.models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by user on 2/9/17.
 */
public interface Posts extends CrudRepository<Post, Long> {

//    public Post findByUser(User user);
//    public Post findById(long id);
//    public Page<Post> findAll(Pageable pageable);
//    public List<Post> findByBodyIsLike(String term);

    @Query("select p from Post p where p.title like ?1")
    public List<Post> findByTitleLike(String searchTerm);
}
