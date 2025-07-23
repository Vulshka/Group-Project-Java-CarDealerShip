/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cardealertrydesign;
import javax.swing.JTextField;
import java.sql.*;
import javax.swing.JOptionPane;

public class Update{
    private Connection con;
    private PreparedStatement ps;
    
    public Update(){
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
    
    
    public void updateCustomer(JTextField CF7, JTextField CF8, JTextField CF9, JTextField CF10, JTextField CF6){
       
        String Name = CF7.getText();
        String Email = CF8.getText();
        String PhoneNO = CF9.getText();
        String Address = CF10.getText();
        String UserID  = CF6.getText(); 

        try {
            String query = "UPDATE tblcustomer SET CustomerName = ?, CustomerEmail = ?, CustomerPhone = ?, CustomerAddress =? WHERE CustomerId=?";
            ps = con.prepareStatement(query);

   
            ps.setString(1, Name);
            ps.setString(2, Email);
            ps.setString(3, PhoneNO);
            ps.setString(4, Address);
            ps.setString(5, UserID); 
            
            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Customer information updated successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "No customer found with the specified UserID.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while updating the customer information.", "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }
    
    public void updateVehicle(JTextField VF1, JTextField VF2, JTextField VF3, JTextField VF4, JTextField VF5){
       
        String make = VF1.getText();
        String model = VF2.getText();
        String price = VF3.getText();
        String yearmodel = VF4.getText();
        String VID  = VF5.getText(); 

        try {
            String query = "UPDATE tblvehicle SET VehicleMake = ?, VehicleModel = ?, VehiclePrice = ?, VehicleYear =? WHERE VehicleId=?";
            ps = con.prepareStatement(query);


            ps.setString(1, make);
            ps.setString(2, model);
            ps.setString(3, price);
            ps.setString(4, yearmodel);
            ps.setString(5, VID); 

            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Customer information updated successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "No customer found with the specified Vehicle ID.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while updating the  vehicle information.", "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }
        

}
