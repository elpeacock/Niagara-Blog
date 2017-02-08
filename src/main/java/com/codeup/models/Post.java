package com.codeup.models;

/**
 * Created by user on 2/8/17.
 */
public class Post {
    private long id;
    private String title;
    private String body;


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

}
