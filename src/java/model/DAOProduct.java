/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Products;
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
public class DAOProduct extends DBConnect {

    public Vector<String> getCategories() {
        Vector<String> categories = new Vector<>();
        try {
            String sql = "SELECT DISTINCT category_name FROM Products";
            ResultSet rs = getData(sql);
            while (rs.next()) {
                categories.add(rs.getString("category_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

    public int insertProduct(Products pro) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[products]\n"
                + "           ([product_id]\n"
                + "           ,[product_name]\n"
                + "           ,[model_year]\n"
                + "           ,[list_price]\n"
                + "           ,[brand_name]\n"
                + "           ,[category_name]\n"
                + "           ,[product_status])\n"
                + "     VALUES (" + pro.getProduct_id() + ",'" + pro.getProduct_name()
                + "' , " + pro.getModel_year() + "," + pro.getList_price()
                + ", '" + pro.getBrand_name() + "','" + pro.getCategory_name() + "' " + pro.getProduct_status() + ")";
        System.out.println(sql);
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int addProduct(Products pro) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[products]\n"
                + "           ([product_id]\n"
                + "           ,[product_name]\n"
                + "           ,[model_year]\n"
                + "           ,[list_price]\n"
                + "           ,[brand_name]\n"
                + "           ,[category_name]\n"
                + "           ,[product_status])\n"
                + "     VALUES (?,?,?,?,?,?,?)";
        try {

            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, pro.getProduct_id());
            pre.setString(2, pro.getProduct_name());
            pre.setInt(3, pro.getModel_year());
            pre.setDouble(4, pro.getList_price());
            pre.setString(5, pro.getBrand_name());
            pre.setString(6, pro.getCategory_name());
            pre.setInt(7, pro.getProduct_status());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateProduct(Products pro) {
        int n = 0;
        String sql = "UPDATE [dbo].[products]\n"
                + "   SET [product_name] = ?,[model_year] = ? ,[list_price] = ?,[brand_name] = ?,[category_name] = ?,[product_status] = ? \n"
                + " WHERE [product_id] = ?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, pro.getProduct_name());
            pre.setInt(2, pro.getModel_year());
            pre.setDouble(3, pro.getList_price());
            pre.setString(4, pro.getBrand_name());
            pre.setString(5, pro.getCategory_name());
            pre.setInt(6, pro.getProduct_status());
            pre.setInt(7, pro.getProduct_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int changeStatus(int pid, int newStatus) {
        int n = 0;
        String sql = "update products set product_status=" + newStatus + "where product_id=" + pid;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int removeProduct(int pid) {
        int n = 0;

        // check foreign key
        String sqlSelect = "select product_id from order_items where product_id=" + pid
                + " union\n"
                + "select product_id from stocks where product_id=" + pid;
        ResultSet rs = this.getData(sqlSelect);
        try {
            if (rs.next()) { //ton tai gia tri been khoa ngoai
                changeStatus(pid, 0);
            } else {
                String sql = "delete from products where product_id=" + pid;
                Statement state = conn.createStatement();
                n = state.executeUpdate(sql);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<Products> getProducts(String sql) {
        Vector<Products> productVector = new Vector<Products>();
        try {
//            default:ResultSet.TYPE_FORWARD_ONLY
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                //rs.getDataType(index|fielName);
                int product_id = rs.getInt("product_id");
                String product_name = rs.getString("product_name");
                int model_year = rs.getInt("model_year");

                double list_price = rs.getDouble("list_price");
                String brand_name = rs.getString("brand_name"),
                        category_name = rs.getString("category_name");
                int product_status = rs.getInt("product_status");
                Products pro = new Products(product_id, product_name, model_year, list_price, brand_name, category_name, product_status);
                productVector.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productVector;
    }

    public Vector<Products> getProductsByCategory(String category) {
        Vector<Products> productVector = new Vector<>();
        String sql = "SELECT * FROM Products WHERE category_name = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, category);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                String product_name = rs.getString("product_name");
                int model_year = rs.getInt("model_year");
                double list_price = rs.getDouble("list_price");
                String brand_name = rs.getString("brand_name");
                int product_status = rs.getInt("product_status");
                Products pro = new Products(product_id, product_name, model_year, list_price, brand_name, category, product_status);
                productVector.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productVector;
    }

    public static void main(String[] args) {
        DAOProduct dao = new DAOProduct();

        // Chèn sản phẩm
//        int n = dao.insertProduct(new Products(202, "product_name", 2024, 200.3, "brand_name", "category_name"));
//        if (n > 0) {
//            System.out.println("Product inserted");
//        }
//
//        // Thêm sản phẩm
//        n = dao.addProduct(new Products(203, "product_name", 2024, 200.3, "brand_name", "category_name"));
//        if (n > 0) {
//            System.out.println("Product added");
//        }
//
//        // Cập nhật sản phẩm
//        n = dao.updateProduct(new Products(203, "new product_name", 2024, 200.3, "brand_name", "category_name"));
//        if (n > 0) {
//            System.out.println("Product updated");
//        }
//
//        // Xóa sản phẩm
//        n = dao.removeProduct(200);
//        if (n > 0) {
//            System.out.println("Product removed");
//        }
        // Lấy danh sách sản phẩm
        Vector<Products> productVector = dao.getProducts("select * from Products");
        for (Products product : productVector) {
            System.out.println(product);
        }
    }
}
