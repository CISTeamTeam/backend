package com.cis.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.*;

public class Post {

    private String id;
    private String authorID;
    private String url;
    private String description;
    private double creationDate;
    private ArrayList<String> comments;
    private HashMap<String, Integer> ratings;

    public Post() {
        this.comments = new ArrayList<>();
        this.ratings = new HashMap<>();
    }

    public Post(String id, String authorID, String url, String description, double creationDate) {
        this.id = id;
        this.authorID = authorID;
        this.url = url;
        this.description = description;
        this.creationDate = creationDate;
        this.comments = new ArrayList<>();
        this.ratings = new HashMap<>();
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

    public ArrayList<String> getComments() {
        return comments;
    }

    public void addComment(String comment) {
        this.comments.add(comment);
    }

    public HashMap<String, Integer> getRatings() { return ratings; }

    @JsonIgnore
    public int getFinalRating() {
        int average = 0;
        int size = getRatings().size();
        for (String key : getRatings().keySet()) {
            int rating = getRatings().get(key);
            average += rating;
        }
        return average / size;
    }

    public void addRating(String user, int rating) {
        this.ratings.put(user, rating);
    }
}
