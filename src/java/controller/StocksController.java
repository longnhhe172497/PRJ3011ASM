/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Stocks;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOStocks;

/**
 *
 * @author admin
 */
@WebServlet(name = "StocksController", urlPatterns = {"/StocksURL"})
public class StocksController extends HttpServlet {

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
        DAOStocks dao = new DAOStocks();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            display all Products

            String service = request.getParameter("service");
            if (service == null) {
                service = "listAllStocks";
            }
            if (service.equals("deleteStocks")) {
                String sid = request.getParameter("sid");
                String pid = request.getParameter("pid");
                int n = dao.removeStock(Integer.parseInt(sid), Integer.parseInt(pid));
                response.sendRedirect("StocksURL?service=listAllStocks");
            }
            if (service.equals("updateStocks")) {
                String submit = request.getParameter("submit");
                if (submit == null) {//show insert form

                    String sid = request.getParameter("sid");
                    String pid = request.getParameter("pid");
                    int store_id = Integer.parseInt(sid);
                    int product_id = Integer.parseInt(pid);
                    Vector<Stocks> vector = dao.getStocks("select * from Stocks where store_id=" + store_id + " AND product_id = " + product_id);

                    request.setAttribute("vector", vector);
                    dispath(request, response, "JSP/updateStocks.jsp");
                } else {
                    //  if (submit != null) {
                    //get data

                    String store_id = request.getParameter("store_id");
                    String product_id = request.getParameter("product_id");
                    String quantity = request.getParameter("quantity");

                    //check data- validate
                    if (store_id.equals("")) {
                        out.print("store_id is empty");
                    }
                    // convert
                    int store_iD = Integer.parseInt(store_id);
                    int product_iD = Integer.parseInt(product_id);
                    int quantitY = Integer.parseInt(quantity);
                    //
                    Stocks stocks = new Stocks(store_iD, product_iD, quantitY);
                    int n = dao.updateStock(stocks);
                    response.sendRedirect("StocksURL?service=listAllStocks");
                }
            }
            if (service.equals("insertStocks")) {
                String submit = request.getParameter("submit");
                if (submit == null) {//show insert form

                    dispath(request, response, "JSP/insertStocks.jsp");
                } else {
                    //  if (submit != null) {
                    //get data
                    String store_id = request.getParameter("store_id");
                    String product_id = request.getParameter("product_id");
                    String quantity = request.getParameter("quantity");

                    //check data- validate
                    if (store_id.equals("")) {
                        out.print("store_id is empty");
                    }
                    // convert
                    int store_iD = Integer.parseInt(store_id);
                    int product_iD = Integer.parseInt(product_id);
                    int quantitY = Integer.parseInt(quantity);
                    //
                    Stocks stocks = new Stocks(store_iD, product_iD, quantitY);
                    int n = dao.addStock(stocks);
                    response.sendRedirect("StocksURL?service=listAllStocks");
                }
            }

            if (service.equals("listAllStocks")) { //request
                //call Model
                String sql = "select * from Stocks";
                String submit = request.getParameter("submit");
                if (submit != null) {
                    String sid = request.getParameter("sid");
                    sql = "select * from Stocks where store_id like '%" + sid + "%'";
                }
                Vector<Stocks> vector = dao.getStocks(sql);
                //select view:jsp
                RequestDispatcher dispath = request.getRequestDispatcher("/JSP/listStocks.jsp");
                //set data to view
                request.setAttribute("stocksData", vector);
                request.setAttribute("tableTitle", "Stocks Manage");
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
