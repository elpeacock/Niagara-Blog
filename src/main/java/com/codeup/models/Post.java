package com.codeup.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Created by user on 2/8/17.
 */
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 150)
    @NotBlank(message = "Posts must have a title")
    private String title;

    @NotBlank(message = "Post must have content")
    @Size(min = 5, message = "Post Content must be at least 5 characters")
    @Column(nullable = false, length = 2000)
    private String body;

    @ManyToOne
    @JoinColumn (name = "user_id")
    @JsonManagedReference
    private User user;

    @Column
    private String image;

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public Post(){

    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
