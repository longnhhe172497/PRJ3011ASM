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
        <link rel="stylesheet" type="text/css" href="css/billManager.css">
    </head>
    <body>
        <h1>Bill Management</h1>
        <% 
            Vector<Orders> orders = (Vector<Orders>)request.getAttribute("ordersData");
            Vector<Order_items> orderItems = (Vector<Order_items>)request.getAttribute("orderItemsData");
            
            if(orders != null) {
        %>
        <table>
            <tr>
                <th>Bill ID</th>
                <th>Customer Name</th>
                <th>Date</th>
                <th>Total</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
            <% for(Orders order : orders) { 
                // Calculate total for each order
                double total = 0;
                for(Order_items item : orderItems) {
                    if(item.getOrder_id() == order.getOrder_id()) {
                        total += item.getQuantity() * item.getList_price() * (1 - item.getDiscount());
                    }
                }
                
                // Determine status
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
                <td>
                    <button class="detail-btn" onclick="toggleSelect(<%= order.getOrder_id() %>)">
                        Detail
                    </button>
                    <select id="status-<%= order.getOrder_id() %>" class="status-select" style="display:none;" onchange="updateStatus(<%= order.getOrder_id() %>)">
                        <option value="1" <%= order.getOrder_status()==1?"selected":"" %>>Waiting</option>
                        <option value="2" <%= order.getOrder_status()==2?"selected":"" %>>Processing</option>
                        <option value="3" <%= order.getOrder_status()==3?"selected":"" %>>Done</option>
                        <option value="4" <%= order.getOrder_status()==4?"selected":"" %>>Received</option>
                    </select>
                    <script>
                        function toggleSelect(orderId) {
                            var select = document.getElementById('status-' + orderId);
                            var btn = document.querySelector(`button[onclick="toggleSelect(${orderId})"]`);
                            select.style.display = select.style.display === 'none' ? 'inline-block' : 'none';
                            btn.style.display = btn.style.display === 'none' ? 'inline-block' : 'none';
                        }
                        
                        function updateStatus(orderId) {
                            var select = document.getElementById('status-' + orderId);
                            var status = select.value;
                            
                            // Gửi yêu cầu AJAX để cập nhật trạng thái
                            var xhr = new XMLHttpRequest();
                            xhr.open("GET", 'BillURL?service=updateStatus&orderId=' + orderId + '&status=' + status, true);
                            xhr.onreadystatechange = function() {
                                if (xhr.readyState === 4 && xhr.status === 200) {
                                    // Cập nhật giao diện người dùng
                                    var statusCell = select.parentElement.previousElementSibling;
                                    var newStatus = "";
                                    var newStatusClass = "";
                                    
                                    switch(parseInt(status)) {
                                        case 1:
                                            newStatus = "Waiting";
                                            newStatusClass = "status-wait";
                                            break;
                                        case 2:
                                            newStatus = "Processing";
                                            newStatusClass = "status-process";
                                            break;
                                        case 3:
                                            newStatus = "Done";
                                            newStatusClass = "status-done";
                                            break;
                                        case 4:
                                            newStatus = "Received";
                                            newStatusClass = "status-received";
                                            break;
                                    }
                                    
                                    statusCell.textContent = newStatus;
                                    statusCell.className = newStatusClass;
                                    
                                    // Ẩn select và hiện nút Detail
                                    select.style.display = 'none';
                                    select.parentElement.querySelector('.detail-btn').style.display = 'inline-block';
                                }
                            };
                            xhr.send();
                        }
                    </script>
                </td>
            </tr>
            <% } %>
        </table>
        <% } else { %>
        <p>No bill data available</p>
        <% } %>
    </body>
</html>
