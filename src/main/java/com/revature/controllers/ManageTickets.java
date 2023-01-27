package com.revature.controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

/*
    Webpage to display the Manger's ticket list
 */

public class ManageTickets implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String manageTicketsPage = "";
        String httpRequestMethod = exchange.getRequestMethod();
        switch (httpRequestMethod) {
            case "GET":
                manageTicketsPage = "This is the manage tickets page.";
                exchange.sendResponseHeaders(200, manageTicketsPage.getBytes().length);
                break;
            default:
                manageTicketsPage = "Wrong method of retrieving data";
                exchange.sendResponseHeaders(404, manageTicketsPage.getBytes().length);
                break;
        }

        OutputStream os = exchange.getResponseBody();
        os.write(manageTicketsPage.getBytes());
        os.close();
    }
}
