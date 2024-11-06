<%-- 
    Document   : updateOrder_items
    Created on : Oct 22, 2024, 8:52:33 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Order_items" %>
<%@page import="java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Order Items</title>
    </head>
    <body>
        <% Vector<Order_items> vector= (Vector<Order_items>)request.getAttribute("vector");
           
          Order_items oitem=vector.get(0);
         
        %>

        <form action="Order_itemsURL" method="post">
            <input type="hidden" name="service" value="updateOrder_items">
            <table>
                <caption>
                    <h1>Update Order Items</h1>
                </caption>

                <tr>
                    <td><label for="order_id">Order ID</label></td>
                    <td><input type="text" name="order_id" id="order_id" readonly value="<%=oitem.getOrder_id()%>"></td>
                </tr>
                <tr>
                    <td><label for="item_id">Item ID</label></td>
                    <td><input type="text" name="item_id" id="item_id" readonly value="<%=oitem.getItem_id()%>"></td>
                </tr>
                <tr>
                    <td><label for="product_id">Product ID</label></td>
                    <td><input type="text" name="product_id" id="product_id" value="<%=oitem.getProduct_id()%>"></td>
                </tr>
                <tr>
                    <td><label for="quantity">Quantity</label></td>
                    <td><input type="text" name="quantity" id="quantity" value="<%=oitem.getQuantity()%>"></td>
                </tr>
                
                <tr>
                    <td><label for="list_price">List Price</label></td>
                    <td><input type="text" name="list_price" id="list_price" value="<%=oitem.getList_price()%>"></td>
                </tr>
                <tr>
                    <td><label for="discount">Discount</label></td>
                    <td><input type="text" name="discount" id="discount" value="<%=oitem.getDiscount()%>"></td>
                </tr>
                
                
                <tr>
                    <td><input type="submit" value="Update Order Items" name="submit"></td>
                    <td><input type="reset" value="Clear"></td>
                </tr>
            </table>
        </form> 
    </body>
</html>

