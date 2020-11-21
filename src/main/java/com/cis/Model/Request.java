package com.cis.Model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;

public class Request {

    private final String path;
    private final String paramsJSON;
    private HashMap<String, Object> params;

    public Request(String header, String payload) throws JsonProcessingException {
        String[] headerLines = header.split("\r\n");
        this.path = headerLines[0].split(" ")[1];
        this.paramsJSON = payload;
        this.params = new ObjectMapper().readValue(payload, HashMap.class);
    }

    public Request(String path, HashMap<String, Object> params) throws JsonProcessingException {
        this.path = path;
        this.params = params;
        this.paramsJSON = new ObjectMapper().writeValueAsString(params);
    }

    public String getPath() {
        return path;
    }

    public Object getParam(String key) {
        return params.get(key);
    }
}
