/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Orders;
import entity.Order_items;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAOOrder_items;
import model.DAOOrders;

/**
 *
 * @author HOANG LONG
 */
@WebServlet(name = "BillController", urlPatterns = {"/BillURL"})
public class BillController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            DAOOrders daoOrders = new DAOOrders();
            DAOOrder_items daoOrderItems = new DAOOrder_items();
            String service = request.getParameter("service");
            
            if (service == null) {
                service = "listAllBill";
            }
            
            if (service.equals("listAllBill")) {
                Vector<Orders> orderList = daoOrders.getOrders("SELECT * FROM orders");
                Vector<Order_items> orderItemsList = daoOrderItems.getOrderItems("SELECT * FROM order_items");
                request.setAttribute("ordersData", orderList);
                request.setAttribute("orderItemsData", orderItemsList);
                dispatch(request, response, "/JSP/billManager.jsp");
            }
            
            if (service.equals("updateStatus")) {
                int orderId = Integer.parseInt(request.getParameter("orderId"));
                int currentStatus = Integer.parseInt(request.getParameter("status"));
                
                // Cập nhật trạng thái mới theo giá trị đã chọn
                Orders order = new Orders();
                order.setOrder_id(orderId);
                order.setOrder_status(currentStatus);
                int n = daoOrders.updateOrderStatus(order);
                
                // Chuyển hướng về trang danh sách
                response.sendRedirect("BillURL?service=listAllBill");
            }
        }
    }
    
    private void dispatch(HttpServletRequest request, HttpServletResponse response, String path) 
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
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
        return "Bill and Payment Management";
    }// </editor-fold>

}
