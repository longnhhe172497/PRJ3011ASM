/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Cart;
import entity.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Vector;

/**
 *
 * @author HOANG LONG
 */
@WebServlet(name = "CheckOutController", urlPatterns = {"/checkoutURL"})
public class CheckOutController extends HttpServlet {

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
        HttpSession session = request.getSession(true);
        
        // Lấy giỏ hàng từ session
        Vector<Cart> vectorCart = new Vector<>();
        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            Object attribute = session.getAttribute(attributeName);
            if (attribute instanceof Cart) {
                vectorCart.add((Cart) attribute);
            }
        }

        // Tính tổng tiền của giỏ hàng
        double grandTotal = 0;
        for (Cart item : vectorCart) {
            double total = item.getQuantity() * item.getList_price();
            grandTotal += total;
        }

        // Giả sử thông tin khách hàng được lưu trong session
        Customer customer = (Customer) session.getAttribute("customer");

        // Nếu không có khách hàng trong session, chuyển hướng đến trang đăng nhập
        if (customer == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Lưu giỏ hàng, tổng tiền và thông tin khách hàng vào request
        request.setAttribute("vectorCart", vectorCart);
        request.setAttribute("grandTotal", grandTotal);
        request.setAttribute("customer", customer);

        // Chuyển hướng tới trang checkout.jsp để hiển thị thông tin
        request.getRequestDispatcher("JSP/checkout.jsp").forward(request, response);
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
