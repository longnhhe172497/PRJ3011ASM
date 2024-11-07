/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Orders;
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
public class DAOOrders extends DBConnect {
    
    public int getNextOrderId() {
        int nextOrderId = 1; // Default to 1 if no orders exist
        String query = "SELECT MAX(order_id) FROM orders";
        
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                nextOrderId = rs.getInt(1) + 1; // Get the next order_id
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return nextOrderId;
    }
    
    public int insertOrder(Orders order) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[orders]\n"
                + "           ([order_id]\n"
                + "           ,[customer_id]\n"
                + "           ,[order_status]\n"
                + "           ,[order_date]\n"
                + "           ,[required_date]\n"
                + "           ,[shipped_date]\n"
                + "           ,[store_id]\n"
                + "           ,[staff_id])\n"
                + "     VALUES(" + order.getOrder_id() + "," + order.getCustomer_id() + "," + order.getOrder_status() + ",'" + order.getOrder_date() + "','" + order.getRequired_date() + "','" + order.getShipped_date() + "'," + order.getStore_id() + "," + order.getStaff_id() + ")";

        System.out.println(sql);
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addOrder(Orders order) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[orders]\n"
                + "           ,[customer_id]\n"
                + "           ,[order_status]\n"
                + "           ,[order_date]\n"
                + "           ,[required_date]\n"
                + "           ,[shipped_date]\n"
                + "           ,[store_id]\n"
                + "           ,[staff_id])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?)";
        try {

            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, order.getCustomer_id());
            pre.setInt(2, order.getOrder_status());
            pre.setString(3, order.getOrder_date());
            pre.setString(4, order.getRequired_date());
            pre.setString(5, order.getShipped_date());
            pre.setInt(6, order.getStore_id());
            pre.setInt(7, order.getStaff_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateOrder(Orders order) {
        int n = 0;
        String sql = "UPDATE [dbo].[orders]\n"
                + "   SET [customer_id] = ?, [order_status] = ?, [order_date] = ?, "
                + "[required_date] = ?, [shipped_date] = ?, [store_id] = ?, [staff_id] = ?\n"
                + " WHERE [order_id] = ?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, order.getCustomer_id());
            pre.setInt(2, order.getOrder_status());
            pre.setString(3, order.getOrder_date());
            pre.setString(4, order.getRequired_date());
            pre.setString(5, order.getShipped_date());
            pre.setInt(6, order.getStore_id());
            pre.setInt(7, order.getStaff_id());
            pre.setInt(8, order.getOrder_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int removeOrder(int order_id) {
        int n = 0;
        String sql = "DELETE FROM [dbo].[orders] WHERE [order_id] = " + order_id;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<Orders> getOrders(String sql) {
        Vector<Orders> vector = new Vector<>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int order_id = rs.getInt("order_id");
                int customer_id = rs.getInt("customer_id");
                int order_status = rs.getInt("order_status");
                String order_date = rs.getString("order_date");
                String required_date = rs.getString("required_date");
                String shipped_date = rs.getString("shipped_date");
                int store_id = rs.getInt("store_id");
                int staff_id = rs.getInt("staff_id");

                Orders order = new Orders(order_id, customer_id, order_status, order_date, required_date, shipped_date, store_id, staff_id);
                vector.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public static void main(String[] args) {
        DAOOrders dao = new DAOOrders();

//        // insert
//        int n = dao.insertOrder(new Orders(5, 100, 6, "2024-09-01", "2024-09-10", "2024-09-05", 2, 2));
//        if (n > 0) {
//            System.out.println("Order inserted");
//        }
//        // add
         int n = dao.addOrder(new Orders(8, 136, 1, "2024-09-02", "2024-09-11", "2024-09-06", 1, 2));
        if (n > 0) {
            System.out.println("Order added");
        }
//
//        // update
//        n = dao.updateOrder(new Orders(2, 102, 2, "2024-09-02", "2024-09-11", "2024-09-06", 1, 2));
//        if (n > 0) {
//            System.out.println("Order updated");
//        }
//
//        // remove
//        n = dao.removeOrder(1);
//        if (n > 0) {
//            System.out.println("Order removed");
//        }
//
//        // select
//        Vector<Orders> orders = dao.getOrders("SELECT * FROM [dbo].[orders]");
//        for (Orders order : orders) {
//            System.out.println(order);
//        }
        }
    }

