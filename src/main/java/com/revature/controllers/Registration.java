package com.revature.controllers;

import com.revature.repository.Repository;
import com.revature.service.Service;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

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
            case "POST":
                postRequest(exchange);
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

    private void postRequest(HttpExchange exchange) throws IOException{
        InputStream is = exchange.getRequestBody();

        StringBuilder sb = new StringBuilder();
        // try_resource block will automatically close the resource within the parenthesis
        // Try to convert the binary data into letters
        try (Reader r = new BufferedReader(new InputStreamReader(is, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c = 0;

            // Keep reading each letter until there is no more left
            while ((c = r.read()) != -1) {
                sb.append((char)c);
            }
        }

        Service service = new Service(new Repository());
        LinkedList<Object> serviceStatement = service.createAccount(sb.toString());

        if (!((boolean)serviceStatement.get(0))) {
            sb.delete(0, sb.toString().getBytes().length);
            sb.append(serviceStatement.get(1));
        }
        // Send back to the client the data
        exchange.sendResponseHeaders(200, sb.toString().getBytes().length);

        // Postman will print this
        OutputStream os = exchange.getResponseBody();
        os.write(sb.toString().getBytes());
        os.close();
    }
}
