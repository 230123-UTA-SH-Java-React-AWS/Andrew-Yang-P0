package com.revature.model;

public class TicketModifer extends PersonTicketRelator{
    private String ticketStatusString;

    public void setTicketStatusString(String ticketStatusString) { this.ticketStatusString = ticketStatusString; }

    public String getTicketStatusString() { return ticketStatusString; }
}
