<%-- 
    Document   : detailBill
    Created on : Nov 8, 2024, 5:24:30 AM
    Author     : HOANG LONG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Orders,entity.Order_items"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Details</title>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <h1>Order Details</h1>
            <a href="BillURL?service=listAllBill" class="back-btn">Back to Bills</a>
            
            <% 
                Vector<Order_items> orderItems = (Vector<Order_items>)request.getAttribute("orderItemsData");
                Orders order = (Orders)request.getAttribute("orderData");
                
                if(orderItems != null && order != null) {
                    double total = 0;
            %>
            
            <div class="order-info">
                <h2>Order #<%= order.getOrder_id() %></h2>
                <p><strong>Order Date:</strong> <%= order.getOrder_date() %></p>
                <p><strong>Customer ID:</strong> <%= order.getCustomer_id() %></p>
            </div>

            <div class="order-items">
                <table>
                    <tr>
                        <th>Product ID</th>
                        <th>Quantity</th>
                        <th>List Price</th>
                        <th>Discount</th>
                        <th>Subtotal</th>
                    </tr>
                    <% for(Order_items item : orderItems) { 
                        double subtotal = item.getQuantity() * item.getList_price() * (1 - item.getDiscount());
                        total += subtotal;
                    %>
                    <tr>
                        <td><%= item.getProduct_id() %></td>
                        <td><%= item.getQuantity() %></td>
                        <td>$<%= String.format("%.2f", item.getList_price()) %></td>
                        <td><%= (int)(item.getDiscount() * 100) %>%</td>
                        <td>$<%= String.format("%.2f", subtotal) %></td>
                    </tr>
                    <% } %>
                    <tr class="total-row">
                        <td colspan="4"><strong>Total</strong></td>
                        <td>$<%= String.format("%.2f", total) %></td>
                    </tr>
                </table>
            </div>
            <% } else { %>
                <p class="error">No order details available</p>
            <% } %>
        </div>
    </body>
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            margin: 0;
            padding: 20px;
            background: #f5f5f5;
            color: #2d2d2d;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
            background: white;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0,0,0,0.1);
        }

        h1 {
            color: #3a3a3a;
            border-bottom: 3px solid #8b7355;
            padding-bottom: 10px;
            margin-bottom: 30px;
        }

        .back-btn {
            display: inline-block;
            padding: 10px 20px;
            background: #8b7355;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            margin-bottom: 20px;
        }

        .back-btn:hover {
            background: #6d5a43;
        }

        .order-info {
            background: #f9f9f9;
            padding: 20px;
            border-radius: 5px;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #3a3a3a;
            color: white;
        }

        tr:hover {
            background-color: #f5f5f5;
        }

        .total-row {
            font-weight: bold;
            background-color: #f0f0f0;
        }

        .error {
            color: #ff0000;
            text-align: center;
            padding: 20px;
        }
    </style>
</html>
