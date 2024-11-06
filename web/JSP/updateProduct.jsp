<%-- 
    Document   : updateProduct
    Created on : Oct 8, 2024, 10:34:58 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Products" %>
<%@page import="java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>updateProduct</title>
    </head>
    <body>
        <% Vector<Products> vector= (Vector<Products>)request.getAttribute("vector");
           
          Products product=vector.get(0);
          ResultSet rsCate=(ResultSet)request.getAttribute("rsCate");
          ResultSet rsBrand=(ResultSet)request.getAttribute("rsBrand");
        %>

        <form action="ProductURL" method="post">
            <input type="hidden" name="service" value="updateProduct">
            <table>
                <caption>
                    <h1>Update Product</h1>
                </caption>

                <tr>
                    <td><label for="product_id">Product ID</label></td>
                    <td><input type="text" name="product_id" id="product_id" readonly value="<%=product.getProduct_id()%>"></td>
                </tr>
                <tr>
                    <td><label for="product_name">Product Name</label></td>
                    <td><input type="text" name="product_name" id="product_name" value="<%=product.getProduct_name()%>"></td>
                </tr>
                <tr>
                    <td><label for="model_year">Model Year</label></td>
                    <td><input type="text" name="model_year" id="model_year" value="<%=product.getModel_year()%>"></td>
                </tr>
                <tr>
                    <td><label for="list_price">List Price</label></td>
                    <td><input type="text" name="list_price" id="list_price" value="<%=product.getList_price()%>"></td>
                </tr>
                <tr>
                    <td><label for="brand_name">Brand Name</label></td>
                    <td>
                        <select name="brand_name" id="brand_name">
                            <%while(rsBrand.next()){%>
                            <option value="<%=rsBrand.getString(1)%>"><%=rsBrand.getString(1)%></option>
                            <%}%>

                        </select>

                    </td>
                </tr>
                <tr>
                    <td><label for="category_name">Category Name</label></td>
                    <td>
                        <select name="category_name" id="category_name">
                            <%while(rsCate.next()){%>
                            <option value="<%=rsCate.getString(1)%>"><%=rsCate.getString(1)%></option>
                            <%}%>
                        </select>

                    </td>
                </tr>
                <tr>
                    <td><label for="product_status">Product Status</label></td>
                    <td>
                        <select name="product_status" id="product_status">
                            <option value="1">Enable</option>
                            <option value="0">Disable</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="Update Product" name="submit"></td>
                    <td><input type="reset" value="Clear"></td>
                </tr>
            </table>
        </form> 
    </body>
</html>
