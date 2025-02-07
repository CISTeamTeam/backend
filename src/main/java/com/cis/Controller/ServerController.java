package com.cis.Controller;

import com.cis.Model.*;
import com.cis.Utils.Constants;
import com.cis.Utils.HTTPServer;
import com.cis.Utils.SortPostByTime;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ServerController implements HTTPServerListener {

    private static ServerController instance = null;

    private Data data;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private ServerController() {
        data = Data.getInstance();
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    @Override
    public String handleRequest(Request request) {
        // Upon receiving HTTP request from client
        try {
            switch (request.getPath()) {
                case Constants.CLEAR_DATA: return clearData(request);
                case Constants.GET_POST: return getPost(request);
                case Constants.GET_POSTS: return getPosts(request);
                case Constants.CREATE_POST: return createPost(request);
                case Constants.CAN_RATE_POST: return canRatePost(request);
                case Constants.RATE_POST: return ratePost(request);
                case Constants.GET_POST_POINTS: return getPostPoints(request);
                case Constants.AUTH: return authenticate(request);
                case Constants.GET_USER: return getUser(request);
                case Constants.CREATE_USER: return createUser(request);
                case Constants.UPDATE_USER: return updateUser(request);
                case Constants.GET_USER_POINTS: return getUserPoints(request);
                case Constants.SPEND_POINTS: return spendPoints(request);
                case Constants.GET_COMMENT: return getComment(request);
                case Constants.POST_COMMENT: return postComment(request);
                case Constants.GET_DISCOUNT: return getDiscount(request);
                case Constants.GET_DISCOUNTS: return getDiscounts(request);
                case Constants.GET_CHALLENGE: return getChallenge(request);
                case Constants.GET_CHALLENGES: return getChallenges(request);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        // If request did not match anything, fail
        return Constants.FAILURE;
    }

    private String clearData(Request request) {
        data.clearData();
        return Constants.SUCCESS;
    }


    /* ------------- */
    /* Post Requests */
    /* ------------- */

    private String getPost(Request request) {
        // Get id from request
        String id = (String) request.getParam(Constants.ID_PARAM);

        // Get post using id
        Post post = data.getPosts().get(id);
        try {
            // Return JSON
            return new ObjectMapper().writeValueAsString(post);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
            return Constants.FAILURE;
        }
    }

    private String getPosts(Request request) {
        String hash = (String) request.getParam(Constants.PAGING_HASH);
        ArrayList<String> topPosts = new ArrayList<>();
        TreeSet<String> posts;

        if (hash != null) {
            posts = data.getTrackPaging().get(hash);
        }
        else {
            hash = UUID.randomUUID().toString();
            posts = new TreeSet<>(new SortPostByTime());
            for (String postID : data.getPosts().keySet()) {
                posts.add(postID);
            }
            data.putPageTracking(hash, posts);
        }

        for (int i = 0; i < 10; i++) {
            String post;
            if ((post = posts.pollFirst()) == null) {
                break;
            }
            topPosts.add(post);
        }

        if (posts.isEmpty()) {
            data.removePageTracking(hash);
        }

        String postsJson = "";
        try {
            postsJson = new ObjectMapper().writeValueAsString(topPosts);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "{ \"hash\": \"" + hash + "\", " +
                 "\"posts\": " + postsJson + " }";
    }

    private String createPost(Request request) {
        // Get various parameters from request
        String id = (String) request.getParam(Constants.ID_PARAM);
        String userID = (String) request.getParam(Constants.USER_ID_PARAM);
        String imageURL = (String) request.getParam(Constants.URL_PARAM);
        String description = (String) request.getParam(Constants.DESCRIPTION_PARAM);
        double creationDate = (Double) request.getParam(Constants.CREATION_DATE);

        Post post = new Post(id, userID, imageURL, description, creationDate);

        // If post already exists, fail
        if (data.getPosts().containsKey(id)) {
            return Constants.FAILURE;
        }
        // Add post to data
        data.addPost(post);

        //TODO: Change back to 24 hours after testing
        scheduler.schedule(() -> data.getUsers().get(userID).addPoints(post.getFinalRating()), 10, TimeUnit.MINUTES);

        return Constants.SUCCESS;
    }

    private String canRatePost(Request request){
        // Get parameters from request. ID_PARAM is the post id and USER_ID_PARAM the user id
        String postId = (String) request.getParam(Constants.ID_PARAM);
        String userId = (String) request.getParam(Constants.USER_ID_PARAM);

        Post post = data.getPosts().get(postId);
        // If the user has already rated the post, fail
        if (post.getRatings().containsKey(userId)) {
            return Constants.FAILURE;
        }
        // Convert epoch time from milliseconds to seconds
        double epochTime = (Instant.now().toEpochMilli() / 1000.0);
        // If the post is more than 24 hours old, fail
        if (post.getCreationDate() < epochTime - 86400) {
            return Constants.FAILURE;
        }
        // Random chance of failure
        if (new Random().nextInt(3) != 0) {
            return Constants.FAILURE;
        }
        return Constants.SUCCESS;
    }

    private String ratePost(Request request) {
        // Get parameters from request
        String id = (String) request.getParam(Constants.ID_PARAM);
        String userID = (String) request.getParam(Constants.USER_ID_PARAM);
        int rating = (Integer) request.getParam(Constants.RATING_PARAM);

        User user = data.getUsers().get(userID);
        Post post = data.getPosts().get(id);

        // If post is more than 24 hours old, fail
        double epochTime = (Instant.now().toEpochMilli() / 1000.0);
        if (post.getCreationDate() < epochTime - 86400) {
            return Constants.FAILURE;
        }
        // Rate post
        post.addRating(user.getId(), rating);
        return Constants.SUCCESS;
    }

    private String getPostPoints(Request request) {
        // Get id from request
        String id = (String) request.getParam(Constants.ID_PARAM);

        int points = data.getPosts().get(id).getFinalRating();
        return "{\"points\":"+points+"}";
    }


    /* ------------- */
    /* User Requests */
    /* ------------- */

    private String authenticate(Request request){
        // Get id from request
        String id = (String) request.getParam(Constants.ID_PARAM);

        // If user already exists, return success
        if (data.getUsers().containsKey(id)){
            return Constants.SUCCESS;
        }
        return Constants.FAILURE;
    }

    private String getUser(Request request) {
        // Get id from request
        String id = (String) request.getParam(Constants.ID_PARAM);

        User user = data.getUsers().get(id);
        try {
            // Return JSON of user
            return new ObjectMapper().writeValueAsString(user);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
            return Constants.FAILURE;
        }
    }

    private String createUser(Request request){
        // Get parameters from request
        String id = (String) request.getParam(Constants.ID_PARAM);
        String bio = (String) request.getParam(Constants.BIO_PARAM);
        String profilePictureURL = (String) request.getParam(Constants.PFP_URL_PARAM);
        String username = (String) request.getParam(Constants.USERNAME_PARAM);
        String name = (String) request.getParam(Constants.NAME_PARAM);

        User user = new User(id, username, name, bio, profilePictureURL);

        // If user already exists, fail
        if (data.getUsers().containsKey(id)) {
            return Constants.FAILURE;
        }
        data.addUser(user);
        return Constants.SUCCESS;
    }

    private String updateUser(Request request){
        // Get parameters from request
        String id = (String) request.getParam(Constants.ID_PARAM);
        String bio = (String) request.getParam(Constants.BIO_PARAM);
        String pfpURL = (String) request.getParam(Constants.PFP_URL_PARAM);
        String username = (String) request.getParam(Constants.USERNAME_PARAM);
        String name = (String) request.getParam(Constants.NAME_PARAM);

        User user = data.getUsers().get(id);

        // Update user info
        user.setBio(bio);
        user.setProfilePictureURL(pfpURL);
        user.setUsername(username);
        user.setName(name);

        return Constants.SUCCESS;
    }

    private String getUserPoints(Request request) {
        // Get id from request
        String id = (String) request.getParam(Constants.ID_PARAM);

        int points = data.getUsers().get(id).getPoints();
        return "{ \"id\": " + "\"" + id + "\"" + ", \"points\": " + points + " }";
    }

    private String spendPoints(Request request) {
        // Get parameters from request
        String id = (String) request.getParam(Constants.ID_PARAM);
        int pointsSpent = (Integer) request.getParam(Constants.POINTS_PARAM);

        User user = data.getUsers().get(id);
        // If user tries to spend more points than they currently have, fail
        if(user.getPoints()<pointsSpent){
            return Constants.FAILURE;
        }
        user.subtractPoints(pointsSpent);
        return Constants.SUCCESS;
    }


    /* ---------------- */
    /* Comment Requests */
    /* ---------------- */

    private String getComment(Request request){
        // Get id from request
        String id = (String) request.getParam(Constants.ID_PARAM);

        Comment comment = data.getComments().get(id);
        try {
            return new ObjectMapper().writeValueAsString(comment);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
            return Constants.FAILURE;
        }
    }

    private String postComment(Request request){
        // Get parameters from request
        String id = (String) request.getParam(Constants.ID_PARAM);
        String authorId = (String) request.getParam(Constants.USER_ID_PARAM);
        String postId = (String) request.getParam(Constants.POST_ID_PARAM);
        String text = (String) request.getParam(Constants.TEXT_PARAM);
        double creationDate = (Double) request.getParam(Constants.CREATION_DATE); // Date is in seconds

        Comment comment = new Comment(id, authorId, postId, text, creationDate);
        // Add comment to post object
        data.getPosts().get(postId).addComment(id);
        data.addComment(comment);

        return Constants.SUCCESS;
    }


    /* ----------------- */
    /* Discount Requests */
    /* ----------------- */

    private String getDiscount(Request request) {
        // Get id from request
        String id = (String) request.getParam(Constants.ID_PARAM);

        Discount discount = data.getDiscounts().get(id);
        try {
            return new ObjectMapper().writeValueAsString(discount);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
            return Constants.FAILURE;
        }
    }

    private String getDiscounts(Request request) {
        // Get all discount IDs
        ArrayList<String> discounts = new ArrayList<>(data.getDiscounts().keySet());

        try {
            return "{ \"discounts\": " + new ObjectMapper().writeValueAsString(discounts) + " }";
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
            return Constants.FAILURE;
        }
    }


    /* ------------------ */
    /* Challenge Requests */
    /* ------------------ */

    private String getChallenge(Request request) {
        // Get id from request
        String id = (String) request.getParam(Constants.ID_PARAM);
        Challenge challenge = data.getChallenges().get(id);
        try {
            return new ObjectMapper().writeValueAsString(challenge);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Constants.FAILURE;
    }

    private String getChallenges(Request request) {
        ArrayList<String> challenges = new ArrayList<>();
        Iterator it = data.getChallenges().keySet().iterator();
        while (it.hasNext()) {
            Challenge challenge = data.getChallenges().get(it.next());
            double epochTime = (Instant.now().toEpochMilli() / 1000.0);
            if (epochTime <= challenge.getEndDate()) { // If challenge is not over yet
                challenges.add(challenge.getId());
            }
        }
        try {
            return "{ \"challenges\": " + new ObjectMapper().writeValueAsString(challenges) + " }";
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Constants.FAILURE;
    }


    /* ----------- */
    /* Main Method */
    /* ----------- */

    public static void main(String[] args) {
        Data.getInstance();
        Data.restoreData(); // Attempt to restore data from save.txt
        ServerController.getInstance().saveServer();
        HTTPServer server = new HTTPServer(ServerController.getInstance(), Constants.PORT);
        server.start();
    }

    public void saveServer() {
        final Runnable save = Data::storeData;
        scheduler.scheduleAtFixedRate(save, 1, 1, TimeUnit.MINUTES);
    }
}
