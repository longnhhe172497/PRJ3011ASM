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
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap" rel="stylesheet">
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
                <ul>
                    <li><div class="menu-item" onclick="loadContent('customer')">Customer Manager</div></li>
                    <li><div class="menu-item" onclick="loadContent('product')">Product Manager</div></li>
                    <li><div class="menu-item" onclick="loadContent('order')">Order Manager</div></li>
                    <li><div class="menu-item" onclick="loadContent('orderItems')">Order Items Manager</div></li>
                    <li><div class="menu-item" onclick="loadContent('stocks')">Stocks Manager</div></li>
                    <li><div class="menu-item" onclick="loadContent('store')">Store Manager</div></li>
                    <li><div class="menu-item" onclick="loadContent('bill')">Bill Manager</div></li>
                </ul>
            </div>

            <div class="content" id="mainContent">
                <h2>Select a management option from the left menu to begin</h2>
            </div>
        </div>

        <script>
            function loadContent(page) {
                // Handle content loading
                const content = document.getElementById('mainContent');
                switch (page) {
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
            display: flex;
            margin: 20px auto;
            max-width: 1400px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.1);
            border-radius: 15px;
            overflow: hidden;
            background: #ffffff;
        }

        .sidebar {
            flex: 1;
            background: #fff;
            padding: 30px;
            border-right: 1px solid #e0e0e0;
        }

        .sidebar h3 {
            margin-top: 0;
            color: #3a3a3a;
            font-size: 1.8em;
            padding-bottom: 15px;
            border-bottom: 3px solid #8b7355;
            text-transform: uppercase;
            letter-spacing: 2px;
        }

        .sidebar ul {
            list-style-type: none;
            padding: 0;
        }

        .sidebar li {
            margin: 20px 0;
        }

        .content {
            flex: 3;
            padding: 30px;
            background-color: #ffffff;
        }

        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 20px;
            background: #3a3a3a;
            color: white;
            margin-bottom: 20px;
            border-radius: 15px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
        }

        .menu-item {
            text-decoration: none;
            color: #4a4a4a;
            font-weight: 500;
            display: block;
            padding: 12px 20px;
            border-radius: 10px;
            font-size: 1.1em;
            cursor: pointer;
            transition: all 0.3s ease;
        }

        .menu-item:hover {
            color: #8b7355;
            background: #f8f8f8;
        }

        .logout-btn {
            color: white;
            text-decoration: none;
            padding: 12px 25px;
            background: #8b7355;
            border: none;
            border-radius: 10px;
            cursor: pointer;
            font-weight: 600;
            font-size: 1em;
            text-transform: uppercase;
            letter-spacing: 1px;
            transition: background 0.3s ease;
        }

        .logout-btn:hover {
            background: #6d5a43;
        }

        h2 {
            color: #3a3a3a;
            font-size: 1.8em;
            margin-bottom: 20px;
        }
    </style>
</html>
