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
                        request.setAttribute("erro", "login faile");
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
                    //  if (submit != null) {
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
                    //check data- validate
                    if (customer_id.equals("")) {
                        out.print("customer_id is empty");
                    }
                    // convert
                    int customer_iD = Integer.parseInt(customer_id);

                    //
                    Customer cus = new Customer(customer_iD, first_name, last_name, phone, email, street, city, state, zip_code);
                    int n = dao.updateCustomer(cus);
                    response.sendRedirect("CustomerURL?service=listAllCustomer");
                }
            }

            if (service.equals("insertCustomer")) {
                String submit = request.getParameter("submit");
                if (submit == null) {//show insert form
                    ResultSet rsState = dao.getData("SELECT distinct state  FROM [Customers]");
                    request.setAttribute("rsState", rsState);
                    dispath(request, response, "JSP/insertCustomer.jsp");
                } else {
                    //  if (submit != null) {
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
                    //check data- validate
                    if (customer_id.equals("")) {
                        out.print("customer_id is empty");
                    }
                    // convert
                    int customer_iD = Integer.parseInt(customer_id);

                    //
                    Customer cus = new Customer(customer_iD, first_name, last_name, phone, email, street, city, state, zip_code);
                    int n = dao.addCustomer(cus);
                    response.sendRedirect("CustomerURL?service=listAllCustomer");
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
