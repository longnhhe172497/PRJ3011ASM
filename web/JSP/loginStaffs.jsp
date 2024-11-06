<%-- 
    Document   : loginStaffs
    Created on : Oct 22, 2024, 7:57:07 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <% String error=(String)request.getAttribute("error");%>
        <%=(error!=null?error:"")%>
        <form action="StaffsURL" method="post">
            <input type="hidden" name="service" value="loginStaffs">
            <table>
                <caption>
                    <h1>Login Staff</h1>
                </caption>
                <tr>
                    <td><label for="username">Username</label></td>
                    <td><input type="text" name="username" id="username" required></td>
                </tr>
                <tr>
                    <td><label for="password">Password</label></td>
                    <td><input type="text" name="password" id="password" required></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Login" name="submit"></td>
                    <td><input type="reset" value="Clear"></td>
                </tr>
            </table>
        </form> 
    </body>
</html>
