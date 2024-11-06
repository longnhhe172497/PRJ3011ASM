<%-- 
    Document   : insertOrder_items
    Created on : Oct 22, 2024, 8:51:58 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>insertOrder_items</title>
    </head>
    <body>
        <% ResultSet rsOid=(ResultSet)request.getAttribute("rsOid");
         
        %>
        <form action="Order_itemsURL" method="post">
            <input type="hidden" name="service" value="insertOrder_items">
            <table>

                <caption>
                    <h1>Insert Order Items</h1>
                </caption>

                <tr>
                    <td><label for="order_id">Order ID</label></td>
                    <td>
                        <select name="order_id" id="order_id">
                            <%while(rsOid.next()){%>
                            <option value="<%=rsOid.getInt(1)%>"><%=rsOid.getInt(1)%></option>
                            <%}%>
                        </select>

                    </td>
                </tr>
                <tr>
                    <td><label for="item_id">Item ID</label></td>
                    <td><input type="text" name="item_id" id="item_id" required></td>
                </tr>
                <tr>
                    <td><label for="product_id">Product ID</label></td>
                    <td><input type="text" name="product_id" id="product_id" required></td>
                </tr>
                <tr>
                    <td><label for="quantity">Quantity </label></td>
                    <td><input type="text" name="quantity" id="quantity"></td>
                </tr>
                <tr>
                    <td><label for="list_price">List Price</label></td>
                    <td><input type="text" name="list_price" id="list_price"></td>
                </tr>
                <tr>
                    <td><label for="discount">Discount</label></td>
                    <td><input type="text" name="discount" id="discount"></td>
                </tr>

                <tr>
                    <td><input type="submit" value="Insert Order Items" name="submit"></td>
                    <td><input type="reset" value="Clear"></td>
                </tr>
            </table>
        </form> 
    </body>
</html>
