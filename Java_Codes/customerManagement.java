/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cardealertrydesign;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Dian
 */
public class customerManagement{
    
        private String CustomerName;
        private String CustomerEmail;
        private String CustomerPhoneNo;
        private String CustomerAddress;
        private String CustomerID;
        private Connection con;

    public customerManagement(String CustomerName, String CustomerEmail, String CustomerPhoneNo, String CustomerAddress, String CustomerID) {
        this.CustomerName = CustomerName;
        this.CustomerEmail = CustomerEmail;
        this.CustomerPhoneNo = CustomerPhoneNo;
        this.CustomerAddress = CustomerAddress;
        this.CustomerID = CustomerID;
        
        
        
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
    
    public void addCustomer() {
    String query = "INSERT INTO tblcustomer (CustomerName, CustomerEmail, CustomerPhone, CustomerAddress, CustomerID) VALUES (?, ?, ?, ?, ?)";
    try (PreparedStatement ps = con.prepareStatement(query)) {
        ps.setString(1, CustomerName);
        ps.setString(2, CustomerEmail);
        ps.setString(3, CustomerPhoneNo);
        ps.setString(4, CustomerAddress);
        ps.setString(5, CustomerID);
        ps.execute();
        JOptionPane.showMessageDialog(null, "Na add mo na pare");
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e);
    }
}
    
    public void fillTableCustomer(JTable table) {
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Customer Name");
    model.addColumn("Customer Email");
    model.addColumn("Customer Phone");
    model.addColumn("Customer Address");
    model.addColumn("Customer ID");

    try {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM tblcustomer");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String customerName = rs.getString("CustomerName");
            String customerPhone = rs.getString("CustomerEmail");
            String customerEmail = rs.getString("CustomerPhone");
            String customerAddress = rs.getString("CustomerAddress");
            String customerID = rs.getString("CustomerID");

            model.addRow(new Object[]{customerName, customerEmail,customerPhone , customerAddress, customerID});
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e);
    }
    table.setModel(model);
}
    
    public void searchCustomer(JTable table, JTextField txtSearch) {
        String search = txtSearch.getText().trim();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Customer Name");
        model.addColumn("Customer Phone");
        model.addColumn("Customer Email");
        model.addColumn("Customer Address");
        model.addColumn("Customer ID");

        String query = "SELECT * FROM tblcustomer WHERE CustomerID LIKE ?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            String searchTerm = "%" + search + "%";
            ps.setString(1, searchTerm);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String customerName = rs.getString("CustomerName");
                String customerPhone = rs.getString("CustomerEmail");
                String customerEmail = rs.getString("CustomerPhone");
                String customerAddress = rs.getString("CustomerAddress");
                String customerID = rs.getString("CustomerID");

                model.addRow(new Object[]{customerName, customerEmail, customerPhone, customerAddress, customerID});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Walang ganyan, Hoy");
        }

        table.setModel(model);
    }

    public String[] getCustomerDetailsById(String customerID) {
        String[] customerDetails = new String[1];  // Index 0 for name, index 1 for phone number
        String query = "SELECT CustomerName FROM tblcustomer WHERE CustomerID = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, customerID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                customerDetails[0] = rs.getString("CustomerName");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error fetching customer details: " + e.getMessage());
        }
        return customerDetails;
    }


    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public String getCustomerEmail() {
        return CustomerEmail;
    }

    public void setCustomerEmail(String CustomerEmail) {
        this.CustomerEmail = CustomerEmail;
    }

    public String getCustomerPhoneNo() {
        return CustomerPhoneNo;
    }

    public void setCustomerPhoneNo(String CustomerPhoneNo) {
        this.CustomerPhoneNo = CustomerPhoneNo;
    }

    public String getCustomerAddress() {
        return CustomerAddress;
    }

    public void setCustomerAddress(String CustomerAddress) {
        this.CustomerAddress = CustomerAddress;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String CustomerID) {
        this.CustomerID = CustomerID;
    }
    
    
    
}
