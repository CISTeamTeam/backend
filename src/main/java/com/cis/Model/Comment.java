package com.cis.Model;

public class Comment {

    private String id;
    private String authorID;
    private String postID;
    private String text;

    public Comment() { }

    public Comment(String id, String authorID, String postID, String text) {
        this.id = id;
        this.authorID = authorID;
        this.postID = postID;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public String getAuthorID() {
        return authorID;
    }

    public String getPostID() {
        return postID;
    }

    public String getText() {
        return text;
    }
}
