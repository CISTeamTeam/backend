package com.cis.Model;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface HTTPServerListener {
    String handleRequest(Request request) throws JsonProcessingException;
}
