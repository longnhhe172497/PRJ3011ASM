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
        <a href="ProductURL" style="font-size: 1,5em; position: fixed; top: 20px; left: 20px; color: white;">Back To Home</a>
        <div class="login-container">
            <h2>Login Staff</h2>
            <% String error=(String)request.getAttribute("error");%>
            <% if(error != null) { %>
            <div class="error-message">
                <p style="color:red; text-align:center; margin-bottom:15px;">
                    <%= error %>
                </p>
            </div>
            <% } %>
            <form action="StaffsURL" method="post">
                <input type="hidden" name="service" value="loginStaffs">
                <label for="username">Username</label>
                <input type="text" name="username" id="username" required>

                <label for="password">Password</label>
                <input type="password" name="password" id="password" required>
                
                
                <div style="display: flex; justify-content: space-between;">
                    <input type="submit" value="Login" name="submit">
                    <input type="reset" value="Clear">

                </div>
            </form>
        </div>
    </body>
    <style>
        body, html {
            height: 100%;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            font-family: 'Poppins', sans-serif;
            background: linear-gradient(135deg, #8b7355, #6d5a43);
        }

        .login-container {
            width: 400px;
            padding: 40px;
            border-radius: 15px;
            background-color: rgba(255, 255, 255, 0.95);
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
        }

        .login-container h2 {
            text-align: center;
            font-size: 2em;
            color: #3a3a3a;
            margin-bottom: 30px;
            font-weight: 600;
        }

        .login-container input[type="text"],
        .login-container input[type="password"] {
            width: 100%;
            padding: 15px;
            margin: 15px 0;
            border: 2px solid #e0e0e0;
            border-radius: 8px;
            box-sizing: border-box;
            font-size: 1em;
            transition: all 0.3s ease;
        }

        .login-container input[type="text"]:focus,
        .login-container input[type="password"]:focus {
            outline: none;
            border-color: #8b7355;
            box-shadow: 0 0 0 3px rgba(139,115,85,0.1);
        }

        .login-container input[type="submit"],
        .login-container input[type="reset"] {
            width: 48%;
            padding: 12px;
            margin-top: 20px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            font-weight: 600;
            font-size: 1em;
            transition: all 0.3s ease;
        }

        .login-container input[type="submit"] {
            background-color: #8b7355;
            color: white;
        }

        .login-container input[type="reset"] {
            background-color: #f8f8f8;
            color: #4a4a4a;
        }

        .login-container input[type="submit"]:hover {
            background-color: #6d5a43;
        }

        .login-container input[type="reset"]:hover {
            background-color: #e0e0e0;
        }

    </style>
</html>