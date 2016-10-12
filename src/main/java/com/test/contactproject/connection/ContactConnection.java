package com.test.contactproject.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContactConnection {

    public static Connection getConnection() {        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbUrl = "jdbc:mysql://localhost:3306/Contacts";
            String user = "root";
            String pwd = "pass2adm";
            return DriverManager.getConnection(dbUrl, user, pwd);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ContactConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
}
