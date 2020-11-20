package com.cis.Controller;

import com.cis.Model.*;
import com.cis.Utils.Constants;
import com.cis.Utils.HTTPServer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.*;

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
        try {
            switch (request.getPath()) {
                case Constants.CLEAR_DATA: return clearData(request);
                case Constants.GET_POST: return getPost(request);
                case Constants.GET_POSTS: return getPosts(request);
                case Constants.CREATE_POST: return createPost(request);
                case Constants.READ_POST: return readPost(request);
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
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
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
        String id = (String) request.getParam(Constants.ID_PARAM);
        Post post = data.getPosts().get(id);
        try {
            return new ObjectMapper().writeValueAsString(post);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
            return Constants.FAILURE;
        }
    }

    private String getPosts(Request request) {
        String userID = (String) request.getParam(Constants.USER_ID_PARAM);
        String hash = (String) request.getParam(Constants.PAGING_HASH);
        ArrayList<String> topPosts = new ArrayList<>();
        TreeSet<String> posts;
        User user;

        if (userID != null) {
            user = data.getUsers().get(userID);
        }
        else {
            user = data.getUsers().get(Constants.ANONYMOUS_USER);
        }

        if (hash != null) {
            posts = (TreeSet<String>) user.getTrackPaging().get(hash);
        }
        else {
            hash = UUID.randomUUID().toString();
            posts = (TreeSet<String>) (user.getUnreadPosts()).clone();
        }

        for (int i = 0; i < 10; i++) {
            String post;
            if ((post = posts.pollFirst()) == null) {
                break;
            }
            topPosts.add(post);
        }
        if (posts.size() > 0) {
            user.putPagingRequest(hash, posts);
        }
        else {
            user.removePagingRequest(hash);
        }
        String postsJson = "";
        try {
            postsJson = new ObjectMapper().writeValueAsString(topPosts);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "{ \"hash\": \"" + userID + "\", " +
                 "\"posts\": " + postsJson + " }";
    }

    private String createPost(Request request) {
        String id = (String) request.getParam(Constants.ID_PARAM);
        String userID = (String) request.getParam(Constants.USER_ID_PARAM);
        String imageURL = (String) request.getParam(Constants.URL_PARAM);
        String description = (String) request.getParam(Constants.DESCRIPTION_PARAM);
        double creationDate = (Double) request.getParam(Constants.CREATION_DATE);

        Post post = new Post(id, userID, imageURL, description, creationDate);

        if (data.getPosts().containsKey(id)) {
            return Constants.FAILURE;
        }
        data.addPost(post);
        data.getUsers().get(userID).addPost(post.getId());
        for (String otherUserID : data.getUsers().keySet()) {
            User otherUser = data.getUsers().get(otherUserID);
            otherUser.addUnreadPost(post.getId());
        }
        return Constants.SUCCESS;
    }

    private String readPost(Request request) {
        String id = (String) request.getParam(Constants.ID_PARAM);
        String userID = (String) request.getParam(Constants.USER_ID_PARAM);

        User user = data.getUsers().get(userID);
        user.removeUnreadPost(id);

        return Constants.SUCCESS;
    }

    private String ratePost(Request request) {
        String id = (String) request.getParam(Constants.ID_PARAM);
        String userID = (String) request.getParam(Constants.USER_ID_PARAM);
        int rating = (Integer) request.getParam(Constants.RATING_PARAM);

        User user = data.getUsers().get(userID);
        Post post = data.getPosts().get(id);

        post.addRating(user.getId(), rating);

        return Constants.SUCCESS;
    }

    private String getPostPoints(Request request) {
        String id = (String) request.getParam(Constants.ID_PARAM);

        int points = data.getPosts().get(id).getRating();
        return "{\"points\":"+points+"}";
    }


    /* ------------- */
    /* User Requests */
    /* ------------- */

    private String authenticate(Request request){
        String id = (String) request.getParam(Constants.ID_PARAM);

        if(data.getUsers().containsKey(id)){
            return Constants.SUCCESS;
        }
        return Constants.FAILURE;
    }

    private String getUser(Request request) {
        String id = (String) request.getParam(Constants.ID_PARAM);

        User user = data.getUsers().get(id);
        try {
            return new ObjectMapper().writeValueAsString(user);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
            return Constants.FAILURE;
        }
    }

    private String createUser(Request request){
        String id = (String) request.getParam(Constants.ID_PARAM);
        String bio = (String) request.getParam(Constants.BIO_PARAM);
        String profilePictureURL = (String) request.getParam(Constants.PFP_URL_PARAM);
        String username = (String) request.getParam(Constants.USERNAME_PARAM);
        String name = (String) request.getParam(Constants.NAME_PARAM);

        User user = new User(id, bio, profilePictureURL, username, name, 0);

        if (data.getUsers().containsKey(id)) {
            return Constants.FAILURE;
        }
        data.addUser(user);
        return Constants.SUCCESS;
    }

    private String updateUser(Request request){
        String id = (String) request.getParam(Constants.ID_PARAM);
        String bio = (String) request.getParam(Constants.BIO_PARAM);
        String pfpURL = (String) request.getParam(Constants.PFP_URL_PARAM);
        String username = (String) request.getParam(Constants.USERNAME_PARAM);
        String name = (String) request.getParam(Constants.NAME_PARAM);

        User user = data.getUsers().get(id);

        user.setBio(bio);
        user.setProfilePictureURL(pfpURL);
        user.setUsername(username);
        user.setName(name);

        return Constants.SUCCESS;
    }

    private String getUserPoints(Request request) {
        String id = (String) request.getParam(Constants.ID_PARAM);

        int points = data.getUsers().get(id).getPoints();
        return "{ \"userID\": " + id + ", \"points\": " + points + " }";
    }

    private String spendPoints(Request request) {
        String id = (String) request.getParam(Constants.ID_PARAM);
        int pointsSpent = (Integer) request.getParam(Constants.POINTS_PARAM);

        User user = data.getUsers().get(id);
        user.subtractPoints(pointsSpent);
        return Constants.SUCCESS;
    }


    /* ---------------- */
    /* Comment Requests */
    /* ---------------- */

    private String getComment(Request request){
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
        String id = (String) request.getParam(Constants.ID_PARAM);
        String authorId = (String) request.getParam(Constants.USER_ID_PARAM);
        String postId = (String) request.getParam(Constants.POST_ID_PARAM);
        String text = (String) request.getParam(Constants.TEXT_PARAM);
        double creationDate = (Double) request.getParam(Constants.CREATION_DATE);

        Comment comment = new Comment(id, authorId, postId, text, creationDate);
        data.addComment(comment);

        return Constants.SUCCESS;
    }


    /* ----------------- */
    /* Discount Requests */
    /* ----------------- */

    private String getDiscount(Request request) {
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
        ArrayList<String> discounts = new ArrayList<>(data.getDiscounts().keySet());

        try {
            return "{ \"discounts\": " + new ObjectMapper().writeValueAsString(discounts) + " }";
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
            return Constants.FAILURE;
        }
    }


    /* ----------- */
    /* Main Method */
    /* ----------- */

    public static void main(String[] args) {
        Data.getInstance();
        Data.restoreData();
        ServerController.getInstance().saveServer();
        HTTPServer server = new HTTPServer(ServerController.getInstance(), Constants.PORT);
        server.start();
    }

    public void saveServer() {
        final Runnable save = Data::storeData;
        scheduler.scheduleAtFixedRate(save, 10, 10, TimeUnit.MINUTES);
    }
}
