package com.cis.Controller;

import com.cis.Model.Data;
import com.cis.Model.HTTPServerListener;
import com.cis.Model.Request;
import com.cis.Model.User;
import com.cis.Utils.Constants;
import com.cis.Utils.HTTPServer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
