package com.cis.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;

public class User {

    private String id;
    private String username;
    private String name;
    private String bio;
    private String profilePictureURL;
    private SortedSet<String> posts;
    private Set<String> followers;
    private Set<String> following;
    @JsonIgnore
    private int points;

    class SortPostByTime implements Comparator<String> {
        @Override
        public int compare(String postUID1, String postUID2) {
            Post post1 = Data.getInstance().getPosts().get(postUID1);
            Post post2 = Data.getInstance().getPosts().get(postUID2);

            return post1.getCreationDate() - post2.getCreationDate();
        }
    }

    public User() {
        this.posts = Collections.synchronizedSortedSet(new TreeSet<>(new SortPostByTime()));
        this.followers = Collections.synchronizedSet(new LinkedHashSet<>());
        this.following = Collections.synchronizedSet(new LinkedHashSet<>());
        this.points = 0;
    }

    public User(String id, String username, String name, String bio, String profilePictureURL, int points) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.bio = bio;
        this.profilePictureURL = profilePictureURL;
        this.posts = Collections.synchronizedSortedSet(new TreeSet<>(new SortPostByTime()));
        this.followers = Collections.synchronizedSet(new LinkedHashSet<>());
        this.following = Collections.synchronizedSet(new LinkedHashSet<>());
        this.points = points;
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

    public SortedSet<String> getPosts() {
        return posts;
    }

    public void addPost(String post) {
        this.posts.add(post);
    }

    public Set<String> getFollowers() {
        return followers;
    }

    public void addFollower(String user) {
        this.followers.add(user);
    }

    public void removeFollower(String user) {
        this.followers.remove(user);
    }

    public Set<String> getFollowing() {
        return following;
    }

    public void addFollowing(String user) {
        this.following.add(user);
    }

    public void removeFollowing(String user) {
        this.following.remove(user);
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public void subtractPoints(int points) {
        this.points -= points;
    }
}
