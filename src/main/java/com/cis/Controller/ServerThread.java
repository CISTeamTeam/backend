package com.cis.Controller;

import com.cis.Model.HTTPServerListener;
import com.cis.Model.Request;
import com.cis.Utils.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.*;
import java.util.HashMap;

public class ServerThread extends Thread{

    private final HTTPServerListener listener;
    private final Socket socket;

    public ServerThread(HTTPServerListener listener, Socket socket) {
        this.listener = listener;
        this.socket = socket;
    }

    public void run() {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            //code to read the headers
            StringBuilder headerBuilder = new StringBuilder();
            String line;
            while((line = reader.readLine()).length() != 0){
                headerBuilder.append(line);
            }
            String header = headerBuilder.toString();

            //code to read the post payload data
            StringBuilder payloadBuilder = new StringBuilder();
            while(reader.ready()){
                payloadBuilder.append((char) reader.read());
            }
            String payload = payloadBuilder.toString();

            System.out.println(header + "\n" + payload);

            try {
                Request request = new Request(header, payload);
                String responseBody = listener.handleRequest(request);

                writer.println("HTTP/1.1 200 OK\r\n" +
                                       "ContentType: text/plain\r\n" +
                                       "\r\n" +
                                       responseBody + "\r\n" +
                                       "\r\n");
            }
            catch (JsonProcessingException e){
                HashMap<String, String> errorMap = new HashMap<String, String>();
                errorMap.put("error", Constants.JSONError);
                String error = new ObjectMapper().writeValueAsString(errorMap);

                writer.println("HTTP/1.1 400 Bad Request\r\n" +
                                       "ContentType: text/plain\r\n" +
                                       "\r\n" +
                                       error + "\r\n" +
                                       "\r\n");
            }

            socket.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
