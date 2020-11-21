package com.cis.Model;

import com.cis.Controller.AddExampleData;
import com.cis.Utils.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;

public class Data {

    private static Data instance = null;

    private HashMap<String, User> users;
    private HashMap<String, Post> posts;
    private HashMap<String, Comment> comments;
    private HashMap<String, Discount> discounts;
    private HashMap<String, Challenge> challenges;
    private HashMap<String, TreeSet<String>> trackPaging;

    private Data() {
        this.users = new HashMap<>();
        this.posts = new HashMap<>();
        this.comments = new HashMap<>();
        this.discounts = new HashMap<>();
        this.challenges = new HashMap<>();
        this.trackPaging = new HashMap<>();
    }

    public static Data getInstance() {
        if (instance == null) {
            instance = new Data();
        }
        return instance;
    }

    public static void storeData() {
        try {
            String dataString = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(getInstance());
            FileWriter outputFile = new FileWriter(Constants.SAVE_FILE);
            outputFile.write(dataString);
            outputFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void restoreData(){
        try {
            File dataFile = new File(Constants.SAVE_FILE);
            Scanner scanner = new Scanner(dataFile);
            StringBuilder dataString = new StringBuilder();
            while(scanner.hasNextLine()){
                dataString.append(scanner.nextLine());
            }
            instance = new ObjectMapper().readValue(dataString.toString(), Data.class);
        }
        catch (FileNotFoundException e) {
            AddExampleData.addData();
            storeData();
        }
        catch (MismatchedInputException e) {
            AddExampleData.addData();
            storeData();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
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

    public HashMap<String, Challenge> getChallenges() {
        return challenges;
    }

    public void addChallenge(Challenge challenge) {
        this.challenges.put(challenge.getId(), challenge);
    }

    public HashMap<String, TreeSet<String>> getTrackPaging() {
        return trackPaging;
    }

    public void putPageTracking(String hash, TreeSet<String> posts) {
        this.trackPaging.put(hash, posts);
    }

    public void removePageTracking(String hash) {
        this.trackPaging.remove(hash);
    }

    public void clearData() {
        this.users = new HashMap<>();
        this.posts = new HashMap<>();
        this.comments = new HashMap<>();
        this.discounts = new HashMap<>();
        this.challenges = new HashMap<>();
        this.trackPaging = new HashMap<>();
    }
}
