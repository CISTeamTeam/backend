package com.cis.Utils;

import com.cis.Controller.ServerThread;
import com.cis.Model.HTTPServerListener;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPServer {

    private final int port;
    private final HTTPServerListener listener;
    private final ServerSocket serverSocket;

    public HTTPServer(HTTPServerListener listener, int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Starting server at port " + port);
            this.port = port;
            this.listener = listener;
        }
        catch (IOException e) {
            throw new IllegalArgumentException("Port out of range: " + port);
        }
    }

    public void start() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                new ServerThread(listener, socket).start();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int getPort() {
        return port;
    }
}