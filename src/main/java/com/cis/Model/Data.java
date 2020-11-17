package com.cis.Model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Data {

    private static Data instance = null;

    private Map<String, User> users;
    private Map<String, Post> posts;
    private Map<String, Comment> comments;

    private Data() {
        this.users = Collections.synchronizedMap(new HashMap<>());
        this.posts = Collections.synchronizedMap(new HashMap<>());
        this.comments = Collections.synchronizedMap(new HashMap<>());
    }

    public static Data getInstance() {
        if (instance == null) {
            instance = new Data();
            instance.addUser(new User("dad1d1e3-843d-49e7-b1ce-18694ae14f86", "seek", "Isaac",
                                      "likes to code", "https://i.imgur.com/0loCM3K.jpg"));
            instance.addUser(new User("0487193b-eaad-4fb6-8eca-bc8b54879325", "flooflez", "Max",
                                      "frickin brony", "https://i.imgur.com/0loCM3K.jpg"));
        }
        return instance;
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        this.users.put(user.getId(), user);
    }

    public Map<String, Post> getPosts() {
        return posts;
    }

    public void addPost(Post post) {
        this.posts.put(post.getId(), post);
    }

    public Map<String, Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment) {
        this.comments.put(comment.getId(), comment);
    }

    public void clearData() {
        this.users = Collections.synchronizedMap(new HashMap<>());
        this.posts = Collections.synchronizedMap(new HashMap<>());
        this.comments = Collections.synchronizedMap(new HashMap<>());
    }
}
