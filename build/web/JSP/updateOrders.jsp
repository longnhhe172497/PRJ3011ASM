<%-- 
    Document   : updateOrders
    Created on : Oct 29, 2024, 9:29:42 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Orders" %>
<%@page import="java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Orders</title>
    </head>
    <body>
        <% Vector<Orders> vector= (Vector<Orders>)request.getAttribute("vector");
           
          Orders order=vector.get(0);
          ResultSet rsStore=(ResultSet)request.getAttribute("rsStore");
          ResultSet rsStaff=(ResultSet)request.getAttribute("rsStaff");
        %>

        <form action="OrdersURL" method="post">
            <input type="hidden" name="service" value="updateOrders">
            <table>
                <caption>
                    <h1>Update Orders</h1>
                </caption>
                <tr>
                    <td><label for="order_id">Order ID</label></td>
                    <td><input type="text" name="order_id" id="order_id" readonly value="<%=order.getOrder_id()%>"></td>
                </tr>
                <tr>
                    <td><label for="customer_id">Customer ID</label></td>
                    <td><input type="text" name="customer_id" id="customer_id" value="<%=order.getCustomer_id()%>"></td>
                </tr>
                <tr>
                    <td><label for="order_status">Order Status</label></td>
                    <td><input type="text" name="order_status" id="order_status" value="<%=order.getOrder_status()%>"></td>
                </tr>
                <tr>
                    <td><label for="order_date">Order Date</label></td>
                    <td><input type="text" name="order_date" id="order_date" value="<%=order.getOrder_date()%>"></td>
                </tr>
                <tr>
                    <td><label for="required_date">Required Date</label></td>
                    <td><input type="text" name="required_date" id="required_date" readonly value="<%=order.getRequired_date()%>"></td>
                </tr>
                <tr>
                    <td><label for="shipped_date">Shipped Date</label></td>
                    <td><input type="text" name="shipped_date" id="shipped_date" readonly value="<%=order.getShipped_date()%>"></td>
                </tr>

                <tr>
                    <td><label for="store_id">Store ID</label></td>
                    <td>
                        <select name="store_id" id="store_id">
                            <%while(rsStore.next()){%>
                            <option value="<%=rsStore.getInt(1)%>"><%=rsStore.getInt(1)%></option>
                            <%}%>

                        </select>

                    </td>
                </tr>
                <tr>
                    <td><label for="staff_id">Staff ID</label></td>
                    <td>
                        <select name="staff_id" id="staff_id">
                            <%while(rsStaff.next()){%>
                            <option value="<%=rsStaff.getInt(1)%>"><%=rsStaff.getInt(1)%></option>
                            <%}%>
                        </select>

                    </td>
                </tr>
                
                <tr>
                    <td><input type="submit" value="Update Orders" name="submit"></td>
                    <td><input type="reset" value="Clear"></td>
                </tr>
            </table>
        </form> 
    </body>
</html>