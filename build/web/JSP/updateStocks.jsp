<%-- 
    Document   : updateStocks
    Created on : Oct 31, 2024, 4:58:55 PM
    Author     : HOANG LONG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Stocks" %>
<%@page import="java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Stocks</title>
    </head>
    <body>
        <% Vector<Stocks> vector= (Vector<Stocks>)request.getAttribute("vector");
           
          Stocks stocks=vector.get(0);
         
        %>

        <form action="StocksURL" method="post">
            <input type="hidden" name="service" value="updateStocks">
            <table>
                <caption>
                    <h1>Update Stocks</h1>
                </caption>
                <tr>
                    <td><label for="store_id">Store ID</label></td>
                    <td><input type="text" name="store_id" id="store_id" readonly value="<%=stocks.getStore_id()%>"></td>
                </tr>
                <tr>
                    <td><label for="product_id">Product ID</label></td>
                    <td><input type="text" name="product_id" id="product_id" readonly value="<%=stocks.getProduct_id()%>"></td>
                </tr>
                <tr>
                    <td><label for="quantity">Quantity</label></td>
                    <td><input type="text" name="quantity" id="quantity" value="<%=stocks.getQuantity()%>"></td>
                </tr>
                
                
                <tr>
                    <td><input type="submit" value="Update Stocks" name="submit"></td>
                    <td><input type="reset" value="Clear"></td>
                </tr>
            </table>
        </form> 
    </body>
</html>
