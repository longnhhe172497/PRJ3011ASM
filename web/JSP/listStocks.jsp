<%-- 
    Document   : listStocks
    Created on : Oct 30, 2024, 4:06:51 PM
    Author     : HOANG LONG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Stocks" %>

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
        <% Vector<Stocks> vector= (Vector<Stocks>)request.getAttribute("stocksData");
           String title=(String)request.getAttribute("tableTitle");     
        %>
        <form action="StocksURL" method="get">
            <input type="hidden" name="service" value="listAllStocks">
            <p>Search by store ID:<input type="text" name="sid" id="">
                <input type="submit" value="Search" name="submit">
                <input type="reset" value="Clear">
            </p>
        </form>
        <p><a href="StocksURL?service=insertStocks">Insert Stocks</a></p>
        <table border="1">
            <caption>
                <h1><%=title%></h1>
            </caption>
            <tr>
                <th>Store ID</th>
                <th>Product ID</th>
                <th>Quantity</th>
                <th>Update</th>
                <th>Delete</th>

            </tr>
            <% for(Stocks stocks:vector){%>

            <tr>
                <td><%=stocks.getStore_id()%></td>
                <td><%=stocks.getProduct_id()%></td>
                <td><%=stocks.getQuantity()%></td>

                <td><a href="StocksURL?service=updateStocks&sid=<%=stocks.getStore_id()%>&pid=<%=stocks.getProduct_id()%>">Update</a></td>
                <td><a href="StocksURL?service=deleteStocks&sid=<%=stocks.getStore_id()%>&pid=<%=stocks.getProduct_id()%>">Delete</a></td>

            </tr>
            <%}%>
        </table>
    </body>
</html>
