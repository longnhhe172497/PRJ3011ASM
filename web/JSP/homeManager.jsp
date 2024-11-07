<%-- 
    Document   : homeManager
    Created on : Nov 8, 2024, 1:48:23 AM
    Author     : HOANG LONG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Staffs"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Management Dashboard</title>
        <link rel="stylesheet" type="text/css" href="PRJ3011ASM/css/homeManager.css">
    </head>
    <body>
        <%
            Staffs staff = (Staffs) session.getAttribute("staffs");
            if (staff == null) {
                response.sendRedirect("/PRJ3011ASM/StaffsURL?service=loginStaffs");
                return;
            }
        %>
        
        <div class="header">
            <h2>Welcome, <%= staff.getFirst_name() + " " + staff.getLast_name() %> (Staff ID: <%= staff.getStaff_id() %>)</h2>
            <a href="/PRJ3011ASM/StaffsURL?service=logoutStaffs" class="logout-btn">Logout</a>
        </div>
        
        <div class="container">
            <div class="sidebar">
                <h3>Management System</h3>
                <div class="menu-item" onclick="loadContent('customer')">Customer Manager</div>
                <div class="menu-item" onclick="loadContent('product')">Product Manager</div>
                <div class="menu-item" onclick="loadContent('order')">Order Manager</div>
                <div class="menu-item" onclick="loadContent('orderItems')">Order Items Manager</div>
                <div class="menu-item" onclick="loadContent('stocks')">Stocks Manager</div>
                <div class="menu-item" onclick="loadContent('store')">Store Manager</div>
                <div class="menu-item" onclick="loadContent('bill')">Bill Manager</div>
            </div>
            
            <div class="content" id="mainContent">
                <!-- Content will be loaded dynamically here -->
                <h2>Select a management option from the left menu to begin</h2>
            </div>
        </div>

        <script>
            function loadContent(page) {
                // Handle content loading
                const content = document.getElementById('mainContent');
                switch(page) {
                    case 'customer':
                        window.location.href = "/PRJ3011ASM/CustomerURL?service=listAllCustomer";
                        break;
                    case 'product':
                        window.location.href = "/PRJ3011ASM/ProductURL?service=productManager";
                        break;
                    case 'order':
                        window.location.href = "/PRJ3011ASM/OrdersURL?service=listAllOrders";
                        break;
                    case 'orderItems':
                        window.location.href = "/PRJ3011ASM/Order_itemsURL?service=listAllOrder_items";
                        break;
                    case 'stocks':
                        window.location.href = "/PRJ3011ASM/StocksURL?service=listAllStocks";
                        break;
                    case 'store':
                        window.location.href = "/PRJ3011ASM/StoresURL?service=listAllStores";
                        break;
                    case 'bill':
                        window.location.href = "/PRJ3011ASM/BillURL?service=listAllBill";
                        break;
                }
            }
        </script>
    </body>
</html>
