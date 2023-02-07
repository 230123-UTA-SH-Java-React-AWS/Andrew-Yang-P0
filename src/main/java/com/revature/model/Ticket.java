package com.revature.model;

public class Ticket {
    private int ticketID;
    private float amount;
    private String description;
    private String ticketStatusString = "PENDING";
    private int personID;

    public void setTicketID (int ticketID) { this.ticketID = ticketID; }
    public void setAmount(float amount) { this.amount = amount; }
    public void setDescription (String description) { this.description = description; }
    public void setTicketStatusString(String ticketStatusString) { this.ticketStatusString = ticketStatusString; }
    public void setPersonID (int personID) { this.personID = personID; }

    public int getTicketID() { return this.ticketID; }
    public float getAmount() { return this.amount; }
    public String getDescription() { return this.description; }
    public String getTicketStatusString() { return this.ticketStatusString; }
    public int getPersonID() { return this.personID; }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ticket ID: " + this.ticketID + "\n");
        sb.append("Amount : $" + this.amount + "\n");
        sb.append("Description: " + this.description + "\n");
        sb.append("Status: " + this.ticketStatusString + "\n");
        sb.append("Person ID: " + this.personID + "\n");

        return sb.toString();
    }
}
