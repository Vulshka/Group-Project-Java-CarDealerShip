/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cardealertrydesign;

import java.sql.*;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Dian
 */
public class DBmanager {
        private Connection con;
        
    public DBmanager() {
        try {
            String url = "jdbc:mysql://localhost:3306/dbcardealership"; 
            String user = "root"; 
            String password = ""; 
            con = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, 
                "Failed to connect to database: " + e.getMessage(), 
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public Connection getConnection() {
        return con;
    }

}
