package com.revature.controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

/*
    Webpage to display the ticket submission fields
 */

public class UserTicket implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String userTicketPage = "";
        String httpRequestMethod = exchange.getRequestMethod();
        switch (httpRequestMethod) {
            case "GET":
                userTicketPage = "This is the user ticket page.";
                exchange.sendResponseHeaders(200, userTicketPage.getBytes().length);
                break;
            default:
                userTicketPage = "Wrong method of retrieving data";
                exchange.sendResponseHeaders(404, userTicketPage.getBytes().length);
                break;
        }

        OutputStream os = exchange.getResponseBody();
        os.write(userTicketPage.getBytes());
        os.close();
    }
}
