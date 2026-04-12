package com.example;

import io.muserver.MuServer;
import io.muserver.MuServerBuilder;
import org.example.controller.BookingHandler;

public class Main {

    public static void main(String[] args) {

        MuServerBuilder serverBuilder = MuServerBuilder.httpServer()
                .withHttpPort(8080);

        BookingHandler handler = new BookingHandler();
        handler.register(serverBuilder);

        MuServer server = serverBuilder.start();

        System.out.println("Server started at " + server.uri());
    }
}