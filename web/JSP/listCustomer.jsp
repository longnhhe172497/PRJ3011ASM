<%-- 
    Document   : listCustomer
    Created on : Oct 8, 2024, 11:29:33 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Customer" %>

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
        <% Vector<Customer> vector= (Vector<Customer>)request.getAttribute("customerData");
           String title=(String)request.getAttribute("tableTitle");     
        %>
        <%Customer customer=(Customer)session.getAttribute("customer");%>
        <p align="right">
            <%if(customer!=null){%>
            <%= "Welcome " + customer.getEmail() %>

            <a href="/PRJ3011ASM/CustomerURL?service=logoutCustomer">Logout</a>

            <%}else{%>
            <a href="/PRJ3011ASM/CustomerURL?service=loginCustomer">Login</a>
            <%}%> 
        </p>
        <form action="CustomerURL" method="get">
            <input type="hidden" name="service" value="listAllCustomer">
            <p>Search by Name:<input type="text" name="cname" id="">
                <input type="submit" value="Search" name="submit">
                <input type="reset" value="Clear">
            </p>
        </form>
        <p><a href="/PRJ3011ASM/CustomerURL?service=insertCustomer">Insert Customer</a></p>
        <table border="1">
           <caption>
                <h1><%=title%></h1>
           </caption>
            <tr>
                <th>Customer ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Phone</th>
                <th>Email</th>
                <th>Street</th>
                <th>City</th>
                <th>State</th>
                <th>Zip Code</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            <% for(Customer cus:vector){%>

            <tr>
                <td><%=cus.getCustomer_id()%></td>
                <td><%=cus.getFirst_name()%></td>
                <td><%=cus.getLast_name()%></td>
                <td><%=cus.getPhone()%></td>
                <td><%=cus.getEmail()%></td>
                <td><%=cus.getStreet()%></td>
                <td><%=cus.getCity()%></td>
                <td><%=cus.getState()%></td>
                <td><%=cus.getZip_code()%></td>

                <td><a href="CustomerURL?service=updateCustomer&cid=<%= cus.getCustomer_id() %>">Update</a></td>
                <td><a href="CustomerURL?service=deleteCustomer&cid=<%= cus.getCustomer_id() %>">Delete</a></td>

            </tr>
            <%}%>
        </table>
    </body>
</html>
