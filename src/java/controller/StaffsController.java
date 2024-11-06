/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Staffs;
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
import model.DAOStaffs;

/**
 *
 * @author admin
 */
@WebServlet(name = "StaffsController", urlPatterns = {"/StaffsURL"})
public class StaffsController extends HttpServlet {

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
        DAOStaffs dao = new DAOStaffs();
        HttpSession session = request.getSession(true);
        try (PrintWriter out = response.getWriter()) {

            String service = request.getParameter("service");

            if (service == null) {
                service = "listAllStaffs";
            }
            if (service.equals("logoutStaffs")) {
                session.invalidate();
                response.sendRedirect("StaffsURL?service=listAllStaffs");
            }

            if (service.equals("loginStaffs")) {
                String submit = request.getParameter("submit");
                Staffs staffs = null;
                if (submit == null) {
                    request.getRequestDispatcher("JSP/loginStaffs.jsp").forward(request, response);
                } else {
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    staffs = dao.login(username, password);
                    if (staffs == null) {
                        request.setAttribute("erro", "login faile");
                        request.getRequestDispatcher("JSP/loginStaffs.jsp").forward(request, response);
                    } else {
                        session.setAttribute("staffs", staffs);
                        response.sendRedirect("StaffsURL?service=listAllStaffs");

                    }
                }
            }
            if (service.equals("deleteStaffs")) {
                String sid = request.getParameter("sid");
                int n = dao.removeStaff(Integer.parseInt(sid));
                if (n > 0) {
                    response.sendRedirect("StaffsURL?service=listAllStaffs");
                } else {
                    out.println("Error occurred while deleting staffs.");
                }
            }

            if (service.equals("updateStaffs")) {
                String submit = request.getParameter("submit");
                if (submit == null) {//show insert form

                    String sid = request.getParameter("sid");
                    int staffID = Integer.parseInt(sid);
                    Vector<Staffs> vector = dao.getStaffs("select * from Staffs where staff_id=" + staffID);
                    ResultSet rsActive = dao.getData("SELECT distinct active FROM [Staffs]");
                    ResultSet rsStore = dao.getData("SELECT distinct store_id FROM [Staffs]");
                    ResultSet rsManager = dao.getData("SELECT distinct manager_id FROM [Staffs]");
                    request.setAttribute("rsActive", rsActive);
                    request.setAttribute("rsStore", rsStore);
                    request.setAttribute("rsManager", rsManager);
                    request.setAttribute("vector", vector);
                    dispath(request, response, "JSP/updateStaffs.jsp");
                } else {
                    //  if (submit != null) {
                    //get data
                    String staff_id = request.getParameter("staff_id");
                    String first_name = request.getParameter("first_name");
                    String last_name = request.getParameter("last_name");
                    String email = request.getParameter("email");
                    String phone = request.getParameter("phone");
                    String active = request.getParameter("active");
                    String store_id = request.getParameter("store_id");
                    String manager_id = request.getParameter("manager_id");
                    //check data- validate
                    if (staff_id.equals("")) {
                        out.print("staff_id is empty");
                    }
                    // convert
                    int staff_iD = Integer.parseInt(staff_id);
                    int activE = Integer.parseInt(active);
                    int store_iD = Integer.parseInt(store_id);
                    int manager_iD = Integer.parseInt(manager_id);
                    //
                    Staffs staffs = new Staffs(staff_iD, first_name, last_name, email, phone, activE, store_iD, manager_iD);
                    int n = dao.updateStaff(staffs);
                    response.sendRedirect("StaffsURL?service=listAllStaffs");
                }
            }

            if (service.equals("insertStaffs")) {
                String submit = request.getParameter("submit");
                if (submit == null) {//show insert form
                    
                    ResultSet rsActive = dao.getData("SELECT distinct active FROM [Staffs]");
                    ResultSet rsStore = dao.getData("SELECT distinct store_id FROM [Staffs]");
                    ResultSet rsManager = dao.getData("SELECT distinct manager_id FROM [Staffs]");
                    request.setAttribute("rsActive", rsActive);
                    request.setAttribute("rsStore", rsStore);
                    request.setAttribute("rsManager", rsManager);
                    
                    dispath(request, response, "JSP/insertStaffs.jsp");
                } else {
                    //  if (submit != null) {
                    //get data

                    String staff_id = request.getParameter("staff_id");
                    String first_name = request.getParameter("first_name");
                    String last_name = request.getParameter("last_name");
                    String email = request.getParameter("email");
                    String phone = request.getParameter("phone");
                    String active = request.getParameter("active");
                    String store_id = request.getParameter("store_id");
                    String manager_id = request.getParameter("manager_id");
                    //check data- validate
                    if (staff_id.equals("")) {
                        out.print("staff_id is empty");
                    }
                    // convert
                    int staff_iD = Integer.parseInt(staff_id);
                    int activE = Integer.parseInt(active);
                    int store_iD = Integer.parseInt(store_id);
                    int manager_iD = Integer.parseInt(manager_id);

                    //
                    Staffs staffs = new Staffs(staff_iD, first_name, last_name, email, phone, activE, store_iD, manager_iD);
                    int n = dao.addStaff(staffs);
                    response.sendRedirect("StaffsURL?service=listAllStaffs");
                }
            }

            if (service.equals("listAllStaffs")) {
                //call Model
                String sql = "select * from Staffs";
                String submit = request.getParameter("submit");
                if (submit != null) {
                    String sname = request.getParameter("sname");
                    sql = "select * from Staffs where first_name like '%" + sname + "%'";
                }
                Vector<Staffs> vector = dao.getStaffs(sql);
                //select view:jsp
                RequestDispatcher dispath = request.getRequestDispatcher("/JSP/listStaffs.jsp");
                //set data to view
                request.setAttribute("staffsData", vector);
                request.setAttribute("tableTitle", "Staffs Manage");
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
