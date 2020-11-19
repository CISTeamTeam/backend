package com.cis.Model;

import com.cis.Utils.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Scanner;

public class Data {

    private static Data instance = null;

    private HashMap<String, User> users;
    private HashMap<String, Post> posts;
    private HashMap<String, Comment> comments;
    private HashMap<String, Discount> discounts;

    private Data() {
        this.users = new HashMap<>();
        this.posts = new HashMap<>();
        this.comments = new HashMap<>();
        this.discounts = new HashMap<>();
    }

    public static Data getInstance() {
        if (instance == null) {
            instance = new Data();
            instance.addUser(new User(Constants.ANONYMOUS_USER, "anon", "anon", "anon", null, 0));
            instance.addUser(new User("abcde", "", "", "", null, 0));
            instance.addUser(new User("test", "", "", "", null, 0));
            instance.addPost(new Post("abcde2", "abcde", "url", "desc", 5.0));
        }
        return instance;
    }

    public HashMap<String, User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        this.users.put(user.getId(), user);
    }

    public HashMap<String, Post> getPosts() {
        return posts;
    }

    public void addPost(Post post) {
        this.posts.put(post.getId(), post);
    }

    public HashMap<String, Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment) {
        this.comments.put(comment.getId(), comment);
    }

    public HashMap<String, Discount> getDiscounts() {
        return discounts;
    }

    public void addDiscount(Discount discount) {
        this.discounts.put(discount.getId(), discount);
    }

    public void clearData() {
        this.users = new HashMap<>();
        this.posts = new HashMap<>();
        this.comments = new HashMap<>();
        this.discounts = new HashMap<>();
        instance.addUser(new User(Constants.ANONYMOUS_USER, "anon", "anon", "anon", null, 0));
    }

    public static void storeData(String filename) {
        try {
            String dataString = new ObjectMapper().writeValueAsString(getInstance());
            FileWriter outputFile = new FileWriter(filename);
            outputFile.write(dataString);
            outputFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void restoreData(String filename){
        try {
            File dataFile = new File(filename);
            Scanner scanner = new Scanner(dataFile);
            String dataString = "";
            while(scanner.hasNextLine()){
                dataString += scanner.nextLine();
            }
            instance = new ObjectMapper().readValue(dataString, Data.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
