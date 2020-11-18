package com.cis.Controller;

import com.cis.Model.*;
import com.cis.Utils.Constants;
import com.cis.Utils.HTTPServer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.UUID;

public class ServerController implements HTTPServerListener {

    private static ServerController instance = null;

    private Data data;

    private ServerController() {
        data = Data.getInstance();
    }

    public static ServerController server() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    @Override
    public String handleRequest(Request request) {
        switch(request.getPath()){
            case Constants.AUTH: return authenticate(request);
            case Constants.GET_USER: return getUser(request);
            case Constants.GET_USER_POINTS: return getUserPoints(request);
            case Constants.CREATE_USER: return createUser(request);
            case Constants.UPDATE_USER: return updateUser(request);
            case Constants.SPEND_POINTS: return spendPoints(request);
            case Constants.GET_POST_POINTS: return getPostPoints(request);
            case Constants.GET_COMMENT: return getComment(request);
            case Constants.POST_COMMENT: return postComment(request);
        }
        return null;
    }

    private String authenticate(Request request){
        String id = (String) request.getParam(Constants.ID_PARAM);
        if(data.getUsers().containsKey(id)){
            return Constants.SUCCESS;
        }
        return Constants.FAILURE;
    }

    private String getPost(Request request) {
        String id = (String) request.getParam(Constants.ID_PARAM);
        Post post = data.getPosts().get(id);
        try {
            return new ObjectMapper().writeValueAsString(post);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getPosts(Request request) {
        String userID = (String) request.getParam(Constants.USER_ID_PARAM);
        String hash = (String) request.getParam(Constants.PAGING_HASH);
        User user = data.getUsers().get(userID);
        ArrayList<String> topPosts = new ArrayList<>();
        TreeSet<String> posts;
        if (hash == null) {
            hash = UUID.randomUUID().toString();
            posts = (TreeSet<String>) ((TreeSet<String>) user.getUnreadPosts()).clone();
        }
        else {
            posts = (TreeSet<String>) user.getTrackPaging().get(hash);
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

    private String getUser(Request request) {
        String id = (String) request.getParam(Constants.ID_PARAM);
        User user = data.getUsers().get(id);
        try {
            return new ObjectMapper().writeValueAsString(user);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String createUser(Request request){
        try {
            String id = (String) request.getParam(Constants.ID_PARAM);
            String bio = (String) request.getParam(Constants.BIO_PARAM);
            String profilePictureURL = (String) request.getParam(Constants.PFP_URL_PARAM);
            String username = (String) request.getParam(Constants.USERNAME_PARAM);
            String name = (String) request.getParam(Constants.NAME_PARAM);

            User user = new User(id, bio, profilePictureURL, username, name, 0);

            if(data.getUsers().containsKey(id)){
                return Constants.FAILURE;
            }

            data.addUser(user);
            return Constants.SUCCESS;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return Constants.FAILURE;
    }

    private String updateUser(Request request){
        try {
            String id = (String) request.getParam(Constants.ID_PARAM);
            String bio = (String) request.getParam(Constants.BIO_PARAM);
            String pfpurl = (String) request.getParam(Constants.PFP_URL_PARAM);
            String username = (String) request.getParam(Constants.USERNAME_PARAM);
            String name = (String) request.getParam(Constants.NAME_PARAM);

            User user = data.getUsers().get(id);

            user.setBio(bio);
            user.setProfilePictureURL(pfpurl);
            user.setUsername(username);
            user.setName(name);

            return Constants.SUCCESS;

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return Constants.FAILURE;
    }

    private String getUserPoints(Request request) {
        String id = (String) request.getParam(Constants.ID_PARAM);
        int points = data.getUsers().get(id).getPoints();
        return "{ \"userID\": " + id + ", \"points\": " + points + " }";
    }

    private String spendPoints(Request request) {
        try {
            String id = (String) request.getParam(Constants.ID_PARAM);
            int pointsSpent = (int) request.getParam(Constants.POINTS_PARAM);
            User user = data.getUsers().get(id);
            user.subtractPoints(pointsSpent);
            return Constants.SUCCESS;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return Constants.FAILURE;
    }

    private String getPostPoints(Request request) {
        try {
            String id = (String) request.getParam(Constants.ID_PARAM);
            int points = data.getPosts().get(id).getRating();
            return "{\"points\":"+points+"}";
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return Constants.FAILURE;
    }

    private String getComment(Request request){
        try {
            String id = (String) request.getParam(Constants.ID_PARAM);
            Comment comment = data.getComments().get(id);
            return new ObjectMapper().writeValueAsString(comment);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return Constants.FAILURE;
    }

    private String postComment(Request request){
        try {
            String id = (String) request.getParam(Constants.ID_PARAM);
            String authorId = (String) request.getParam("authorID");
            String postId = (String) request.getParam("postID");
            String text = (String) request.getParam("text");

            Comment comment = new Comment(id, authorId, postId, text);

            data.addComment(comment);

            return Constants.SUCCESS;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return Constants.FAILURE;
    }

    public static void main(String[] args) {
        Data.getInstance();
        HTTPServer server = new HTTPServer(ServerController.server(), Constants.PORT);
        server.start();
    }
}
