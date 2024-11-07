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
           String error = (String)request.getAttribute("error");
        %>
        <div class="container">
            <% if(error != null) { %>
                <div class="error-message">
                    <%= error %>
                </div>
            <% } %>
            <form action="CustomerURL" method="post">
                <input type="hidden" name="service" value="insertCustomer">
                <table>
                    <caption>
                        <h1>Register Customer Account</h1>
                    </caption>
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
            </form>
        </div>
    </body>
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            margin: 0;
            padding: 20px;
            background: #f5f5f5;
            color: #2d2d2d;
            min-height: 100vh;
        }

        .container {
            margin: 20px auto;
            max-width: 800px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.1);
            border-radius: 15px;
            overflow: hidden;
            background: #ffffff;
            padding: 30px;
        }

        h1 {
            color: #3a3a3a;
            font-size: 1.8em;
            padding-bottom: 15px;
            border-bottom: 3px solid #8b7355;
            text-transform: uppercase;
            letter-spacing: 2px;
            text-align: center;
            margin-top: 0;
        }

        table {
            width: 100%;
            border-collapse: separate;
            border-spacing: 0;
            margin-top: 25px;
            box-shadow: 0 5px 20px rgba(0,0,0,0.05);
            border-radius: 10px;
            overflow: hidden;
        }

        td {
            padding: 15px;
            text-align: center;
        }

        input[type="text"], select {
            padding: 12px 20px;
            border: 2px solid #e0e0e0;
            border-radius: 10px;
            font-size: 1.1em;
            width: 250px;
            background: white;
        }

        input[type="text"]:focus, select:focus {
            outline: none;
            border-color: #8b7355;
            box-shadow: 0 0 0 3px rgba(139,115,85,0.1);
        }

        input[type="submit"], input[type="reset"] {
            padding: 12px 25px;
            background: #8b7355;
            color: white;
            border: none;
            border-radius: 10px;
            cursor: pointer;
            font-weight: 600;
            font-size: 1em;
            text-transform: uppercase;
            letter-spacing: 1px;
        }

        input[type="submit"]:hover, input[type="reset"]:hover {
            background: #6d5a43;
        }

        label {
            color: #4a4a4a;
            font-weight: 500;
            font-size: 1.1em;
        }

        .error-message {
            background-color: #ffe6e6;
            color: #ff0000;
            padding: 15px;
            margin-bottom: 20px;
            border-radius: 10px;
            border: 1px solid #ff9999;
            text-align: center;
            font-weight: 500;
        }
    </style>
</html>