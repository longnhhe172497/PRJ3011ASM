<%-- 
    Document   : insertStocks
    Created on : Oct 30, 2024, 4:04:36 PM
    Author     : HOANG LONG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert Stocks</title>
    </head>
    <body>

        <form action="StocksURL" method="post">
            <input type="hidden" name="service" value="insertStocks">
            <table>
                <caption>
                    <h1>Insert Stocks</h1>
                </caption>

                <tr>
                    <td><label for="store_id">Store ID</label></td>
                    <td><input type="text" name="store_id" id="store_id" required></td>
                </tr>
                <tr>
                    <td><label for="product_id">Product ID</label></td>
                    <td><input type="text" name="product_id" id="product_id" required></td>
                </tr>
                <tr>
                    <td><label for="quantity">Quantity</label></td>
                    <td><input type="text" name="quantity" id="quantity" required></td>
                </tr>

                <tr>
                    <td><input type="submit" value="Insert Stocks" name="submit"></td>
                    <td><input type="reset" value="Clear"></td>
                </tr>
            </table>
        </form> 
    </body>
</html>
