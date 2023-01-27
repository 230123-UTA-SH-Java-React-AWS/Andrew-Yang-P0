package com.revature.controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

/*
    Webpage to display the registration page
 */

public class Registration implements HttpHandler{
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String registerPage = "";
        String httpRequestMethod = exchange.getRequestMethod();
        switch (httpRequestMethod) {
            case "GET":
                registerPage = "This is the registration page.";
                exchange.sendResponseHeaders(200, registerPage.getBytes().length);
                break;
            default:
                registerPage = "Wrong method of retrieving data";
                exchange.sendResponseHeaders(404, registerPage.getBytes().length);
                break;
        }

        OutputStream os = exchange.getResponseBody();
        os.write(registerPage.getBytes());
        os.close();
    }
}
