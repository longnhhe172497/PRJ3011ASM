/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.sun.jdi.connect.spi.Connection;
import entity.Cart;
import entity.Customer;
import entity.Order_items;
import entity.Orders;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import static java.lang.System.out;
import java.util.Enumeration;
import java.util.Vector;
import model.DAOOrder_items;
import model.DAOOrders;
import model.DBConnect;

/**
 *
 * @author HOANG LONG
 */
@WebServlet(name = "CheckOutController", urlPatterns = {"/CheckOutURL"})
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
            response.sendRedirect("CustomerURL?service=loginCustomer");
            return;
        }

        // Lưu giỏ hàng, tổng tiền và thông tin khách hàng vào request
        request.setAttribute("vectorCart", vectorCart);
        request.setAttribute("grandTotal", grandTotal);
        request.setAttribute("customer", customer);

        // Chuyển hướng tới trang checkout.jsp để hiển thị thông tin
        request.getRequestDispatcher("JSP/checkout.jsp").forward(request, response);

        DAOOrders daoOr = new DAOOrders();
        DAOOrder_items daoOrIt = new DAOOrder_items();
        try {
            String service = request.getParameter("service");
            if ("confirmOrder".equals(service)) {
                String order_id = request.getParameter("order_id");  // Not needed anymore
                String customer_id = request.getParameter("customer_id");
                String order_status = request.getParameter("order_status");
                String order_date = "2024-11-07"; // Example order date (this should come dynamically)
                String required_date = "2024-11-14"; // Example required date (this should come dynamically)
                String shipped_date = "2024-11-08";
                String store_id = request.getParameter("store_id");
                String staff_id = request.getParameter("staff_id");

                // check data- validate
                if (customer_id == null || customer_id.equals("")) {
                    out.print("customer_id is empty");
                    return;
                }

                // Convert to integers
                int customer_iD = Integer.parseInt(customer_id);
                int order_statuS = Integer.parseInt(order_status);
                int store_iD = Integer.parseInt(store_id);
                int staff_iD = Integer.parseInt(staff_id);

                // Get the next order_id by finding the max order_id and incrementing it
                int order_iD = daoOr.getNextOrderId();  // This method gets the next order_id (max + 1)

                // Create a new order
                Orders order = new Orders(order_iD, customer_iD, order_statuS, order_date, required_date, shipped_date, store_iD, staff_iD);

                // Add the order to the database
                int n = daoOr.addOrder(order);

                // Remove the cart from session after order is placed
                session.removeAttribute("vectorCart");

                // Forward to order confirmation page
                request.setAttribute("orderId", order_iD);
                request.setAttribute("grandTotal", grandTotal);
                request.getRequestDispatcher("JSP/orderConfirmation.jsp").forward(request, response);
            } else {
                // Handle other services here
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
