package com.cis.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Post {

    private String id;
    private String authorID;
    private String url;
    private String description;
    private int creationDate;
    private ArrayList<String> comments;
    @JsonIgnore
    private Map<String, Integer> ratings;

    public Post() {
        this.comments = (ArrayList<String>) Collections.synchronizedList(new ArrayList<String>());
        this.ratings = Collections.synchronizedMap(new HashMap<>());
    }

    public Post(String id, String authorID, String url, String description, int creationDate) {
        this.id = id;
        this.authorID = authorID;
        this.url = url;
        this.description = description;
        this.creationDate = creationDate;
        this.comments = (ArrayList<String>) Collections.synchronizedList(new ArrayList<String>());
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

    public int getCreationDate() {
        return creationDate;
    }

    public ArrayList<String> getComments() {
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
