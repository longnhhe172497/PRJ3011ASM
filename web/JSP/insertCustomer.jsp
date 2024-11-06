<%-- 
    Document   : insertCustomer
    Created on : Oct 8, 2024, 11:20:44 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert Customer</title>
    </head>
    <body>
        <% ResultSet rsState=(ResultSet)request.getAttribute("rsState");
          
        %>
        <form action="CustomerURL" method="post">
            <input type="hidden" name="service" value="insertCustomer">
            <center>
                <table>
                    <caption>
                        <h1>Register Customer Account</h1>
                    </caption>
                    <tr>
                        <td><label for="customer_id">Customer ID</label></td>
                        <td><input type="text" name="customer_id" id="customer_id" required></td>
                    </tr>
                    <tr>
                        <td><label for="first_name">First Name</label></td>
                        <td><input type="text" name="first_name" id="first_name" required></td>
                    </tr>
                    <tr>
                        <td><label for="last_name">Last Name</label></td>
                        <td><input type="text" name="last_name" id="last_name" required></td>
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
                        <td><input type="text" name="city" id="city"></td>
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
                        <td><input type="submit" value="Insert Customer" name="submit"></td>
                        <td><input type="reset" value="Clear"></td>
                    </tr>
                </table>
            </center>
        </form> 
    </body>
</html>