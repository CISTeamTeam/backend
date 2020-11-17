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
        if (request.getPath().equals(Constants.GET_USER)) {
            String id = (String) request.getParam(Constants.ID_PARAM);
            User user = data.getUsers().get(id);
            try {
                return new ObjectMapper().writeValueAsString(user);
            }
            catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Data.getInstance();
        HTTPServer server = new HTTPServer(ServerController.server(), Constants.PORT);
        server.start();
    }
}
