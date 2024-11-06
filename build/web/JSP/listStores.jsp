<%-- 
    Document   : listStores
    Created on : Oct 30, 2024, 4:06:43 PM
    Author     : HOANG LONG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Stores" %>

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
        <% Vector<Stores> vector= (Vector<Stores>)request.getAttribute("storesData");
           String title=(String)request.getAttribute("tableTitle");     
        %>
        <form action="StoresURL" method="get">
            <input type="hidden" name="service" value="listAllStores">
            <p>Search by Name:<input type="text" name="sname" id="">
                <input type="submit" value="Search" name="submit">
                <input type="reset" value="Clear">
            </p>
        </form>
        <p><a href="StoresURL?service=insertStores">Insert Stores</a></p>

        <table border="1">
            <caption>
                <h1><%=title%></h1>
            </caption>
            <tr>
                <th>Store ID</th>
                <th>Store Name</th>
                <th>Phone</th>
                <th>Email</th>
                <th>Street</th>
                <th>City</th>
                <th>State</th>
                <th>Zip Code</th>
                <th>Update</th>
                <th>Delete</th>

            </tr>
            <% for(Stores stores:vector){%>

            <tr>
                <td><%=stores.getStore_id()%></td>
                <td><%=stores.getStore_name()%></td>
                <td><%=stores.getPhone()%></td>
                <td><%=stores.getEmail()%></td>
                <td><%=stores.getStreet()%></td>
                <td><%=stores.getCity()%></td>
                <td><%=stores.getState()%></td>
                <td><%=stores.getZip_code()%></td>
                <td><a href="StoresURL?service=updateStores&sid=<%=stores.getStore_id()%>">Update</a></td>
                <td><a href="StoresURL?service=deleteStores&sid=<%=stores.getStore_id()%>">Delete</a></td>

            </tr>
            <%}%>
        </table>
    </body>
</html>
