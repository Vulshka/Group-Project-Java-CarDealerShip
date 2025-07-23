/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cardealertrydesign;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Jericho Mico
 */
public class Delete {
    private Connection con;
    private PreparedStatement ps;

    public Delete() {
                     try {
            String uname = "root";
            String pass = "";
            String url = "jdbc:mysql://localhost:3306/dbcardealership";
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.con = DriverManager.getConnection(url, uname, pass);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
void deleteCustomer(JTextField customerID) {
    String deleteID = customerID.getText();
    
    // Check if the ID is valid
    if (deleteID.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please enter a valid Customer ID", "Warning", JOptionPane.WARNING_MESSAGE);
        return;  // Exit if the ID is empty
    }

    try {
        // Prepare the SQL statement with parameterized query
        ps = con.prepareStatement("DELETE FROM tblcustomer WHERE CustomerId = ?");
        ps.setString(1, deleteID);
        
        // Ask for confirmation
        int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this customer?", "Confirmation",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.YES_OPTION) {
            int rowsAffected = ps.executeUpdate();  // Use executeUpdate for delete operations
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Record deleted successfully", "Information", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No record found with the provided Customer ID", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    
void deleteVehicle(JTextField VF5) {
    String deleteID = VF5.getText(); 

    if (deleteID.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please enter a valid Vehicle ID", "Warning", JOptionPane.WARNING_MESSAGE);
        return; 
    }

    try {
        ps = con.prepareStatement("DELETE FROM tblvehicle WHERE VehicleId = ?");
        ps.setString(1, deleteID);  


        int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this vehicle?", 
                                                    "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.YES_OPTION) {
            int rowsAffected = ps.executeUpdate(); 


            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Vehicle deleted successfully", "Information", JOptionPane.INFORMATION_MESSAGE);
                
            } else {
                JOptionPane.showMessageDialog(null, "No vehicle found with the provided Vehicle ID", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }}
    
    void deleteSales(JTextField salesID) {
    String deleteID = salesID.getText(); 

    if (deleteID.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please enter a valid Vehicle ID", "Warning", JOptionPane.WARNING_MESSAGE);
        return; 
    }

    try {
        ps = con.prepareStatement("DELETE FROM tblsales WHERE SaleID = ?");
        ps.setString(1, deleteID);  


        int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this vehicle?", 
                                                    "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (result == JOptionPane.YES_OPTION) {
            int rowsAffected = ps.executeUpdate(); 


            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Vehicle deleted successfully", "Information", JOptionPane.INFORMATION_MESSAGE);
                
            } else {
                JOptionPane.showMessageDialog(null, "No vehicle found with the provided Vehicle ID", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    
}}
    
    
    
