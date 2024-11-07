<%-- 
    Document   : listProduct
    Created on : Oct 4, 2024, 8:37:31 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Products" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% Vector<Products> vector= (Vector<Products>)request.getAttribute("productData");
           String title=(String)request.getAttribute("tableTitle");     
        %>
        <form action="ProductURL" method="get">
            <input type="hidden" name="service" value="listAllProducts">
            <p>Search by Name:<input type="text" name="pname" id="">
                <input type="submit" value="Search" name="submit">
                <input type="reset" value="Clear">
            </p>
        </form>
        <p><a href="/PRJ3011ASM/JSP/homeManager.jsp">Back to Home Manager</a></p>
        <p><a href="ProductURL?service=insertProduct">Insert Product</a></p>
        <table border="1">
            <caption>
                <h1><%=title%></h1>
            </caption>
            <tr>
                <th>Product ID</th>
                <th>Product Name</th>
                <th>Model Year</th>
                <th>List Price</th>
                <th>Brand Name</th>
                <th>Brand Name</th>
                <th>Product Status</th>
                <th>Update</th> 
                <th>Delete</th>
            </tr>
            <% for(Products product:vector){%>

            <tr>
                <td><%=product.getProduct_id()%></td>
                <td><%=product.getProduct_name()%></td>
                <td><%=product.getModel_year()%></td>
                <td><%=product.getList_price()%></td>
                <td><%=product.getBrand_name()%></td>
                <td><%=product.getCategory_name()%></td>
                <td><%=(product.getProduct_status()==1?"Enable":"Disable")%></td>
                <td><a href="ProductURL?service=updateProduct&pid=<%=product.getProduct_id()%>">Update</a></td>
                <td><a href="ProductURL?service=deleteProduct&pid=<%=product.getProduct_id()%>">Delete</a></td>
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



