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
        <link rel="stylesheet" type="text/css" href="css/home.css">
    </head>
    
    <%@include file="/JSP/panner.jsp" %>
    
    <body>
        <% if (customer != null) { %>
            <% Vector<Products> productVector= (Vector<Products>)request.getAttribute("productData");
               String tableTitle =(String)request.getAttribute("tableTitle");     
               Vector<String> categories = (Vector<String>) request.getAttribute("categories");
            %>
            <div class="container">
                <div class="sidebar">
                    <h3>Categories</h3>
                    <ul>
                        <% for (String category : categories) { %>
                        <li><a href="ProductURL?category=<%= category %>"><%= category %></a></li>
                            <% } %>
                    </ul>                      
                </div>
                <div class="product-list">
                    <form action="ProductURL" method="get">
                        <input type="hidden" name="service" value="listAllProducts">
                        <div class="search">
                        <p>Search by Name:<input type="text" name="pname" id="">
                            <input type="submit" value="Search" name="submit">
                            <input type="reset" value="Clear">
                        </p>
                        </div>
                    </form>
                    <table border="1">
                        <caption>
                            <h1><%=tableTitle %></h1>
                        </caption>
                        <tr>
                            <th>Product Name</th>
                            <th>Model Year</th>
                            <th>List Price</th>
                            <th>Brand Name</th>
                            <th>Category Name</th>
                            <th>Product Status</th>
                            <th>Add To Cart</th>
                        </tr>
                        <% for(Products product : productVector){%>

                        <tr>
                            <td><%=product.getProduct_name()%></td>
                            <td><%=product.getModel_year()%></td>
                            <td><%=product.getList_price()%></td>
                            <td><%=product.getBrand_name()%></td>
                            <td><%=product.getCategory_name()%></td>
                            <td><%=(product.getProduct_status()==1?"Enable":"Disable")%></td>
                            <td><button onclick="window.location.href='CartURL?service=add2Cart&pid=<%=product.getProduct_id()%>'">Add</button></td>
                        </tr>
                        <%}%>
                    </table>            
                </div>
            </div>
        <% } %>
    </body>
</html>
