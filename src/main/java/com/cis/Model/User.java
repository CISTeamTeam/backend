package com.cis.Model;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class User {

    private String id;
    private String username;
    private String name;
    private String bio;
    private String profilePictureURL;
    private TreeSet<String> posts;
    private LinkedHashSet<String> followers;
    private LinkedHashSet<String> following;

    class SortPostByTime implements Comparator<String> {
        @Override
        public int compare(String postUID1, String postUID2) {
            Post post1 = Data.getInstance().getPosts().get(postUID1);
            Post post2 = Data.getInstance().getPosts().get(postUID2);

            return post1.getCreationDate() - post2.getCreationDate();
        }
    }

    public User() {
        this.posts = (TreeSet<String>) Collections.synchronizedSortedSet(new TreeSet<>(new SortPostByTime()));
        this.followers = (LinkedHashSet<String>) Collections.synchronizedSet(new LinkedHashSet<String>());
        this.following = (LinkedHashSet<String>) Collections.synchronizedSet(new LinkedHashSet<String>());
    }

    public User(String id, String username, String name, String bio, String profilePictureURL) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.bio = bio;
        this.profilePictureURL = profilePictureURL;
        this.posts = (TreeSet<String>) Collections.synchronizedSortedSet(new TreeSet<>(new SortPostByTime()));
        this.followers = (LinkedHashSet<String>) Collections.synchronizedSet(new LinkedHashSet<String>());
        this.following = (LinkedHashSet<String>) Collections.synchronizedSet(new LinkedHashSet<String>());
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

    public LinkedHashSet<String> getFollowers() {
        return followers;
    }

    public void addFollower(String user) {
        this.followers.add(user);
    }

    public void removeFollower(String user) {
        this.followers.remove(user);
    }

    public LinkedHashSet<String> getFollowing() {
        return following;
    }

    public void addFollowing(String user) {
        this.following.add(user);
    }

    public void removeFollowing(String user) {
        this.following.remove(user);
    }
}
