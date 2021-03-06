package com.codeup.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by user on 2/10/17.
 */
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(nullable = false)
    @NotBlank(message = "You must enter a username")
    private String username;

    @Column(nullable = false)
    @Email(message = "Enter a valid email")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "You must enter a password")
    @Size(min = 8, message = "Your password must contain at least 8 characters")
    @JsonIgnore //add this annotation to any fields that contain sensitive info
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonBackReference
    private List<Post> posts;

    // this is a copy constructor -> an alternative to clone
    // this is being called by the UserWithRoles service
    public User(User user) {
        id = user.id;
        username = user.username;
        password = user.password;
        email = user.email;
        posts = user.posts;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public User() {

    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
