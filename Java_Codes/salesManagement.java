/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cardealertrydesign; // For java.sql.Date
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dian
 */
public class salesManagement {
    private String saleId;
    private String yearSale;
    private customerManagement Customer;
    private vehicleManagement Vehicle;
    private Connection con;

    public salesManagement(String saleId, String yearSale, customerManagement Customer, vehicleManagement Vehicle) {
        this.saleId = saleId;
        this.yearSale = yearSale;
        this.Customer = Customer;
        this.Vehicle = Vehicle;
        
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
    
     public void processSale() {
         
        String customerID = Customer.getCustomerID();
        String[] customerDetails = Customer.getCustomerDetailsById(customerID);
        String customerName = customerDetails[0];

        String vehicleID = Vehicle.getID();
        String[] vehicleDetails = Vehicle.getVehicleDetailsById(vehicleID);
        String vehicleMake = vehicleDetails[0];
        String vehicleModel = vehicleDetails[1];
        String vehiclePrice = vehicleDetails[2];

        String query = "INSERT INTO tblSales (SaleID, YearSale, CustomerName, VehicleMake, VehicleModel, VehiclePrice) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbcardealership", "root", "");
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, saleId);
            ps.setString(2, yearSale);
            ps.setString(3, customerName);
            ps.setString(4, vehicleMake);
            ps.setString(5, vehicleModel);
            ps.setString(6, vehiclePrice);
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error processing sale: " + e.getMessage());
        }
    }
     
     
   public void fillTableSales(JTable table) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Sale ID");
        model.addColumn("Date purchased");
        model.addColumn("Customer Name");
        model.addColumn("Vehicle Make");
        model.addColumn("Vehicle Model");
        model.addColumn("Vehicle Price");

        try {
            String query = "SELECT * FROM tblSales";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String SaleID = rs.getString("SaleID");
                String YearSale = rs.getString("YearSale");
                String CustomerName = rs.getString("CustomerName");  
                String VehicleMake = rs.getString("VehicleMake");
                String VehicleModel = rs.getString("VehicleModel");
                double VehiclePrice = rs.getDouble("VehiclePrice");

                model.addRow(new Object[]{SaleID, YearSale, CustomerName, VehicleMake, VehicleModel, VehiclePrice});
            }
        } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e);
        }

        table.setModel(model);
     
    }
   
    public void searchSales(JTable table, JTextField txtsearch3) {
        String search = txtsearch3.getText().trim();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Sales Id");
        model.addColumn("Purchase Date");
        model.addColumn("Customer Name");
        model.addColumn("Vehicle Make");
        model.addColumn("Vehicle Model");
        model.addColumn("Vehicle Price");

        String query = "SELECT * FROM tblsales WHERE SaleID LIKE ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            String searchTerm = "%" + search + "%";
            ps.setString(1, searchTerm);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String SaleID = rs.getString("SaleID");
                String YearSale = rs.getString("YearSale");
                String CustomerName = rs.getString("CustomerName");  
                String VehicleMake = rs.getString("VehicleMake");
                String VehicleModel = rs.getString("VehicleModel");
                double VehiclePrice = rs.getDouble("VehiclePrice");

                model.addRow(new Object[]{SaleID, YearSale, CustomerName, VehicleMake, VehicleModel, VehiclePrice});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Walang ganyan, Hoy");
        }

        table.setModel(model);
    }
}
    
