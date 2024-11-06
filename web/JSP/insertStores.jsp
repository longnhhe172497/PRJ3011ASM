<%-- 
    Document   : insertStores
    Created on : Oct 30, 2024, 4:04:44 PM
    Author     : HOANG LONG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert Stores</title>
    </head>
    <body>
        <% ResultSet rsCity=(ResultSet)request.getAttribute("rsCity");
          ResultSet rsState=(ResultSet)request.getAttribute("rsState");
        %>
        <form action="StoresURL" method="post">
            <input type="hidden" name="service" value="insertStores">
            <table>
                <caption>
                    <h1>Insert Stores</h1>
                </caption>

                <tr>
                    <td><label for="store_id">Store ID</label></td>
                    <td><input type="text" name="store_id" id="store_id" required></td>
                </tr>
                <tr>
                    <td><label for="store_name">Stores Name</label></td>
                    <td><input type="text" name="store_name" id="store_name" required></td>
                </tr>
                <tr>
                    <td><label for="phone">Phone</label></td>
                    <td><input type="text" name="phone" id="phone"></td>
                </tr>
                <tr>
                    <td><label for="email">Email</label></td>
                    <td><input type="text" name="email" id="email"></td>
                </tr>
                <tr>
                    <td><label for="street">Street</label></td>
                    <td><input type="text" name="street" id="street"></td>
                </tr>
                <tr>
                    <td><label for="city">City</label></td>
                    <td>
                        <select name="city" id="city">
                            <%while(rsCity.next()){%>
                            <option value="<%=rsCity.getString(1)%>"><%=rsCity.getString(1)%></option>
                            <%}%>

                        </select>

                    </td>
                </tr>
                <tr>
                    <td><label for="state">State</label></td>
                    <td>
                        <select name="state" id="state">
                            <%while(rsState.next()){%>
                            <option value="<%=rsState.getString(1)%>"><%=rsState.getString(1)%></option>
                            <%}%>
                        </select>

                    </td>
                </tr>

                <tr>
                    <td><label for="zip_code">Zip Code</label></td>
                    <td><input type="text" name="zip_code" id="zip_code"></td>
                </tr>

                <tr>
                    <td><input type="submit" value="Insert Stores" name="submit"></td>
                    <td><input type="reset" value="Clear"></td>
                </tr>
            </table>
        </form> 
    </body>
</html>
