/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Orders;
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
import model.DAOOrders;

/**
 *
 * @author admin
 */
@WebServlet(name = "OrdersController", urlPatterns = {"/OrdersURL"})
public class OrdersController extends HttpServlet {

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
        DAOOrders dao = new DAOOrders();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            display all Products

            String service = request.getParameter("service");
            if (service == null) {
                service = "listAllOrders";
            }
            if (service.equals("deleteOrders")) {
                String oid = request.getParameter("oid");
                int n = dao.removeOrder(Integer.parseInt(oid));
                response.sendRedirect("OrdersURL?service=listAllOrders");
            }
            if (service.equals("updateOrders")) {
                String submit = request.getParameter("submit");
                if (submit == null) {//show insert form

                    String oid = request.getParameter("oid");
                    int orderID = Integer.parseInt(oid);
                    Vector<Orders> vector = dao.getOrders("select * from Orders where order_id=" + orderID);

                    ResultSet rsStore = dao.getData("SELECT distinct  [store_id]  FROM [Orders]");
                    ResultSet rsStaff = dao.getData("SELECT distinct  [staff_id]  FROM [Orders]");

                    request.setAttribute("rsStore", rsStore);
                    request.setAttribute("rsStaff", rsStaff);
                    request.setAttribute("vector", vector);
                    dispath(request, response, "JSP/updateOrders.jsp");
                } else {
                    //  if (submit != null) {
                    //get data

                    String order_id = request.getParameter("order_id");
                    String customer_id = request.getParameter("customer_id");
                    String order_status = request.getParameter("order_status");
                    String order_date = request.getParameter("order_date");
                    String required_date = request.getParameter("required_date");
                    String shipped_date = request.getParameter("shipped_date");
                    String store_id = request.getParameter("store_id");
                    String staff_id = request.getParameter("staff_id");
                    //check data- validate
                    if (order_id.equals("")) {
                        out.print("order_id is empty");
                    }
                    // convert
                    int order_iD = Integer.parseInt(order_id);
                    int customer_iD = Integer.parseInt(customer_id);
                    int order_statuS = Integer.parseInt(order_status);
                    int store_iD = Integer.parseInt(store_id);
                    int staff_iD = Integer.parseInt(staff_id);
                    //
                    Orders order = new Orders(order_iD, customer_iD, order_statuS, order_date, required_date, shipped_date, store_iD, staff_iD);
                    int n = dao.updateOrder(order);
                    response.sendRedirect("OrdersURL?service=listAllOrders");
                }
            }
            if (service.equals("insertOrders")) {
                String submit = request.getParameter("submit");
                if (submit == null) {//show insert form
                     ResultSet rsStore = dao.getData("SELECT distinct  [store_id]  FROM [Orders]");
                    ResultSet rsStaff = dao.getData("SELECT distinct  [staff_id]  FROM [Orders]");

                    request.setAttribute("rsStore", rsStore);
                    request.setAttribute("rsStaff", rsStaff);
                    dispath(request, response, "JSP/insertOrders.jsp");
                } else {
                    //  if (submit != null) {
                    //get data
                   String order_id = request.getParameter("order_id");
                    String customer_id = request.getParameter("customer_id");
                    String order_status = request.getParameter("order_status");
                    String order_date = request.getParameter("order_date");
                    String required_date = request.getParameter("required_date");
                    String shipped_date = request.getParameter("shipped_date");
                    String store_id = request.getParameter("store_id");
                    String staff_id = request.getParameter("staff_id");
                    //check data- validate
                    if (order_id.equals("")) {
                        out.print("order_id is empty");
                    }
                    // convert
                    int order_iD = Integer.parseInt(order_id);
                    int customer_iD = Integer.parseInt(customer_id);
                    int order_statuS = Integer.parseInt(order_status);
                    int store_iD = Integer.parseInt(store_id);
                    int staff_iD = Integer.parseInt(staff_id);
                    //
                    Orders order = new Orders(order_iD, customer_iD, order_statuS, order_date, required_date, shipped_date, store_iD, staff_iD);
                    int n = dao.addOrder(order);
                    response.sendRedirect("OrdersURL?service=listAllOrders");
                }
            }

            if (service.equals("listAllOrders")) { //request
                //call Model
                String sql = "select * from Orders";
                String submit = request.getParameter("submit");
                if (submit != null) {
                    String oid = request.getParameter("oid");
                    sql = "select * from Orders where order_id like '%" + oid + "%'";
                }
                Vector<Orders> vector = dao.getOrders(sql);
                //select view:jsp
                RequestDispatcher dispath = request.getRequestDispatcher("/JSP/listOrders.jsp");
                //set data to view
                request.setAttribute("ordersData", vector);
                request.setAttribute("tableTitle", "Orders Manage");
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
