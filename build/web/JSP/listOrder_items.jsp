<%-- 
    Document   : listOrder_items
    Created on : Oct 22, 2024, 8:52:22 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Order_items" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
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
    <body>
        <% Vector<Order_items> vector= (Vector<Order_items>)request.getAttribute("Order_itemsData");
           String title=(String)request.getAttribute("tableTitle");     
        %>
        <form action="Order_itemsURL" method="get">
            <input type="hidden" name="service" value="listAllOrder_items">
            <p>Search by Order ID:<input type="text" name="oid" id="">
                <input type="submit" value="Search" name="submit">
                <input type="reset" value="Clear">
            </p>
        </form>
        <p><a href="Order_itemsURL?service=insertOrder_items">Insert Order Ítems</a></p>
        <table border="1">
            <caption>
                <h1><%=title%></h1>
            </caption>
            <tr>
                <th>Order ID</th>
                <th>Item ID</th>
                <th>Product ID</th>
                <th>Quantity</th>
                <th>List Price</th>
                <th>Discount</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            <% for(Order_items oitem:vector){%>

            <tr>
                <td><%=oitem.getOrder_id()%></td>
                <td><%=oitem.getItem_id()%></td>
                <td><%=oitem.getProduct_id()%></td>
                <td><%=oitem.getQuantity()%></td>
                <td><%=oitem.getList_price()%></td>
                <td><%=oitem.getDiscount()%></td>

                <td><a href="Order_itemsURL?service=updateOrder_items&oid=<%=oitem.getOrder_id()%>&iid=<%=oitem.getItem_id()%>">Update</a></td>
                <td><a href="Order_itemsURL?service=deleteOrder_items&oid=<%=oitem.getOrder_id()%>&iid=<%=oitem.getItem_id()%>">Delete</a></td>
            </tr>
            <%}%>
        </table>
    </body>
</html>
