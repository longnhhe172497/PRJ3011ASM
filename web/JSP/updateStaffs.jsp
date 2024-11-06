<%-- 
    Document   : updateStaffs
    Created on : Oct 22, 2024, 7:57:52 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Staffs" %>
<%@page import="java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Staffs</title>
    </head>
    <body>
       <% Vector<Staffs> vector= (Vector<Staffs>)request.getAttribute("vector");
           
          Staffs staffs=vector.get(0);
          ResultSet rsActive=(ResultSet)request.getAttribute("rsActive");
          ResultSet rsStore=(ResultSet)request.getAttribute("rsStore");
          ResultSet rsManager=(ResultSet)request.getAttribute("rsManager");
        %>
        

        <form action="StaffsURL" method="post">
            <input type="hidden" name="service" value="updateStaffs">
            <table>
                <caption>Update Staffs</caption>
                <tr>
                    <td><label for="staff_id">Staff ID</label></td>
                    <td><input type="text" name="staff_id" id="staff_id" readonly value="<%=staffs.getStaff_id()%>"></td>
                </tr>
                <tr>
                    <td><label for="first_name">First name</label></td>
                    <td><input type="text" name="first_name" id="first_name" value="<%=staffs.getFirst_name()%>"></td>
                </tr>
                <tr>
                    <td><label for="product_name">Last name</label></td>
                    <td><input type="text" name="last_name" id="last_name" value="<%=staffs.getLast_name()%>"></td>
                </tr>
                <tr>
                    <td><label for="email">Email</label></td>
                    <td><input type="text" name="email" id="email" value="<%=staffs.getEmail()%>"></td>
                </tr>
                <tr>
                    <td><label for="phone">Phone</label></td>
                    <td><input type="text" name="phone" id="phone" value="<%=staffs.getPhone()%>"></td>
                </tr>
                <tr>
                    <td><label for="active">Active</label></td>
                    <td>
                        <select name="active" id="active">
                            <%while(rsActive.next()){%>
                            <option value="<%=rsActive.getInt(1)%>"><%=rsActive.getInt(1)%></option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="store_id">Store ID</label></td>
                    <td>
                        <select name="store_id" id="store_id">
                            <%while(rsStore.next()){%>
                            <option value="<%=rsStore.getInt(1)%>"><%=rsStore.getInt(1)%></option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label for="manager_id">Manager ID</label></td>
                    <td>
                        <select name="manager_id" id="manager_id">
                            <%while(rsManager.next()){%>
                            <option value="<%=rsManager.getInt(1)%>"><%=rsManager.getInt(1)%></option>
                            <%}%>

                        </select>

                    </td>
                </tr>
                

                <tr>
                    <td><input type="submit" value="Update Staffs" name="submit"></td>
                    <td><input type="reset" value="Clear"></td>
                </tr>
            </table>
        </form> 
    </body>
</html>