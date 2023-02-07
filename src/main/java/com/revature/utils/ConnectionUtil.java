package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Connects to our database
public class ConnectionUtil {

    // Only one connection to the database
    private static Connection con;

    // Disables any object creation of this class.
    private ConnectionUtil () {
        con = null;
    }

    // Gives us a new or an existing connection to the database
    public static Connection getConnection() {
        try {
            if (con != null && !con.isClosed()) {
                return con;
            }
        }
        catch  (SQLException e){
            e.printStackTrace();
        }

        String url, user, pass;
        url = System.getenv("Andrew-Yang-P0-url");
        user = System.getenv("Andrew-Yang-P0-user");
        pass = System.getenv("Andrew-Yang-P0-password");

        try {
            con = DriverManager.getConnection(url, user, pass);
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Wrong pass, url or user combo");
        }

        return con;
    }
}
