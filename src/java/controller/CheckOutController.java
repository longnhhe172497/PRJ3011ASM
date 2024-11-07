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
import java.time.LocalDate;

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
            double total = item.getQuantity() * item.getList_price() * (1 - item.getDiscount());
            grandTotal += total;
        }

        // Lấy thông tin khách hàng từ session
        Customer customer = (Customer) session.getAttribute("customer");

        // Nếu không có khách hàng trong session, chuyển hướng đến trang đăng nhập
        if (customer == null) {
            response.sendRedirect("CustomerURL?service=loginCustomer");
            return;
        }

        // Lưu thông tin khách hàng vào request để hiển thị trên form thanh toán
        request.setAttribute("customerName", customer.getFirst_name() + " " + customer.getLast_name());
        request.setAttribute("customerPhone", customer.getPhone());
        request.setAttribute("customerEmail", customer.getEmail());
        request.setAttribute("customerAddress", customer.getStreet() + ", " + customer.getCity() + ", " + customer.getState());
        
        // Lưu giỏ hàng và tổng tiền vào request
        request.setAttribute("vectorCart", vectorCart);
        request.setAttribute("grandTotal", grandTotal);
        request.setAttribute("customer", customer);

        DAOOrders daoOr = new DAOOrders();
        DAOOrder_items daoOrIt = new DAOOrder_items();
        try {
            String service = request.getParameter("service");
            if ("confirmOrder".equals(service)) {
                // Lấy thông tin từ session
                int customer_id = customer.getCustomer_id();
                int order_status = 1; // Mặc định là đang xử lý
                
                // Lấy ngày hiện tại và tính ngày giao hàng dự kiến (7 ngày sau)
                LocalDate currentDate = LocalDate.now();
                String order_date = currentDate.toString();
                String required_date = currentDate.plusDays(7).toString();
                String shipped_date = currentDate.plusDays(7).toString(); // Chưa giao hàng - đặt là chuỗi rỗng thay vì null
                
                // Giả sử store_id và staff_id được cố định hoặc lấy từ cấu hình
                int store_id = 1; // Cần thay đổi theo logic thực tế
                int staff_id = 1; // Cần thay đổi theo logic thực tế

                // Tạo đơn hàng mới với đầy đủ thông tin
                int order_id = daoOr.getNextOrderId();
                Orders order = new Orders();
                order.setOrder_id(order_id);
                order.setCustomer_id(customer_id);
                order.setOrder_status(order_status);
                order.setOrder_date(order_date);
                order.setRequired_date(required_date);
                order.setShipped_date(shipped_date);
                order.setStore_id(store_id);
                order.setStaff_id(staff_id);
                
                // Thêm đơn hàng vào database
                int n = daoOr.addOrder(order);
                
                if (n > 0) {
                    // Thêm chi tiết đơn hàng
                    int item_id = 1; // Bắt đầu từ 1 và tăng dần
                    for (Cart item : vectorCart) {
                        Order_items orderItem = new Order_items();
                        orderItem.setOrder_id(order_id);
                        orderItem.setItem_id(item_id); // Sử dụng item_id tăng dần
                        orderItem.setProduct_id(item.getProduct_id());
                        orderItem.setQuantity(item.getQuantity());
                        orderItem.setList_price(item.getList_price());
                        orderItem.setDiscount(item.getDiscount());
                        daoOrIt.addOrder_items(orderItem);
                        item_id++; // Tăng item_id sau mỗi lần thêm sản phẩm
                    }
                    
                    // Xóa tất cả các sản phẩm trong giỏ hàng
                    Enumeration<String> sessionAttrs = session.getAttributeNames();
                    while (sessionAttrs.hasMoreElements()) {
                        String attrName = sessionAttrs.nextElement();
                        if (attrName.startsWith("product_")) {
                            session.removeAttribute(attrName);
                        }
                    }
                    session.removeAttribute("cart");
                    
                    // Chuyển hướng đến trang xác nhận đơn hàng
                    request.setAttribute("orderId", order_id);
                    request.setAttribute("grandTotal", grandTotal);
                    request.getRequestDispatcher("JSP/processPayment.jsp").forward(request, response);
                }
            } else {
                // Hiển thị trang checkout
                request.getRequestDispatcher("JSP/checkout.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
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
