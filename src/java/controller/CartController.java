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
import model.DAOCart;

/**
 *
 * @author admin
 */
@WebServlet(name = "CartController", urlPatterns = {"/CartURL"})
public class CartController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAOCart dao = new DAOCart();
        HttpSession session = request.getSession(true);

        try (PrintWriter out = response.getWriter()) {
            Cart cart = (Cart) session.getAttribute("cart");

            if (cart == null) {
                cart = new Cart();
                session.setAttribute("cart", cart);
            }

            String service = request.getParameter("service");

            // Handle add to cart
            if ("add2Cart".equals(service)) {
                int pid = Integer.parseInt(request.getParameter("pid"));
                Cart itemInCart = (Cart) session.getAttribute("product_" + pid);

                if (itemInCart == null) {
                    Cart newCartItem = dao.getCart(pid);
                    newCartItem.setQuantity(1);
                    session.setAttribute("product_" + pid, newCartItem);
                } else {
                    itemInCart.setQuantity(itemInCart.getQuantity() + 1);
                    session.setAttribute("product_" + pid, itemInCart);
                }
                updateCartItemCount(session);
                response.sendRedirect("ProductURL?service=listAllProducts");
            } // Handle show cart
            else if ("showCart".equals(service)) {
                Vector<Cart> vectorCart = new Vector<>();
                Enumeration<String> attributeNames = session.getAttributeNames();

                while (attributeNames.hasMoreElements()) {
                    String attributeName = attributeNames.nextElement();
                    Object attribute = session.getAttribute(attributeName);
                    if (attribute instanceof Cart) {
                        vectorCart.add((Cart) attribute);
                    }
                }

                request.setAttribute("vectorCart", vectorCart);
                request.getRequestDispatcher("JSP/viewCart.jsp").forward(request, response);
            } // Handle remove from cart
            else if ("removeCart".equals(service)) {
                String pid = request.getParameter("pid");
                if (pid != null) {
                    session.removeAttribute("product_" + pid);
                }

                Vector<Cart> vectorCart = new Vector<>();
                Enumeration<String> attributeNames = session.getAttributeNames();

                while (attributeNames.hasMoreElements()) {
                    String attributeName = attributeNames.nextElement();
                    Object attribute = session.getAttribute(attributeName);
                    if (attribute instanceof Cart) {
                        vectorCart.add((Cart) attribute);
                    }
                }
                updateCartItemCount(session);
                request.setAttribute("vectorCart", vectorCart);
                request.getRequestDispatcher("JSP/viewCart.jsp").forward(request, response);
            } else if ("updateQuantity".equals(service)) {
                Enumeration<String> parameterNames = request.getParameterNames();
                while (parameterNames.hasMoreElements()) {
                    String paramName = parameterNames.nextElement();
                    if (paramName.startsWith("quantity_")) {
                        int pid = Integer.parseInt(paramName.substring("quantity_".length()));
                        int newQuantity = Integer.parseInt(request.getParameter(paramName));
                        Cart cartItem = (Cart) session.getAttribute("product_" + pid);
                        if (cartItem != null) {
                            cartItem.setQuantity(newQuantity);
                            session.setAttribute("product_" + pid, cartItem);
                        }
                    }
                }
                Vector<Cart> vectorCart = new Vector<>();
                Enumeration<String> attributeNames = session.getAttributeNames();

                while (attributeNames.hasMoreElements()) {
                    String attributeName = attributeNames.nextElement();
                    Object attribute = session.getAttribute(attributeName);
                    if (attribute instanceof Cart) {
                        vectorCart.add((Cart) attribute);
                    }
                }

                updateCartItemCount(session);
                request.setAttribute("vectorCart", vectorCart);
                request.getRequestDispatcher("JSP/viewCart.jsp").forward(request, response);
            }

        }
    }

    private void updateCartItemCount(HttpSession session) {
        int itemCount = 0;
        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            Object attribute = session.getAttribute(attributeName);
            if (attribute instanceof Cart) {
                itemCount++;
            }
        }

        session.setAttribute("cartItemCount", itemCount);
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
