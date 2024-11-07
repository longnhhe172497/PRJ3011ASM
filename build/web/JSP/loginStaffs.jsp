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
        <title>Login Staff</title>
        <link rel="stylesheet" type="text/css" href="css/login.css">
    </head>
    <body>
        <div class="login-container">
            <h2>Login Staff</h2>
            <% String error=(String)request.getAttribute("error");%>
            <p style="color:red;"><%=(error!=null?error:"")%></p> <!-- Display error message if any -->
            <form action="StaffsURL" method="post">
                <input type="hidden" name="service" value="loginStaffs">
                <label for="username">Username</label>
                <input type="text" name="username" id="username" required>
                
                <label for="password">Password</label>
                <input type="password" name="password" id="password" required>
                
                <div>
                    <input type="submit" value="Login" name="submit">
                    <input type="reset" value="Clear">
                </div>
            </form>
        </div>
    </body>
</html>