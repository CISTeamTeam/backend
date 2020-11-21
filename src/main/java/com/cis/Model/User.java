package com.cis.Model;

import com.cis.Utils.SortPostByTime;

import java.util.TreeSet;

public class User {

    private String id;
    private String username;
    private String name;
    private String bio;
    private String profilePictureURL;
    private TreeSet<String> posts;
    private int points;

    public User() {
        this.posts = new TreeSet<>(new SortPostByTime());
        this.points = 400;
    }

    public User(String id, String username, String name, String bio, String profilePictureURL) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.bio = bio;
        this.profilePictureURL = profilePictureURL;
        this.posts = new TreeSet<>(new SortPostByTime());
        this.points = 400;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfilePictureURL() {
        return profilePictureURL;
    }

    public void setProfilePictureURL(String profilePictureURL) {
        this.profilePictureURL = profilePictureURL;
    }

    public TreeSet<String> getPosts() {
        return posts;
    }

    public void addPost(String post) {
        this.posts.add(post);
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public void subtractPoints(int points) {
        this.points -= points;
    }
}
