package ru.netology;

import java.io.IOException;

public class Main {

    public static int numberOfThreads = 64;
    protected static final int PORT = 9999;

    public static void main(String[] args) throws InterruptedException {
    Server server = new Server(PORT, numberOfThreads);
    server.addHandler("GET", "/messages", ((request, responseStream) -> {
            try {
            server.responseWithoutContent(responseStream, "404", "Not found!");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));
    server.addHandler("POST", "/messages", (request, responseStream) ->
            server.responseWithoutContent(responseStream, "503", "Service unavailable"));
    server.addHandler("GET", "/", (request, responseStream) ->
            server.defaultHandler(responseStream, "spring.svg"));
    server.start();

    }
}
