package com.revature.controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

/*
    Webpage to display the home page of the website
 */

public class Homepage implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String homepage = "";
        String httpRequestMethod = exchange.getRequestMethod();
        switch (httpRequestMethod) {
            case "GET":
                homepage = "This is the homepage.";
                exchange.sendResponseHeaders(200, homepage.getBytes().length);
                break;
            default:
                homepage = "Wrong method of retrieving data";
                exchange.sendResponseHeaders(404, homepage.getBytes().length);
                break;
        }

        OutputStream os = exchange.getResponseBody();
        os.write(homepage.getBytes());
        os.close();
    }
}
