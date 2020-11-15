package com.cis.Controller;

import com.cis.Model.HTTPServerListener;
import com.cis.Model.Request;
import com.cis.Utils.Constants;
import com.cis.Utils.HTTPServer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;

public class ServerController implements HTTPServerListener {

    public static ServerController instance = null;

    private ServerController() {}

    public static ServerController server() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    @Override
    public String handleRequest(Request request) throws JsonProcessingException {
        HashMap<String, String> output = new HashMap<String, String>();

        if (request.getPath().equals(Constants.PING)) {
            output.put("ping", "ping");
        }

        return new ObjectMapper().writeValueAsString(output);
    }

    public static void main(String[] args) {
        HTTPServer server = new HTTPServer(ServerController.server(), Constants.PORT);
        server.start();
    }
}
