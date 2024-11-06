/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Staffs;
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
public class DAOStaffs extends DBConnect {
    
     public Staffs login(String user, String pass){
        Staffs staffs = null;
        try {
            
            String sql="select * from Staffs where email=? and phone =?";
            PreparedStatement pre=conn.prepareCall(sql);
            pre.setString(1, user);
            pre.setString(2, pass);
            ResultSet rs= pre.executeQuery();
            if(rs.next()){
                staffs= new Staffs(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStaffs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return staffs;
    }

    public int insertStaffs(Staffs sta) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[staffs]\n"
                + "           ([staff_id]\n"
                + "           ,[first_name]\n"
                + "           ,[last_name]\n"
                + "           ,[email]\n"
                + "           ,[phone]\n"
                + "           ,[active]\n"
                + "           ,[store_id]\n"
                + "           ,[manager_id])\n"
                + "     VALUES(" + sta.getStaff_id() + ", '" + sta.getFirst_name() + "', '" + sta.getLast_name() + "', '" + sta.getEmail() + "', '"
                + sta.getPhone() + "', " + sta.getActive() + ", " + sta.getStore_id() + ", " + sta.getManager_id() + ")";
        System.out.println(sql);
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOStaffs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addStaff(Staffs staff) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[staffs]\n"
                + "           ([staff_id]\n"
                + "           ,[first_name]\n"
                + "           ,[last_name]\n"
                + "           ,[email]\n"
                + "           ,[phone]\n"
                + "           ,[active]\n"
                + "           ,[store_id]\n"
                + "           ,[manager_id])\n"
                + "     VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, staff.getStaff_id());
            pre.setString(2, staff.getFirst_name());
            pre.setString(3, staff.getLast_name());
            pre.setString(4, staff.getEmail());
            pre.setString(5, staff.getPhone());
            pre.setInt(6, staff.getActive());
            pre.setInt(7, staff.getStore_id());
            pre.setInt(8, staff.getManager_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOStaffs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateStaff(Staffs staff) {
        int n = 0;
        String sql = "UPDATE [dbo].[staffs]\n"
                + "   SET [first_name] = ?, [last_name] = ?, [email] = ?, "
                + "[phone] = ?, [active] = ?, [store_id] = ?, [manager_id] = ?\n"
                + " WHERE [staff_id] = ?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, staff.getFirst_name());
            pre.setString(2, staff.getLast_name());
            pre.setString(3, staff.getEmail());
            pre.setString(4, staff.getPhone());
            pre.setInt(5, staff.getActive());
            pre.setInt(6, staff.getStore_id());
            pre.setInt(7, staff.getManager_id());
            pre.setInt(8, staff.getStaff_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOStaffs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int removeStaff(int staff_id) {
        int n = 0;
        String sql = "DELETE FROM [dbo].[staffs] WHERE [staff_id] =" + staff_id;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOStaffs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<Staffs> getStaffs(String sql) {
        Vector<Staffs> vector = new Vector<>();
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int staff_id = rs.getInt("staff_id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                int active = rs.getInt("active");
                int store_id = rs.getInt("store_id");
                int manager_id = rs.getInt("manager_id");

                Staffs staff = new Staffs(staff_id, first_name, last_name, email, phone, active, store_id, manager_id);
                vector.add(staff);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStaffs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public static void main(String[] args) {
        DAOStaffs sta = new DAOStaffs();
        // insert
        int n = sta.insertStaffs(new Staffs(1, "John", "Doe", "john@example.com", "123456789", 1, 1, 0));
        if (n > 0) {
            System.out.println("Staff inserted");
        }

        // add
        n = sta.addStaff(new Staffs(2, "Jane", "Smith", "jane@example.com", "987654321", 1, 1, 1));
        if (n > 0) {
            System.out.println("Staff added");
        }

        // update
        n = sta.updateStaff(new Staffs(2, "Jane", "Doe", "jane@example.com", "987654321", 1, 1, 1));
        if (n > 0) {
            System.out.println("Staff updated");
        }

        // remove
        n = sta.removeStaff(1);
        if (n > 0) {
            System.out.println("Staff removed");
        }

        // select
        Vector<Staffs> staffs = sta.getStaffs("SELECT * FROM [dbo].[staffs]");
        for (Staffs staff : staffs) {
            System.out.println(staff);
        }
    }
}
