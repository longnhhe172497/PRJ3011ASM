    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Customer;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Vector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DAOCustomer;

/**
 *
 * @author admin
 */
@WebServlet(name = "CustomerController", urlPatterns = {"/CustomerURL"})
public class CustomerController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);

        DAOCustomer dao = new DAOCustomer();

        try (PrintWriter out = response.getWriter()) {

            String service = request.getParameter("service");

            if (service == null) {
                service = "listAllCustomer";
            }
            if (service.equals("logoutCustomer")) {
                session.invalidate();
                response.sendRedirect("ProductURL");
            }

            if (service.equals("loginCustomer")) {
                String submit = request.getParameter("submit");
                Customer customer = null;
                if (submit == null) {
                    request.getRequestDispatcher("JSP/loginCustomer.jsp").forward(request, response);
                } else {
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    customer = dao.login(username, password);
                    if (customer == null) {
                        request.setAttribute("error", "Invalid username or password!");
                        request.getRequestDispatcher("JSP/loginCustomer.jsp").forward(request, response);
                    } else {
                        session.setAttribute("customer", customer);
                        response.sendRedirect("ProductURL");
                    }
                }
            }
            
            if (service.equals("deleteCustomer")) {
                String cid = request.getParameter("cid");
                int n = dao.removeCustomer(Integer.parseInt(cid));
                if (n > 0) {
                    response.sendRedirect("CustomerURL?service=listAllCustomer");
                } else {
                    out.println("Error occurred while deleting customer.");
                }
            }

            if (service.equals("updateCustomer")) {
                String submit = request.getParameter("submit");
                if (submit == null) {//show insert form
                    String cid = request.getParameter("cid");
                    int customerID = Integer.parseInt(cid);
                    Vector<Customer> vector = dao.getCustomers("select * from Customers where customer_id=" + customerID);
                    ResultSet rsState = dao.getData("SELECT distinct state FROM [Customers]");
                    request.setAttribute("rsState", rsState);
                    request.setAttribute("vector", vector);
                    dispath(request, response, "JSP/updateCustomer.jsp");
                } else {
                    //get data
                    String customer_id = request.getParameter("customer_id");
                    String first_name = request.getParameter("first_name");
                    String last_name = request.getParameter("last_name");
                    String phone = request.getParameter("phone");
                    String email = request.getParameter("email");
                    String street = request.getParameter("street");
                    String city = request.getParameter("city");
                    String state = request.getParameter("state");
                    String zip_code = request.getParameter("zip_code");

                    // Validate data
                    StringBuilder errorMsg = new StringBuilder();
                    
                    // Check customer_id
                    if (customer_id == null || customer_id.trim().isEmpty()) {
                        errorMsg.append("Customer ID cannot be empty<br>");
                    }

                    // Check first_name and last_name
                    if (first_name == null || first_name.trim().isEmpty()) {
                        errorMsg.append("First name cannot be empty<br>");
                    }
                    if (last_name == null || last_name.trim().isEmpty()) {
                        errorMsg.append("Last name cannot be empty<br>");
                    }

                    // Check phone number format
                    if (phone != null && !phone.trim().isEmpty() && !phone.matches("\\d{10,11}")) {
                        errorMsg.append("Invalid phone number (must be 10-11 digits)<br>");
                    }

                    // Check email format
                    if (email != null && !email.trim().isEmpty() && !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                        errorMsg.append("Invalid email format<br>");
                    }

                    // Check zip code
                    if (zip_code != null && !zip_code.trim().isEmpty() && !zip_code.matches("\\d{5,6}")) {
                        errorMsg.append("Invalid zip code (must be 5-6 digits)<br>");
                    }

                    // Check if city is empty
                    if (city == null || city.trim().isEmpty()) {
                        errorMsg.append("City cannot be empty<br>");
                    }

                    // Check if street is empty
                    if (street == null || street.trim().isEmpty()) {
                        errorMsg.append("Street address cannot be empty<br>");
                    }

                    // Check if state is empty
                    if (state == null || state.trim().isEmpty()) {
                        errorMsg.append("State cannot be empty<br>");
                    }

                    if (errorMsg.length() > 0) {
                        // If there are errors, display them and retain the form
                        request.setAttribute("error", errorMsg.toString());
                        Vector<Customer> vector = dao.getCustomers("select * from Customers where customer_id=" + customer_id);
                        ResultSet rsState = dao.getData("SELECT distinct state FROM [Customers]");
                        request.setAttribute("rsState", rsState);
                        request.setAttribute("vector", vector);
                        dispath(request, response, "JSP/updateCustomer.jsp");
                        return;
                    }

                    int customer_iD = Integer.parseInt(customer_id);
                    Customer cus = new Customer(customer_iD, first_name, last_name, phone, email, street, city, state, zip_code);
                    int n = dao.updateCustomer(cus);
                    response.sendRedirect("CustomerURL?service=listAllCustomer");
                }
            }

            if (service.equals("insertCustomer")) {
                String submit = request.getParameter("submit");
                if (submit == null) {//show insert form
                    ResultSet rsState = dao.getData("SELECT distinct state FROM [Customers]");
                    request.setAttribute("rsState", rsState);
                    dispath(request, response, "JSP/insertCustomer.jsp");
                } else {
                    //get data
                    String first_name = request.getParameter("first_name");
                    String last_name = request.getParameter("last_name"); 
                    String phone = request.getParameter("phone");
                    String email = request.getParameter("email");
                    String street = request.getParameter("street");
                    String city = request.getParameter("city");
                    String state = request.getParameter("state");
                    String zip_code = request.getParameter("zip_code");

                    // Validate data
                    StringBuilder errorMsg = new StringBuilder();

                    // Check first_name and last_name
                    if (first_name == null || first_name.trim().isEmpty()) {
                        errorMsg.append("First name cannot be empty<br>");
                    }
                    if (last_name == null || last_name.trim().isEmpty()) {
                        errorMsg.append("Last name cannot be empty<br>");
                    }

                    // Check phone number format
                    if (phone != null && !phone.trim().isEmpty() && !phone.matches("\\d{10,11}")) {
                        errorMsg.append("Invalid phone number (must be 10-11 digits)<br>");
                    }

                    // Check email format
                    if (email != null && !email.trim().isEmpty() && !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                        errorMsg.append("Invalid email format<br>");
                    }

                    // Check zip code
                    if (zip_code != null && !zip_code.trim().isEmpty() && !zip_code.matches("\\d{5,6}")) {
                        errorMsg.append("Invalid zip code (must be 5-6 digits)<br>");
                    }

                    // Check if city is empty
                    if (city == null || city.trim().isEmpty()) {
                        errorMsg.append("City cannot be empty<br>");
                    }

                    // Check if street is empty
                    if (street == null || street.trim().isEmpty()) {
                        errorMsg.append("Street address cannot be empty<br>");
                    }

                    // Check if state is empty
                    if (state == null || state.trim().isEmpty()) {
                        errorMsg.append("State cannot be empty<br>");
                    }

                    if (errorMsg.length() > 0) {
                        // If there are errors, display them and retain the form
                        request.setAttribute("error", errorMsg.toString());
                        ResultSet rsState = dao.getData("SELECT distinct state FROM [Customers]");
                        request.setAttribute("rsState", rsState);
                        dispath(request, response, "JSP/insertCustomer.jsp");
                        return;
                    }

                    ResultSet rs = dao.getData("SELECT MAX(customer_id) as maxId FROM Customers");
                    int customer_iD = 1;
                    if(rs.next()) {
                        customer_iD = rs.getInt("maxId") + 1;
                    }

                    Customer cus = new Customer(customer_iD, first_name, last_name, phone, email, street, city, state, zip_code);
                    int n = dao.addCustomer(cus);
                    response.sendRedirect("ProductURL");
                }
            }

            if (service.equals("listAllCustomer")) {
                //call Model
                String sql = "select * from Customers";
                String submit = request.getParameter("submit");
                if (submit != null) {
                    String cname = request.getParameter("cname");
                    sql = "select * from Customers where first_name like '%" + cname + "%'";
                }
                Vector<Customer> vector = dao.getCustomers(sql);
                //select view:jsp
                RequestDispatcher dispath = request.getRequestDispatcher("/JSP/listCustomer.jsp");
                //set data to view
                request.setAttribute("customerData", vector);
                request.setAttribute("tableTitle", "Customer Manage");
                //run
                dispath.forward(request, response);

//                String submit = request.getParameter("submit");
//                if (submit != null) {
//                    String cname = request.getParameter("cname");
//                    sql = "select * from Customers where Customers like '%" + cname + "%'";
//                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
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
