package com.revature.service;

import com.revature.model.*;
import com.revature.repository.*;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

// Responsible for hold behavior driven classes
public class Service {
    private final Repository repo;
    private final ObjectMapper mapper = new ObjectMapper();

    public Service (Repository r) {
        this.repo = r;
    }

    // Format: First element - True or False
    //         Second element - empty if True, error message if false
    private LinkedList<Object> returnStatement;

    // Sessions
    public LinkedList<Object> login(String acctJson){
        returnStatement = new LinkedList<>();

        try {
            // Ideally this would be handled in the repo file to get data from the database
            Person p = mapper.readValue(acctJson, Employee.class);
            Person p2 = repo.getPerson(p);

            if (p2 == null) {
                // The account does not exist, but we do not want the client to
                // know that there is no account with the credentials they used.
                returnStatement.add(false);
                returnStatement.add("Incorrect credentials");
                return returnStatement;
            }

            boolean isEmailSame = p2.getEmail().equals(p.getEmail());
            boolean isPasswordSame = p2.getPassword().equals(p.getPassword());

            if (!(isEmailSame && isPasswordSame)) {
                // Actual incorrect credentials
                returnStatement.add(false);
                returnStatement.add("Incorrect credentials");
            }
            else {
                returnStatement.add(true);
                returnStatement.add("Login Successful!");
            }

        } catch (IOException e) {
            e.printStackTrace();
            returnStatement.add(false);
            returnStatement.add("Service could not save the data.");
        }


        return returnStatement;
    }
    public boolean logout(){ return false; }

    // Accounts
    public LinkedList<Object> createAccount(String acctJson){
        returnStatement = new LinkedList<>();

        try {
            Person p = mapper.readValue(acctJson, Employee.class);
            if (!(p.getEmail().contains("@") && p.getEmail().contains("."))) {
                returnStatement.add(false);
                returnStatement.add("Not a valid email address");
                return returnStatement;
            }

            if (p.getPassword().length() < 8) {
                returnStatement.add(false);
                returnStatement.add("Password needs to be 8 or more characters");
                return returnStatement;
            }

            int saveErrorCode = repo.save(p);
            switch (saveErrorCode) {
                case 1:
                    returnStatement.add(false);
                    returnStatement.add("Account already registered with that email.");
                    break;
                case 2:
                    returnStatement.add(false);
                    returnStatement.add("Could not save your data into the server");
                    break;
                default:
                    returnStatement.add(true);
                    returnStatement.add("");
            }

            return returnStatement;

        } catch (IOException e) {
            e.printStackTrace();
        }

        returnStatement.add(false);
        returnStatement.add("Service could not save the data.");
        return returnStatement;
    }

    public boolean changeName(){ return false; }
    public boolean changeEmail(){ return false; }
    public boolean changePassword(){ return false; }

    // Employee Interaction
    public LinkedList<Object> createTicket(String acctJson){
        returnStatement = new LinkedList<>();

        try {
            Ticket t = mapper.readValue(acctJson, Ticket.class);

            if (repo.submitTicket(t)) {
                returnStatement.add(true);
                returnStatement.add("");
            }
            else {
                returnStatement.add(false);
                returnStatement.add("Could not submit your ticket info to the database");
            }

            return returnStatement;

        } catch (IOException e) {
            e.printStackTrace();
        }

        returnStatement.add(false);
        returnStatement.add("Service couldn't submit your ticket data.");
        return returnStatement;
    }
    public LinkedList<Object> viewTickets(String acctJson){
        returnStatement = new LinkedList<>();
        Person p = null;
        try {
            p = mapper.readValue(acctJson, Employee.class);
            List<Ticket> tickets = repo.getTickets(p);

            if (tickets == null) {
                returnStatement.add(false);
                returnStatement.add("You have not submitted any tickets yet.");
            }
            else {
                returnStatement.add(true);
                returnStatement.add("Here are your selected tickets!");
                returnStatement.add(tickets);
            }

        } catch (IOException e) {
            e.printStackTrace();
            returnStatement.add(false);
            returnStatement.add("Could not retrieve your tickets from the database.");
        }
        return returnStatement;
    }


}
