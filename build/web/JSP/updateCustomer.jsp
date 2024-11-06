<%-- 
    Document   : updateCustomer
    Created on : Oct 8, 2024, 11:24:31 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Customer" %>
<%@page import="java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Customer</title>
    </head>
    <body>
       <% Vector<Customer> vector= (Vector<Customer>)request.getAttribute("vector");
           
          Customer customer=vector.get(0);
          ResultSet rsState=(ResultSet)request.getAttribute("rsState");
        %>
        

        <form action="CustomerURL" method="post">
            <input type="hidden" name="service" value="updateCustomer">
            <table>
                <caption>
                    <h1>Update Customer</h1>
                </caption>
              
                <tr>
                    <td><label for="customer_id">Customer ID</label></td>
                    <td><input type="text" name="customer_id" id="customer_id" readonly value="<%=customer.getCustomer_id()%>"></td>
                </tr>
                <tr>
                    <td><label for="first_name">First Name</label></td>
                    <td><input type="text" name="first_name" id="first_name" value="<%=customer.getFirst_name()%>"></td>
                </tr>
                <tr>
                    <td><label for="product_name">Last Name</label></td>
                    <td><input type="text" name="last_name" id="last_name" value="<%=customer.getLast_name()%>"></td>
                </tr>
                <tr>
                    <td><label for="phone">Phone</label></td>
                    <td><input type="text" name="phone" id="phone" value="<%=customer.getPhone()%>"></td>
                </tr>
                <tr>
                    <td><label for="email">Email</label></td>
                    <td><input type="text" name="email" id="email" value="<%=customer.getEmail()%>"></td>
                </tr>
                <tr>
                    <td><label for="street">Street</label></td>
                    <td><input type="text" name="street" id="street" value="<%=customer.getStreet()%>"></td>
                </tr>
                <tr>
                    <td><label for="city">City</label></td>
                    <td><input type="text" name="city" id="city" value="<%=customer.getCity()%>"></td>
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
                    <td><input type="text" name="zip_code" id="zip_code" value="<%=customer.getZip_code()%>"></td>
                </tr>

                <tr>
                    <td><input type="submit" value="Update Customer" name="submit"></td>
                    <td><input type="reset" value="Clear"></td>
                </tr>
            </table>
        </form> 
    </body>
</html>
