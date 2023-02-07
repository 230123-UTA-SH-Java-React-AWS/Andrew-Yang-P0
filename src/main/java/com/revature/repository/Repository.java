package com.revature.repository;

import com.revature.model.*;
import com.revature.utils.ConnectionUtil;
import org.codehaus.jackson.map.ObjectMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

// Deals with handling the data back and forth
public class Repository {
    public boolean update(){
        return false;
    }
    public int save(Person person) {
        // Check if the person exists first in the database
        if (getPerson(person) != null) {
            return 1;
        }

        // Build the SQL string to insert the person into the database
        String sql = "INSERT INTO person (personName, personEmail, personPassword) " +
                     " values (?, ?, ?)";

        try (Connection con = ConnectionUtil.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, person.getName());
            ps.setString(2, person.getEmail());
            ps.setString(3, person.getPassword());

            ps.execute();

        }
        // Throws exception for things SQL related
        catch (SQLException exception) {
            exception.printStackTrace();
            return 2;
        }

        // Success from inserting the employee into the database
        return 0;
    }
    public Person getPerson(Person p) {
        Person retP = null;
        PreparedStatement ps;
        ResultSet r;

        // Return the employee as a Person sub-class
        try {
            ps = ConnectionUtil.getConnection().prepareStatement("SELECT * FROM person WHERE personEmail = ?");
            ps.setString(1, p.getEmail());
            r = ps.executeQuery();

            // If the ResultSet does not contain any data, then the person was not
            // in the table to begin with.
            if (!r.isBeforeFirst()) {
                return retP;
            }

            retP = new Employee();
            retP.setPersonID(r.getInt("personID"));
            retP.setEmail(r.getString("personEmail"));
            retP.setPassword(r.getString("personPassword"));
            retP.setName(r.getString("personName"));
            retP.setEmployeeStatusString(r.getString("personStatus"));

            // Can't evaluate an enum value when pulling from the database, weird...
            // Guess I can't evaluate the enum when I'm in a try-catch block?
            //EmployeeStatus employeeStatus = EmployeeStatus.valueOf(r.getString("personStatus"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return retP;
    }
    public Person getPersonFromID(int id) {
        Person retP = null;
        PreparedStatement ps;
        ResultSet r;

        // Return the employee as a Person sub-class
        try {
            ps = ConnectionUtil.getConnection().prepareStatement("SELECT * FROM person WHERE personID = ?");
            ps.setInt(1, id);
            r = ps.executeQuery();

            // If the ResultSet does not contain any data, then the person was not
            // in the table to begin with.
            if (!r.next()) {
                return retP;
            }

            retP = new Employee();
            retP.setPersonID(r.getInt("personID"));
            retP.setEmail(r.getString("personEmail"));
            retP.setPassword(r.getString("personPassword"));
            retP.setName(r.getString("personName"));
            retP.setEmployeeStatusString(r.getString("personStatus"));

            // Can't evaluate an enum value when pulling from the database, weird...
            // Guess I can't evaluate the enum when I'm in a try-catch block?
            //EmployeeStatus employeeStatus = EmployeeStatus.valueOf(r.getString("personStatus"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return retP;
    }
    public boolean submitTicket(Ticket t) {
        // Build the SQL string to insert the person into the database
        String sql = "INSERT INTO Ticket (ticketAmount, ticketDesc, personID) " +
                " values (?, ?, ?)";

        try (Connection con = ConnectionUtil.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setFloat(1, t.getAmount());
            ps.setString(2, t.getDescription());
            ps.setInt(3, t.getPersonID());

            ps.execute();
        }
        // Throws exception for things SQL related
        catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }

        // Success from inserting the employee into the database
        return true;

    }
    public List<Ticket> getTickets(Person p) {
        List<Ticket> tickets = null;
        StringBuilder sql = new StringBuilder();
        ObjectMapper mapper = new ObjectMapper();
        PreparedStatement ps;
        ResultSet rs;

        p = getPersonFromID(p.getPersonID());
        if (p == null) {
            return null;
        }

        System.out.println(p);
        switch (p.getEmployeeStatusString()) {
            case "EMPLOYEE":
                try {
                    ps = ConnectionUtil.getConnection().prepareStatement("SELECT * FROM Ticket WHERE (personID = ?)");
                    ps.setInt(1, p.getPersonID());
                    rs = ps.executeQuery();
                    if (!rs.isBeforeFirst()) {
                        return null;
                    }
                    tickets = getAllTickets(rs);

                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "MANAGER":
                try {
                    ps = ConnectionUtil.getConnection().prepareStatement("SELECT * FROM Ticket WHERE ticketStatus = \'PENDING\'");
                    rs = ps.executeQuery();
                    if (!rs.isBeforeFirst()) {
                        return null;
                    }
                    tickets = getAllTickets(rs);
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                return null;
        }
        return tickets;
    }

    // Generates a list of tickets when the user wants to retrieve them.
    private List<Ticket> getAllTickets (ResultSet rs) throws SQLException {
        List<Ticket> tickets = new LinkedList<>();
        while (rs.next()) {
            Ticket t = new Ticket();
            t.setTicketID(rs.getInt("ticketID"));
            t.setAmount(rs.getInt("ticketAmount"));
            t.setDescription(rs.getString("ticketDesc"));
            t.setPersonID(rs.getInt("personID"));
            t.setTicketStatusString(rs.getString("ticketStatus"));
            tickets.add(t);
        }
        return tickets;
    }
}
