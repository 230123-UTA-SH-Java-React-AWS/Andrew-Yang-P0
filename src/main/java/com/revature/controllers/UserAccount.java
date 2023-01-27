package com.revature.controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

/*
    Webpage to display the user's account into
 */

public class UserAccount implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String userAccountPage = "";
        String httpRequestMethod = exchange.getRequestMethod();
        switch (httpRequestMethod) {
            case "GET":
                userAccountPage = "This is the user account page.";
                exchange.sendResponseHeaders(200, userAccountPage.getBytes().length);
                break;
            default:
                userAccountPage = "Wrong method of retrieving data";
                exchange.sendResponseHeaders(404, userAccountPage.getBytes().length);
                break;
        }

        OutputStream os = exchange.getResponseBody();
        os.write(userAccountPage.getBytes());
        os.close();
    }
}
