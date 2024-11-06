/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Order_items;
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
import model.DAOOrder_items;

/**
 *
 * @author admin
 */
@WebServlet(name = "Order_itemsController", urlPatterns = {"/Order_itemsURL"})
public class Order_itemsController extends HttpServlet {

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

        DAOOrder_items dao = new DAOOrder_items();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            display all Products

            String service = request.getParameter("service");
            if (service == null) {
                service = "listAllOrder_items";
            }
            if (service.equals("deleteOrder_items")) {
                String oid = request.getParameter("oid");
                String iid = request.getParameter("iid");
                int n = dao.removeOrderItem(Integer.parseInt(oid), Integer.parseInt(iid));
                response.sendRedirect("Order_itemsURL?service=listAllOrder_items");
            }
            if (service.equals("updateOrder_items")) {
                String submit = request.getParameter("submit");
                if (submit == null) {//show insert form

                    String oid = request.getParameter("oid");
                    String iid = request.getParameter("iid");
                    int orderID = Integer.parseInt(oid);
                    int itemID = Integer.parseInt(iid);
                    Vector<Order_items> vector = dao.getOrderItems("SELECT * FROM Order_items WHERE order_id = " + orderID + " AND item_id = " + itemID);

                    request.setAttribute("vector", vector);
                    dispath(request, response, "JSP/updateOrder_items.jsp");
                } else {
                    //  if (submit != null) {
                    //get data

                    String order_id = request.getParameter("order_id");
                    String item_id = request.getParameter("item_id");
                    String product_id = request.getParameter("product_id");
                    String quantity = request.getParameter("quantity");
                    String list_price = request.getParameter("list_price");
                    String discount = request.getParameter("discount");

                    //check data- validate
                    if (order_id.equals("")) {
                        out.print("order_id is empty");
                    }
                    // convert
                    int order_iD = Integer.parseInt(order_id);
                    int item_iD = Integer.parseInt(item_id);
                    int product_iD = Integer.parseInt(product_id);
                    int quantitY = Integer.parseInt(quantity);
                    double list_pricE = Double.parseDouble(list_price);
                    double discounT = Double.parseDouble(discount);
                    //
                    Order_items oitem = new Order_items(order_iD, item_iD, product_iD, quantitY, list_pricE, discounT);
                    int n = dao.updateOrder_items(oitem);
                    
                    response.sendRedirect("Order_itemsURL?service=listAllOrder_items");
                }
            }
            if (service.equals("insertOrder_items")) {
                String submit = request.getParameter("submit");
                if (submit == null) {//show insert form
                    ResultSet rsOid = dao.getData("SELECT distinct  [order_id]  FROM [Order_items]");
                    request.setAttribute("rsOid", rsOid);   
                    dispath(request, response, "JSP/insertOrder_items.jsp");
                } else {
                    //  if (submit != null) {
                    //get data
                    String order_id = request.getParameter("order_id");
                    String item_id = request.getParameter("item_id");
                    String product_id = request.getParameter("product_id");
                    String quantity = request.getParameter("quantity");
                    String list_price = request.getParameter("list_price");
                    String discount = request.getParameter("discount");

                    //check data- validate
                    if (order_id.equals("")) {
                        out.print("order_id is empty");
                    }
                    // convert
                    int order_iD = Integer.parseInt(order_id);
                    int item_iD = Integer.parseInt(item_id);
                    int product_iD = Integer.parseInt(product_id);
                    int quantitY = Integer.parseInt(quantity);
                    double list_pricE = Double.parseDouble(list_price);
                    double discounT = Double.parseDouble(discount);
                    //
                    Order_items oitem = new Order_items(order_iD, item_iD, product_iD, quantitY, list_pricE, discounT);
                    int n = dao.addOrder_items(oitem);
                    response.sendRedirect("Order_itemsURL?service=listAllOrder_items");
                }
            }

            if (service.equals("listAllOrder_items")) { //request
                //call Model
                String sql = "select * from Order_items";
                String submit = request.getParameter("submit");
                if (submit != null) {
                    String oid = request.getParameter("oid");
                    sql = "select * from Order_items where order_id like '%" + oid + "%'";
                }
                Vector<Order_items> vector = dao.getOrderItems(sql);
                //select view:jsp
                RequestDispatcher dispath = request.getRequestDispatcher("/JSP/listOrder_items.jsp");
                //set data to view
                request.setAttribute("Order_itemsData", vector);
                request.setAttribute("tableTitle", "Order_items Manage");
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
