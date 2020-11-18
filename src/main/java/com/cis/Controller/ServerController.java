package com.cis.Controller;

import com.cis.Model.Data;
import com.cis.Model.HTTPServerListener;
import com.cis.Model.Request;
import com.cis.Model.User;
import com.cis.Utils.Constants;
import com.cis.Utils.HTTPServer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

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

            data.addUser(user);
            return Constants.SUCCESS;
        }catch(Exception e){
            e.printStackTrace();
        }
        return Constants.FAILURE;
    }

    private String getUserPoints(Request request) {
        String id = (String) request.getParam(Constants.ID_PARAM);
        int points = data.getUsers().get(id).getPoints();
        return "{ \"userID\": " + id + ", \"points\": " + points + " }";
    }


    public static void main(String[] args) {
        Data.getInstance();
        HTTPServer server = new HTTPServer(ServerController.server(), Constants.PORT);
        server.start();
    }
}
