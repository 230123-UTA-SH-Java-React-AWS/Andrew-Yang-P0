package com.revature.controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

/*
    Webpage to display all submitted tickets for the user
 */

public class YourTickets implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String yourTicketPage = "";
        String httpRequestMethod = exchange.getRequestMethod();
        switch (httpRequestMethod) {
            case "GET":
                yourTicketPage = "This is your submitted tickets page.";
                exchange.sendResponseHeaders(200, yourTicketPage.getBytes().length);
                break;
            default:
                yourTicketPage = "Wrong method of retrieving data";
                exchange.sendResponseHeaders(404, yourTicketPage.getBytes().length);
                break;
        }

        OutputStream os = exchange.getResponseBody();
        os.write(yourTicketPage.getBytes());
        os.close();
    }
}
