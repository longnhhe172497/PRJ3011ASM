<%-- 
    Document   : listOrders
    Created on : Oct 29, 2024, 9:29:30 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Orders" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% Vector<Orders> vector= (Vector<Orders>)request.getAttribute("ordersData");
           String title=(String)request.getAttribute("tableTitle");     
        %>
        <form action="OrdersURL" method="get">
            <input type="hidden" name="service" value="listAllOrders">
            <p>Search by Order ID:<input type="text" name="oid" id="">
                <input type="submit" value="Search" name="submit">
                <input type="reset" value="Clear">
            </p>
        </form>
        <p><a href="/PRJ3011ASM/JSP/homeManager.jsp">Back to Home Manager</a></p>
        <p><a href="OrdersURL?service=insertOrders">Insert Orders</a></p>

        <table border="1">
            <caption>
                <h1><%=title%></h1>
            </caption>
            <tr>
                <th>Order ID</th>
                <th>Customer ID</th>
                <th>Order Status</th>
                <th>Order Date</th>
                <th>Required Date</th>
                <th>Shipped Date</th>
                <th>Store ID</th>
                <th>Staff ID</th>
                <th>Update</th>
                <th>Delete</th>

            </tr>
            <% for(Orders order:vector){%>

            <tr>
                <td><%=order.getOrder_id()%></td>
                <td><%=order.getCustomer_id()%></td>
                <td><%=order.getOrder_status()%></td>
                <td><%=order.getOrder_date()%></td>
                <td><%=order.getRequired_date()%></td>
                <td><%=order.getShipped_date()%></td>
                <td><%=order.getStore_id()%></td>
                <td><%=order.getStaff_id()%></td>
                <td><a href="OrdersURL?service=updateOrders&oid=<%=order.getOrder_id()%>">Update</a></td>
                <td><a href="OrdersURL?service=deleteOrders&oid=<%=order.getOrder_id()%>">Delete</a></td>

            </tr>
            <%}%>
        </table>
        <style>
            table {
                width: 100%;
                border-collapse: collapse;
            }
            th, td {
                border: 1px solid black;
                padding: 10px;
                text-align: center;
            }
        </style>
    </body>
</html>
