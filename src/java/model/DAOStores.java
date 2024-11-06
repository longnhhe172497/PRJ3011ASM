/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Stores;
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
public class DAOStores extends DBConnect {

    public int insertStores(Stores store) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[stores]\n"
                + "           ([store_id]\n"
                + "           ,[store_name]\n"
                + "           ,[phone]\n"
                + "           ,[email]\n"
                + "           ,[street]\n"
                + "           ,[city]\n"
                + "           ,[state]\n"
                + "           ,[zip_code])\n"
                + "     VALUES(" + store.getStore_id() + ", '" + store.getStore_name() + "', '" + store.getPhone() + "', '" + store.getEmail() + "', '" + store.getStreet() + "', '" + store.getCity() + "', '" + store.getState() + "', '" + store.getZip_code() + "')";
        System.out.println(sql);
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOStores.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addStores(Stores store) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[stores]\n"
                + "           ([store_id]\n"
                + "           ,[store_name]\n"
                + "           ,[phone]\n"
                + "           ,[email]\n"
                + "           ,[street]\n"
                + "           ,[city]\n"
                + "           ,[state]\n"
                + "           ,[zip_code])\n"
                + "     VALUES (?,?,?,?,?,?,?,?)";
        try {

            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, store.getStore_id());
            pre.setString(2, store.getStore_name());
            pre.setString(3, store.getPhone());
            pre.setString(4, store.getEmail());
            pre.setString(5, store.getStreet());
            pre.setString(6, store.getCity());
            pre.setString(7, store.getState());
            pre.setString(8, store.getZip_code());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOStores.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateStore(Stores store) {
        int n = 0;
        String sql = "UPDATE[dbo].[stores]\n"
                + "   SET [store_name] = ?,[phone] = ? ,[email] = ?,[street] = ?,[city] = ?, [state] = ?, [zip_code] = ? \n"
                + " WHERE [store_id] = ?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, store.getStore_name());
            pre.setString(2, store.getPhone());
            pre.setString(3, store.getEmail());
            pre.setString(4, store.getStreet());
            pre.setString(5, store.getCity());
            pre.setString(6, store.getState());
            pre.setString(7, store.getZip_code());
            pre.setInt(8, store.getStore_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOStores.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int removeStore(int storeId) {
        int n = 0;
        String sql = "DELETE FROM [dbo].[stores] WHERE [store_id] =" + storeId;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOStores.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<Stores> getStores(String sql) {
        Vector<Stores> vector = new Vector<>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int store_id = rs.getInt("store_id");
                String store_name = rs.getString("store_name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String street = rs.getString("street");
                String city = rs.getString("city");
                String stateString = rs.getString("state");
                String zip_code = rs.getString("zip_code");
                Stores store = new Stores(store_id, store_name, phone, email, street, city, stateString, zip_code);
                vector.add(store);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStores.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public static void main(String[] args) {
        DAOStores store = new DAOStores();
        // insert
        int n = store.insertStores(new Stores(1, "Store A", "123456789", "storeA@example.com", "123 Street", "City A", "State A", "00001"));
        if (n > 0) {
            System.out.println("Store inserted");
        }

        // add
        n = store.addStores(new Stores(0, "Store B", "987654321", "storeB@example.com", "456 Street", "City B", "State B", "00002"));
        if (n > 0) {
            System.out.println("Store added");
        }

        // update
        n = store.updateStore(new Stores(1, "Updated Store A", "123456789", "storeA_updated@example.com", "123 Street", "City A", "State A", "00001"));
        if (n > 0) {
            System.out.println("Store updated");
        }

        // remove
        n = store.removeStore(1);
        if (n > 0) {
            System.out.println("Store removed");
        }

        // select
        Vector<Stores> vector = store.getStores("select * from Stores");
        for (Stores stores : vector) {
            System.out.println(stores);
        }
    }
}
