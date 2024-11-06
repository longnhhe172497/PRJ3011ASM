/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Stocks;
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
public class DAOStocks extends DBConnect {

    public int insertStocks(Stocks stock) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[stocks]\n"
                + "           ([store_id]\n"
                + "           ,[product_id]\n"
                + "           ,[quantity])\n"
                + "     VALUES(" + stock.getStore_id() + ", " + stock.getProduct_id() + ", " + stock.getQuantity() + ")";
        System.out.println(sql);
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOStocks.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addStock(Stocks stock) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[stocks]\n"
                + "           ([store_id]\n"
                + "           ,[product_id]\n"
                + "           ,[quantity])\n"
                + "     VALUES (?, ?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, stock.getStore_id());
            pre.setInt(2, stock.getProduct_id());
            pre.setInt(3, stock.getQuantity());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOStocks.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateStock(Stocks stock) {
        int n = 0;
        String sql = "UPDATE [dbo].[stocks]\n"
                + "   SET [quantity] = ?\n"
                + " WHERE [store_id] = ? AND [product_id] = ?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, stock.getQuantity());
            pre.setInt(2, stock.getStore_id());
            pre.setInt(3, stock.getProduct_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOStocks.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int removeStock(int store_id, int product_id) {
        int n = 0;
        String sql = "DELETE FROM [dbo].[stocks] WHERE [store_id] = ? AND [product_id] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, store_id);
            pre.setInt(2, product_id);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOStocks.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<Stocks> getStocks(String sql) {
        Vector<Stocks> vector = new Vector<>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int store_id = rs.getInt("store_id");
                int product_id = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");

                Stocks stock = new Stocks(store_id, product_id, quantity);
                vector.add(stock);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStocks.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public static void main(String[] args) {
        DAOStocks stock = new DAOStocks();
        // insrt
        int n = stock.insertStocks(new Stocks(1, 101, 50));
        if (n > 0) {
            System.out.println("Stock inserted");
        }

        // add
        n = stock.addStock(new Stocks(1, 102,  30));
        if (n > 0) {
            System.out.println("Stock added");
        }

        // update
        n = stock.updateStock(new Stocks(1, 102, 25));
        if (n > 0) {
            System.out.println("Stock updated");
        }

        // remove
        n = stock.removeStock(1, 101);
        if (n > 0) {
            System.out.println("Stock removed");
        }

        // select
        Vector<Stocks> vector = stock.getStocks("select * from Stocks");
        for (Stocks stocks : vector) {
            System.out.println(stocks);
        }
    }
}
