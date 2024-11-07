/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Products;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.util.Vector;
import model.DAOProduct;

/**
 *
 * @author admin
 */
@WebServlet(name = "ProductController", urlPatterns = {"/ProductURL"})
public class ProductController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOProduct dao = new DAOProduct();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            display all Products

            String service = request.getParameter("service");
            if (service == null) {
                service = "listAllProducts";
            }
            if (service.equals("deleteProduct")) {
                String pid = request.getParameter("pid");
                int n = dao.removeProduct(Integer.parseInt(pid));
                response.sendRedirect("ProductURL?service=listAllProducts");
            }
            if (service.equals("updateProduct")) {
                String submit = request.getParameter("submit");
                if (submit == null) {//show insert form

                    String pid = request.getParameter("pid");
                    int productID = Integer.parseInt(pid);
                    Vector<Products> productVector = dao.getProducts("select * from Products where product_id=" + productID);

                    ResultSet rsCate = dao.getData("SELECT distinct  [category_name]  FROM [products]");
                    ResultSet rsBrand = dao.getData("SELECT distinct  brand_name  FROM [products]");
                    request.setAttribute("rsCate", rsCate);
                    request.setAttribute("rsBrand", rsBrand);
                    request.setAttribute("vector", productVector);
                    dispath(request, response, "JSP/updateProduct.jsp");
                } else {
                    //  if (submit != null) {
                    //get data
                    String product_id = request.getParameter("product_id");
                    String product_name = request.getParameter("product_name");
                    String model_year = request.getParameter("model_year");
                    String list_price = request.getParameter("list_price");
                    String brand_name = request.getParameter("brand_name");
                    String category_name = request.getParameter("category_name");
                    String product_status = request.getParameter("product_status");
                    //check data- validate
                    if (product_id.equals("")) {
                        out.print("product_id is empty");
                    }
                    // convert
                    int product_iD = Integer.parseInt(product_id);
                    int model_yeaR = Integer.parseInt(model_year);
                    double list_pricE = Double.parseDouble(list_price);
                    int product_statuS = Integer.parseInt(product_status);
                    //
                    Products pro = new Products(product_iD, product_name, model_yeaR, list_pricE, brand_name, category_name, product_statuS);
                    int n = dao.updateProduct(pro);
                    response.sendRedirect("ProductURL?service=listAllProducts");
                }
            }
            if (service.equals("insertProduct")) {
                String submit = request.getParameter("submit");
                if (submit == null) {//show insert form
                    ResultSet rsCate = dao.getData("SELECT distinct  [category_name]  FROM [products]");
                    ResultSet rsBrand = dao.getData("SELECT distinct  brand_name  FROM [products]");
                    request.setAttribute("rsCate", rsCate);
                    request.setAttribute("rsBrand", rsBrand);
                    dispath(request, response, "JSP/insertProduct.jsp");
                } else {
                    //  if (submit != null) {
                    //get data
                    String product_id = request.getParameter("product_id");
                    String product_name = request.getParameter("product_name");
                    String model_year = request.getParameter("model_year");
                    String list_price = request.getParameter("list_price");
                    String brand_name = request.getParameter("brand_name");
                    String category_name = request.getParameter("category_name");
                    String product_status = request.getParameter("product_status");
                    //check data- validate
                    if (product_id.equals("")) {
                        out.print("product_id is empty");
                    }
                    // convert
                    int product_iD = Integer.parseInt(product_id);
                    int model_yeaR = Integer.parseInt(model_year);
                    double list_pricE = Double.parseDouble(list_price);
                    int product_statuS = Integer.parseInt(product_status);
                    //
                    Products pro = new Products(product_iD, product_name, model_yeaR, list_pricE, brand_name, category_name, product_statuS);
                    int n = dao.addProduct(pro);
                    response.sendRedirect("ProductURL?service=listAllProducts");
                }
            }

            if (service.equals("listAllProducts")) { //request
                String category = request.getParameter("category");
                String sql = "select * from Products";
                String submit = request.getParameter("submit");
                if (submit != null) {
                    String pname = request.getParameter("pname");
                    sql = "select * from Products where product_name like '%" + pname + "%'";
                } else if (category != null && !category.isEmpty()) {
                    sql = "SELECT * FROM Products WHERE category_name = '" + category + "'";
                }
                Vector<Products> productVector = dao.getProducts(sql);
                
                Vector<String> categories = dao.getCategories();
                
                //select view:jsp
                RequestDispatcher dispath = request.getRequestDispatcher("/JSP/home.jsp");
                //set data to view
                request.setAttribute("productData", productVector);
                request.setAttribute("tableTitle", "Product");
                request.setAttribute("categories", categories);
                //run
                dispath.forward(request, response);
//                String submit=request.getParameter("submit");
//                if(submit!=null){
//                    String pname=request.getParameter("pname");
//                    sql="select * from Products where product_name like '%"+pname+"%'";
//                } 
            }
            
            if (service.equals("productManager")) { //request
                String category = request.getParameter("category");
                String sql = "select * from Products";
                String submit = request.getParameter("submit");
                if (submit != null) {
                    String pname = request.getParameter("pname");
                    sql = "select * from Products where product_name like '%" + pname + "%'";
                } else if (category != null && !category.isEmpty()) {
                    sql = "SELECT * FROM Products WHERE category_name = '" + category + "'";
                }
                Vector<Products> productVector = dao.getProducts(sql);
                
                Vector<String> categories = dao.getCategories();
                
                //select view:jsp
                RequestDispatcher dispath = request.getRequestDispatcher("/JSP/listProduct.jsp");
                //set data to view
                request.setAttribute("productData", productVector);
                request.setAttribute("tableTitle", "Product");
                request.setAttribute("categories", categories);
                //run
                dispath.forward(request, response);
//                String submit=request.getParameter("submit");
//                if(submit!=null){
//                    String pname=request.getParameter("pname");
//                    sql="select * from Products where product_name like '%"+pname+"%'";
//                } 
            }
        }
    }

    protected void dispath(HttpServletRequest request,
            HttpServletResponse response, String URL) throws ServletException, IOException {
        RequestDispatcher dis = request.getRequestDispatcher(URL);
        dis.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
