/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cardealertrydesign;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
/**
 *
 * @author Dian
 */
public class vehicleManagement {
    
    private String Make;
    private String Model;
    private String ID;
    private double price;
    private String year;
    private Connection con;

    public vehicleManagement(String Make, String Model, String ID, double price, String year) {
        this.Make = Make;
        this.Model = Model;
        this.ID = ID;
        this.price = price;
        this.year = year;
        
                
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
    
    public void addVehicle(){
        String query = "INSERT INTO tblVehicle (VehicleMake, VehicleModel, Vehicleprice, VehicleYear, VehicleId) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, Make);
                ps.setString(2, Model);
                ps.setDouble(3, price);
                ps.setString(4, year);
                ps.setString(5, ID);
                ps.execute();
                JOptionPane.showMessageDialog(null, "Car successfuly added!!!!");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
    }
    
    public void fillTableVehicle(JTable table) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Vehicle Make");
        model.addColumn("Vehicle Model");
        model.addColumn("Vehicle Year Model");
        model.addColumn("Vehicle Price");
        model.addColumn("Vehicle ID");

        try {
            String query = "SELECT * FROM tblVehicle";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String vehicleMake = rs.getString("VehicleMake");
                String vehicleModel = rs.getString("VehicleModel");
                String vehicleYear = rs.getString("VehicleYear");  
                double vehiclePrice = rs.getDouble("VehiclePrice");
                String vehicleId = rs.getString("VehicleId");

                model.addRow(new Object[]{vehicleMake, vehicleModel, vehicleYear, vehiclePrice, vehicleId});
            }
        } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e);
        }

        table.setModel(model);
}

    
    public void searchVehicle(JTable table, JTextField VsearchF){
        String Vsearch = VsearchF.getText().trim();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Vehicle Make");
        model.addColumn("Vehicle Model");
        model.addColumn("Vehicle Year");
        model.addColumn("Vehicle Price");
        model.addColumn("Vehicle ID");

        String query = "SELECT * FROM tblVehicle WHERE VehicleId LIKE ?";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, "%" + Vsearch + "%"); 

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String vehicleMake = rs.getString("VehicleMake");
                String vehicleModel = rs.getString("VehicleModel");
                String vehicleYear = rs.getString("VehicleYear"); 
                double vehiclePrice = rs.getDouble("VehiclePrice");
                String vehicleID = rs.getString("VehicleId");

                model.addRow(new Object[]{vehicleMake, vehicleModel, vehicleYear, vehiclePrice, vehicleID});
            }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error while searching: " + e.getMessage(), "Search Error", JOptionPane.ERROR_MESSAGE);
            }
            table.setModel(model);

    }
    
    public String[] getVehicleDetailsById(String vehicleID) {
        String[] vehicleDetails = new String[3]; 
        String query = "SELECT VehicleMake, VehicleModel, VehiclePrice FROM tblVehicle WHERE VehicleId = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, vehicleID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                vehicleDetails[0] = rs.getString("VehicleMake");
                vehicleDetails[1] = rs.getString("VehicleModel");
                vehicleDetails[2] = rs.getString("VehiclePrice");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error fetching vehicle details: " + e.getMessage());
        }
        return vehicleDetails;
    }
    
   
    public String getMake() {
        return Make;
    }

    public void setMake(String Make) {
        this.Make = Make;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String Model) {
        this.Model = Model;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
    
    
}
