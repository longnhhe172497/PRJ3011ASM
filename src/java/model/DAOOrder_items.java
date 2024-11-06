/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Order_items;
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
public class DAOOrder_items extends DBConnect {

    public int insertOrder_items(Order_items oitems) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[order_items]\n"
                + "           ([order_id]\n"
                + "           ,[item_id]\n"
                + "           ,[product_id]\n"
                + "           ,[quantity]\n"
                + "           ,[list_price]\n"
                + "           ,[discount])\n"
                + "     VALUES (" + oitems.getOrder_id() + ", " + oitems.getItem_id()
                + ", " + oitems.getProduct_id() + ", " + oitems.getQuantity()
                + ", " + oitems.getList_price() + ", " + oitems.getDiscount() + ")";

        System.out.println(sql);
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder_items.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addOrder_items(Order_items oitems) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[order_items]\n"
                + "           ([order_id]\n"
                + "           ,[item_id]\n"
                + "           ,[product_id]\n"
                + "           ,[quantity]\n"
                + "           ,[list_price]\n"
                + "           ,[discount])\n"
                + "     VALUES(?,?,?,?,?,? )";
        try {

            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, oitems.getOrder_id());
            pre.setInt(2, oitems.getItem_id());
            pre.setInt(3, oitems.getProduct_id());
            pre.setInt(4, oitems.getQuantity());
            pre.setDouble(5, oitems.getList_price());
            pre.setDouble(6, oitems.getDiscount());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder_items.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateOrder_items(Order_items oitems) {
        int n = 0;
        String sql = "UPDATE [dbo].[order_items]\n"
                + "   SET [product_id] = ?\n"
                + "      ,[quantity] = ?\n"
                + "      ,[list_price] = ?\n"
                + "      ,[discount] = ?\n"
                + " WHERE [order_id] = ? AND [item_id]=?";
//   private int order_id;
//    private int item_id;
//    private int product_id;
//    private int quantity;
//    private double list_price;
//    private double discount;
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, oitems.getProduct_id());
            pre.setInt(2, oitems.getQuantity());
            pre.setDouble(3, oitems.getList_price());
            pre.setDouble(4, oitems.getDiscount());
            pre.setInt(5, oitems.getOrder_id());
            pre.setInt(6, oitems.getItem_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder_items.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int removeOrderItem(int order_id, int item_id) {
        int n = 0;
        String sql = "DELETE FROM [dbo].[order_items] WHERE [order_id] = ? AND [item_id] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, order_id);
            pre.setInt(2, item_id);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder_items.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<Order_items> getOrderItems(String sql) {
        Vector<Order_items> vector = new Vector<>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int order_id = rs.getInt("order_id");
                int item_id = rs.getInt("item_id");
                int product_id = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                double list_price = rs.getDouble("list_price");
                double discount = rs.getDouble("discount");

                Order_items item = new Order_items(order_id, item_id, product_id, quantity, list_price, discount);
                vector.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder_items.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public static void main(String[] args) {
        DAOOrder_items dao = new DAOOrder_items();

        // Add 
       // int n = dao.insertOrder_items(new Order_items(1, 1, 101, 3, 15.69, 0));
//        if (n > 0) {
//            System.out.println("Order item added");
//        }
//
//        // Update 
//        n = dao.addOrder_items(new Order_items(1, 1, 101, 3, 15.69, 0));
//        if (n > 0) {
//            System.out.println("Order item updated");
//        }

        //Update 
       int n = dao.updateOrder_items(new Order_items(1, 2, 123, 123, 15.69, 0));
        if (n > 0) {
            System.out.println("Order item updated");
        }

//        // Remove 
//        n = dao.removeOrderItem(1, 1);
//        if (n > 0) {
//            System.out.println("Order item removed");
//        }
//
//        // select
//        Vector<Order_items> orderItems = dao.getOrderItems("SELECT * FROM [dbo].[order_items]");
//        for (Order_items item : orderItems) {
//            System.out.println(item);
//        }
    }
}
