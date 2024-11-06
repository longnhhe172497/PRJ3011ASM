/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Stores;
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
import model.DAOStores;

/**
 *
 * @author admin
 */
@WebServlet(name = "StoresController", urlPatterns = {"/StoresURL"})
public class StoresController extends HttpServlet {

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
        DAOStores dao = new DAOStores();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            display all Products

            String service = request.getParameter("service");
            if (service == null) {
                service = "listAllStores";
            }
            if (service.equals("deleteStores")) {
                String sid = request.getParameter("sid");
                int n = dao.removeStore(Integer.parseInt(sid));
                response.sendRedirect("StoresURL?service=listAllStores");
            }
            if (service.equals("updateStores")) {
                String submit = request.getParameter("submit");
                if (submit == null) {//show insert form

                    String sid = request.getParameter("sid");
                    int storeID = Integer.parseInt(sid);
                    Vector<Stores> vector = dao.getStores("select * from Stores where store_id=" + storeID);

                    ResultSet rsCity = dao.getData("SELECT distinct  city  FROM [Stores]");
                    ResultSet rsState = dao.getData("SELECT distinct  state  FROM [Stores]");
                    request.setAttribute("rsCity", rsCity);
                    request.setAttribute("rsState", rsState);
                    request.setAttribute("vector", vector);
                    dispath(request, response, "JSP/updateStores.jsp");
                } else {
                    //  if (submit != null) {
                    //get data
                    String store_id = request.getParameter("store_id");
                    String store_name = request.getParameter("store_name");
                    String phone = request.getParameter("phone");
                    String email = request.getParameter("email");
                    String street = request.getParameter("street");
                    String city = request.getParameter("city");
                    String state = request.getParameter("state");
                    String zip_code = request.getParameter("zip_code");
                    //check data- validate
                    if (store_id.equals("")) {
                        out.print("store_id is empty");
                    }
                    // convert
                    int store_iD = Integer.parseInt(store_id);

                    //
                    Stores stores = new Stores(store_iD, store_name, phone, email, street, city, state, zip_code);
                    int n = dao.updateStore(stores);
                    response.sendRedirect("StoresURL?service=listAllStores");
                }
            }
            if (service.equals("insertStores")) {
                String submit = request.getParameter("submit");
                if (submit == null) {//show insert form
                    ResultSet rsCity = dao.getData("SELECT distinct  city  FROM [Stores]");
                    ResultSet rsState = dao.getData("SELECT distinct  state  FROM [Stores]");
                    request.setAttribute("rsCity", rsCity);
                    request.setAttribute("rsState", rsState);
                    dispath(request, response, "JSP/insertStores.jsp");
                } else {
                    //  if (submit != null) {
                    //get data
                    String store_id = request.getParameter("store_id");
                    String store_name = request.getParameter("store_name");
                    String phone = request.getParameter("phone");
                    String email = request.getParameter("email");
                    String street = request.getParameter("street");
                    String city = request.getParameter("city");
                    String state = request.getParameter("state");
                    String zip_code = request.getParameter("zip_code");
                    //check data- validate
                    if (store_id.equals("")) {
                        out.print("store_id is empty");
                    }
                    // convert
                    int store_iD = Integer.parseInt(store_id);

                    //
                    Stores stores = new Stores(store_iD, store_name, phone, email, street, city, state, zip_code);
                    int n = dao.addStores(stores);
                    response.sendRedirect("StoresURL?service=listAllStores");
                }
            }

            if (service.equals("listAllStores")) { //request
                //call Model
                String sql = "select * from Stores";
                String submit = request.getParameter("submit");
                if (submit != null) {
                    String sname = request.getParameter("sname");
                    sql = "select * from Stores where store_name like '%" + sname + "%'";
                }
                Vector<Stores> vector = dao.getStores(sql);
                //select view:jsp
                RequestDispatcher dispath = request.getRequestDispatcher("/JSP/listStores.jsp");
                //set data to view
                request.setAttribute("storesData", vector);
                request.setAttribute("tableTitle", "Stores Manage");
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
