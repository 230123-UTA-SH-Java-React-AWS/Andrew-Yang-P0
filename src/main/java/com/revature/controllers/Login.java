package com.revature.controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

/*
    Webpage to display the login
 */

public class Login implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String loginPage = "";
        String httpRequestMethod = exchange.getRequestMethod();
        switch (httpRequestMethod) {
            case "GET":
                loginPage = "This is the login page.";
                exchange.sendResponseHeaders(200, loginPage.getBytes().length);
                break;
            default:
                loginPage = "Wrong method of retrieving data";
                exchange.sendResponseHeaders(404, loginPage.getBytes().length);
                break;
        }

        OutputStream os = exchange.getResponseBody();
        os.write(loginPage.getBytes());
        os.close();
    }
}
