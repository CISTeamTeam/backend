package com.cis.Model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;

public class Request {

    private final String path;
    private HashMap<String, String> params;

    public Request(String header, String payload) throws JsonProcessingException {
        String[] headerLines = header.split("\r\n");
        this.path = headerLines[0].split(" ")[1];

        this.params = new ObjectMapper().readValue(payload, HashMap.class);
    }

    public Request(String path, HashMap<String, String> params) {
        this.path = path;
        this.params = params;
    }

    public String getPath() {
        return path;
    }

    public String getParam(String key) {
        return params.get(key);
    }
}
