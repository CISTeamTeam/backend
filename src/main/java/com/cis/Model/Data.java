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
