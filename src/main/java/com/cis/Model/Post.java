package com.cis.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;

public class Post {

    private String id;
    private String authorID;
    private String url;
    private String description;
    private double creationDate;
    private List<String> comments;
    @JsonIgnore
    private Map<String, Integer> ratings;

    public Post() {
        this.comments = Collections.synchronizedList(new ArrayList<String>());
        this.ratings = Collections.synchronizedMap(new HashMap<>());
    }

    public Post(String id, String authorID, String url, String description, double creationDate) {
        this.id = id;
        this.authorID = authorID;
        this.url = url;
        this.description = description;
        this.creationDate = creationDate;
        this.comments = Collections.synchronizedList(new ArrayList<String>());
        this.ratings = Collections.synchronizedMap(new HashMap<>());
    }

    public String getId() {
        return id;
    }

    public String getAuthorID() {
        return authorID;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public double getCreationDate() {
        return creationDate;
    }

    public List<String> getComments() {
        return comments;
    }

    public void addComment(String comment) {
        this.comments.add(comment);
    }

    public int getRating() {
        //TODO add rating algorithm
        return 0;
    }

    public void addRating(String user, int rating) {
        this.ratings.put(user, rating);
    }
}
