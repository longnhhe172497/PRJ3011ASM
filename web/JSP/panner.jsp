<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.Customer,entity.Cart" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Panner</title>
        <link rel="stylesheet" type="text/css" href="css/panner.css">
    </head>
    <body>
        <% 
            Vector<Customer> vector = (Vector<Customer>) request.getAttribute("customerData");
            Customer customer = (Customer) session.getAttribute("customer");
            Vector<Cart> vectorCart = (Vector<Cart>) session.getAttribute("vectorCart");
            int cartItemCount = (vectorCart != null) ? vectorCart.size() : 0;
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
                    <a href="/PRJ3011ASM/CustomerURL?service=loginCustomer">Login</a>
                    <a href="/PRJ3011ASM/CustomerURL?service=insertCustomer">Register</a>
                <% } %>
            </div>
        </div>
    </body>
</html>