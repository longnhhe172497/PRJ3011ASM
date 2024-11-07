<%-- 
    Document   : listStaffs
    Created on : Oct 22, 2024, 7:57:37 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Staffs" %>

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
        <% Vector<Staffs> vector= (Vector<Staffs>)request.getAttribute("staffsData");
           String title=(String)request.getAttribute("tableTitle");     
        %>
        <%Staffs staffs=(Staffs)session.getAttribute("staffs");%>
        
        <form action="StaffsURL" method="get">
            <input type="hidden" name="service" value="listAllStaffs">
            <p>Search by Name:<input type="text" name="sname" id="">
                <input type="submit" value="Search" name="submit">
                <input type="reset" value="Clear">
            </p>
        </form>
        <p><a href="/PRJ3011ASM/JSP/homeManager.jsp">Back to Home Manager</a></p>
        <p><a href="StaffsURL?service=insertStaffs">Insert Staffs</a></p>
        <table border="1">
            <caption>
            <h1><%=title%></h1>
            </caption>
            <tr>
                <th>Staff ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Active</th>
                <th>Store ID</th>
                <th>Manager ID</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            <% for(Staffs staff:vector){%>

            <tr>
                <td><%=staff.getStaff_id()%></td>
                <td><%=staff.getFirst_name()%></td>
                <td><%=staff.getLast_name()%></td>
                <td><%=staff.getEmail()%></td>
                <td><%=staff.getPhone()%></td>
                <td><%=staff.getActive()%></td>
                <td><%=staff.getStore_id()%></td>
                <td><%=staff.getManager_id()%></td>

                <td><a href="StaffsURL?service=updateStaffs&sid=<%= staff.getStaff_id() %>">Update</a></td>
                <td><a href="StaffsURL?service=deleteStaffs&sid=<%= staff.getStaff_id() %>">Delete</a></td>

            </tr>
            <%}%>
        </table>
    </body>
</html>

