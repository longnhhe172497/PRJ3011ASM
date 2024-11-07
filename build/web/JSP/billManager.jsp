<%-- 
    Document   : billManager
    Created on : Nov 8, 2024, 2:11:39 AM
    Author     : HOANG LONG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Orders,entity.Order_items"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bill Management</title>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <h1>Bill Management</h1>
            <% 
                Vector<Orders> orders = (Vector<Orders>)request.getAttribute("ordersData");
                Vector<Order_items> orderItems = (Vector<Order_items>)request.getAttribute("orderItemsData");
                
                if(orders != null) {
            %>
            <a class="back-btn" href="JSP/homeManager.jsp">Back to Home</a>
            <table>
                <tr>
                    <th>Bill ID</th>
                    <th>Customer Name</th>
                    <th>Date</th>
                    <th>Total</th>
                    <th>Status</th>
                    <th>Actions</th>
                    <th>Details</th>
                </tr>
                <% for(Orders order : orders) { 
                    double total = 0;
                    for(Order_items item : orderItems) {
                        if(item.getOrder_id() == order.getOrder_id()) {
                            total += item.getQuantity() * item.getList_price() * (1 - item.getDiscount());
                        }
                    }
                    
                    String status = "";
                    String statusClass = "";
                    switch(order.getOrder_status()) {
                        case 1:
                            status = "Waiting";
                            statusClass = "status-wait";
                            break;
                        case 2:
                            status = "Processing";
                            statusClass = "status-process";
                            break;
                        case 3:
                            status = "Done";
                            statusClass = "status-done";
                            break;
                        case 4:
                            status = "Received";
                            statusClass = "status-received";
                            break;
                    }
                %>
                <tr>
                    <td><%= order.getOrder_id() %></td>
                    <td><%= order.getCustomer_id() %></td>
                    <td><%= order.getOrder_date() %></td>
                    <td>$<%= String.format("%.2f", total) %></td>
                    <td class="<%= statusClass %>"><%= status %></td>
                    <td class="actions-cell">
                        <form action="BillURL" method="GET" class="status-form">
                            <input type="hidden" name="service" value="updateStatus">
                            <input type="hidden" name="orderId" value="<%= order.getOrder_id() %>">
                            <select name="status" onchange="this.form.submit()">
                                <option value="1" <%= order.getOrder_status()==1?"selected":"" %>>Waiting</option>
                                <option value="2" <%= order.getOrder_status()==2?"selected":"" %>>Processing</option>
                                <option value="3" <%= order.getOrder_status()==3?"selected":"" %>>Done</option>
                                <option value="4" <%= order.getOrder_status()==4?"selected":"" %>>Received</option>
                            </select>
                        </form>
                    </td>
                    <td>
                        <form action="BillURL" method="GET">
                            <input type="hidden" name="service" value="viewDetail">
                            <input type="hidden" name="orderId" value="<%= order.getOrder_id() %>">
                            <button type="submit" class="view-detail-btn">View Detail</button>
                        </form>
                    </td>
                </tr>
                <% } %>
            </table>
            <% } else { %>
            <p>No bill data available</p>
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
            min-height: 100vh;
        }

        .container {
            max-width: 1400px;
            margin: 20px auto;
            padding: 20px;
            background: white;
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.1);
        }

        h1 {
            margin-top: 0;
            color: #3a3a3a;
            font-size: 1.8em;
            padding-bottom: 15px;
            border-bottom: 3px solid #8b7355;
            text-transform: uppercase;
            letter-spacing: 2px;
            text-align: center;
        }

        table {
            width: 100%;
            border-collapse: separate;
            border-spacing: 0;
            margin-top: 25px;
            box-shadow: 0 5px 20px rgba(0,0,0,0.05);
            border-radius: 10px;
            overflow: hidden;
            table-layout: fixed;
        }

        th, td {
            border: none;
            padding: 15px;
            text-align: center;
        }

        th {
            background: #3a3a3a;
            color: white;
            font-weight: 600;
            text-transform: uppercase;
            letter-spacing: 1.5px;
            font-size: 0.9em;
        }

        tr:nth-child(even) {
            background-color: #fafafa;
        }

        tr {
            border-bottom: 1px solid #f0f0f0;
        }

        tr:hover {
            background-color: #f5f5f5;
        }

        button, select {
            padding: 8px 16px;
            background: #8b7355;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-weight: 500;
        }

        button:hover, select:hover {
            background: #6d5a43;
        }

        .status-wait {
            color: #f39c12;
        }

        .status-process {
            color: #3498db;
        }

        .status-done {
            color: #27ae60;
        }

        .status-received {
            color: #2ecc71;
        }

        .actions-cell {
            min-width: 150px;
        }

        .status-form {
            display: inline;
        }

        .view-detail-btn {
            background: #3a3a3a;
        }

        .view-detail-btn:hover {
            background: #2d2d2d;
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
    </style>
</html>
