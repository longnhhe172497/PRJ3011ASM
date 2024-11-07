<%-- 
    Document   : insertOrder_items
    Created on : Oct 22, 2024, 8:51:58 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>insertOrder_items</title>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap" rel="stylesheet">
    </head>
    <body>
        <% ResultSet rsOid=(ResultSet)request.getAttribute("rsOid");
         
        %>
        <div class="container">
            <form action="Order_itemsURL" method="post">
                <input type="hidden" name="service" value="insertOrder_items">
                <table>
                    <caption>
                        <h1>Insert Order Items</h1>
                    </caption>
                    <tr>
                        <td><label for="order_id">Order ID</label></td>
                        <td>
                            <select name="order_id" id="order_id">
                                <%while(rsOid.next()){%>
                                <option value="<%=rsOid.getInt(1)%>"><%=rsOid.getInt(1)%></option>
                                <%}%>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><label for="item_id">Item ID</label></td>
                        <td><input type="text" name="item_id" id="item_id" required></td>
                    </tr>
                    <tr>
                        <td><label for="product_id">Product ID</label></td>
                        <td><input type="text" name="product_id" id="product_id" required></td>
                    </tr>
                    <tr>
                        <td><label for="quantity">Quantity </label></td>
                        <td><input type="text" name="quantity" id="quantity"></td>
                    </tr>
                    <tr>
                        <td><label for="list_price">List Price</label></td>
                        <td><input type="text" name="list_price" id="list_price"></td>
                    </tr>
                    <tr>
                        <td><label for="discount">Discount</label></td>
                        <td><input type="text" name="discount" id="discount"></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Insert Order Items" name="submit"></td>
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
    </style>
</html>
