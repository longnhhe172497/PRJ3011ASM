/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Customer;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class DAOCustomer extends DBConnect {
    
    
    public Customer login(String user, String pass){
        Customer customer = null;
        try {
            
            String sql="select * from Customers where email=? and phone =?";
            PreparedStatement pre=conn.prepareCall(sql);
            pre.setString(1, user);
            pre.setString(2, pass);
            ResultSet rs= pre.executeQuery();
            if(rs.next()){
                customer= new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customer;
    }

    public int insertCustomer(Customer cus) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[customers]\n"
                + "           ([customer_id]\n"
                + "           ,[first_name]\n"
                + "           ,[last_name]\n"
                + "           ,[phone]\n"
                + "           ,[email]\n"
                + "           ,[street]\n"
                + "           ,[city]\n"
                + "           ,[state]\n"
                + "           ,[zip_code])\n"
                + "     VALUES\n"
                + "           (" + cus.getCustomer_id() + ",'" + cus.getFirst_name() + "','" + cus.getLast_name() + "','" + cus.getPhone()
                + "','" + cus.getEmail() + "','" + cus.getStreet() + "','" + cus.getCity() + "','" + cus.getState() + "','" + cus.getZip_code() + "')";

        System.out.println(sql);
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int addCustomer(Customer cus) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[customers]\n"
                + "           ([customer_id]\n"
                + "           ,[first_name]\n"
                + "           ,[last_name]\n"
                + "           ,[phone]\n"
                + "           ,[email]\n"
                + "           ,[street]\n"
                + "           ,[city]\n"
                + "           ,[state]\n"
                + "           ,[zip_code])\n"
                + "     VALUES(?,?,?,?,?,?,?,?,?)";
        try {

            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, cus.getCustomer_id());
            pre.setString(2, cus.getFirst_name());
            pre.setString(3, cus.getLast_name());
            pre.setString(4, cus.getPhone());
            pre.setString(5, cus.getEmail());
            pre.setString(6, cus.getStreet());
            pre.setString(7, cus.getCity());
            pre.setString(8, cus.getState());
            pre.setString(9, cus.getZip_code());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    // Update customer details
    public int updateCustomer(Customer cus) {
        int n = 0;
        String sql = "UPDATE [dbo].[customers]\n"
                + "   SET [first_name] = ?, [last_name] = ?, [phone] = ?, [email] = ?, [street] = ?, [city] = ?, [state] = ?, [zip_code] = ?\n"
                + " WHERE [customer_id] = ?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cus.getFirst_name());
            pre.setString(2, cus.getLast_name());
            pre.setString(3, cus.getPhone());
            pre.setString(4, cus.getEmail());
            pre.setString(5, cus.getStreet());
            pre.setString(6, cus.getCity());
            pre.setString(7, cus.getState());
            pre.setString(8, cus.getZip_code());
            pre.setInt(9, cus.getCustomer_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    // Remove customer by ID
    public int removeCustomer(int customer_id) {
        int n = 0;
        String sql = "DELETE FROM [dbo].[customers] WHERE [customer_id] = " + customer_id;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    // Retrieve list of customers based on SQL query
    public Vector<Customer> getCustomers(String sql) {
        Vector<Customer> vector = new Vector<Customer>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int customer_id = rs.getInt("customer_id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String street = rs.getString("street");
                String city = rs.getString("city");
                String stateString = rs.getString("state");
                String zip_code = rs.getString("zip_code");

                Customer cus = new Customer(customer_id, first_name, last_name, phone, email, street, city, stateString, zip_code);
                vector.add(cus);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public static void main(String[] args) {
        DAOCustomer dao = new DAOCustomer();

        // Insert customer
        int n = dao.insertCustomer(new Customer(1, "John", "Doe", "123456789", "john.doe@example.com", "123 Street", "City", "State", "12345"));
        if (n > 0) {
            System.out.println("Customer inserted");
        }

        // Add customer using PreparedStatement
        n = dao.addCustomer(new Customer(2, "Jane", "Smith", "987654321", "jane.smith@example.com", "456 Avenue", "Another City", "Another State", "67890"));
        if (n > 0) {
            System.out.println("Customer added");
        }

        // Update customer
        n = dao.updateCustomer(new Customer(1, "John", "Doe Updated", "111222333", "john.updated@example.com", "789 New Street", "New City", "New State", "54321"));
        if (n > 0) {
            System.out.println("Customer updated");
        }

        // Remove customer
        n = dao.removeCustomer(2);
        if (n > 0) {
            System.out.println("Customer removed");
        }

        // Retrieve and display all customers
        Vector<Customer> customers = dao.getCustomers("SELECT * FROM [dbo].[customers]");
        for (Customer cus : customers) {
            System.out.println(cus);
        }
    }
}
