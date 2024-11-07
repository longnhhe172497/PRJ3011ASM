<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Customer,entity.Cart,entity.Staffs" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Panner</title>
        <link rel="stylesheet" type="text/css" href="css/panner.css">
    </head>
    <style>
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 20px 40px;
            background: #ffffff;
            box-shadow: 0 2px 15px rgba(0,0,0,0.1);
            margin-bottom: 30px;
        }

        .welcome-message {
            color: #3a3a3a;
            font-size: 1.4em;
            font-weight: 600;
            margin: 0;
        }

        .login-links {
            display: flex;
            gap: 20px;
            align-items: center;
        }

        .login-links a {
            text-decoration: none;
            color: #4a4a4a;
            font-weight: 500;
            padding: 10px 20px;
            border-radius: 8px;
            transition: all 0.3s ease;
            background: #f8f8f8;
        }

        .login-links a:hover {
            background: #8b7355;
            color: white;
        }

    </style>
    <body>
        <% 
            Vector<Customer> vector = (Vector<Customer>) request.getAttribute("customerData");
            Customer customer = (Customer) session.getAttribute("customer");
            Vector<Staffs> vectorStaff = (Vector<Staffs>) request.getAttribute("staffsData");
            Staffs staff = (Staffs) session.getAttribute("staffs");
            Vector<Cart> vectorCart = (Vector<Cart>) session.getAttribute("vectorCart");
            int cartItemCount = (vectorCart != null) ? vectorCart.size() : 0;

            if (staff != null) {
                response.sendRedirect("/PRJ3011ASM/JSP/homeManager.jsp");
                return;
            }
        %>

        <!-- Header Section -->
        <div class="header">
            <h2 class="welcome-message">
                <% if (customer != null) { %>
                Welcome, <%= customer.getEmail() %>!
                <% } else { %>
                Please log in or register.
                <% } %>
            </h2>

            <div class="login-links">
                <% if (customer != null) { %>
                <a href="/PRJ3011ASM/CustomerURL?service=logoutCustomer">Logout</a>
                <a href="/PRJ3011ASM/CartURL?service=showCart">Show Cart (<%= session.getAttribute("cartItemCount") != null ? session.getAttribute("cartItemCount") : 0 %>)</a>
                <% } else { %>
                <a href="/PRJ3011ASM/CustomerURL?service=loginCustomer">Login Customer</a>
                <a href="/PRJ3011ASM/StaffsURL?service=loginStaffs">Login Staff</a>
                <a href="/PRJ3011ASM/CustomerURL?service=insertCustomer">Register</a>
                <% } %>
            </div>
        </div>
    </body>
</html>
