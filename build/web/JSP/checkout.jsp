<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.Vector,entity.Customer,entity.Cart" %>
<%@ page import="model.DAOOrders" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Checkout</title>
        <link rel="stylesheet" type="text/css" href="css/checkout.css">
    </head>
    <body>
        <div class="checkout-container">

            <%
                Vector<Customer> vector = (Vector<Customer>) request.getAttribute("customerData");
                Customer customer = (Customer) session.getAttribute("customer");
            %>

            <div class="customer-info">
                <h2 class="checkout-title">Checkout</h2>
                <% if (customer != null) { %>
                <input type="hidden" name="customer_id" value="<%= customer.getCustomer_id() %>">
                <p><strong>First Name:</strong> <%= customer.getFirst_name() %></p>
                <p><strong>Last Name:</strong> <%= customer.getLast_name() %></p>
                <p><strong>Phone:</strong> <%= customer.getPhone() %></p>
                <p><strong>Email:</strong> <%= customer.getEmail() %></p>
                <p><strong>Street:</strong> <%= customer.getStreet() %></p>
                <p><strong>City:</strong> <%= customer.getCity() %></p>
                <p><strong>State:</strong> <%= customer.getState() %></p>
                <p><strong>Zip Code:</strong> <%= customer.getZip_code() %></p>
                <% } else { %>
                <p>Please log in or register.</p>
                <% } %>
            </div>

            <%-- Hiển thị giỏ hàng --%>
            <div class="cart-items">
                <h3>Your Cart</h3>
                <%
                    Vector<Cart> vectorCart = (Vector<Cart>) request.getAttribute("vectorCart");
                    if (vectorCart != null && !vectorCart.isEmpty()) {
                %>
                <table>
                    <tr>
                        <th>Product Name</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Total</th>
                    </tr>
                    <%
                        double grandTotal = 0;
                        for (Cart item : vectorCart) {
                            double total = item.getQuantity() * item.getList_price();
                            grandTotal += total;
                    %>
                    <tr>
                        <td><%= item.getProduct_name() %></td>
                        <td><%= item.getQuantity() %></td>
                        <td>$<%= String.format("%.2f", item.getList_price()) %></td>
                        <td>$<%= String.format("%.2f", total) %></td>
                    </tr>
                    <% } %>
                    <tr class="grand-total">
                        <td colspan="3"><strong>Grand Total</strong></td>
                        <td>$<%= String.format("%.2f", grandTotal) %></td>
                    </tr>
                </table>
                <% } else { %>
                <p>Your cart is empty.</p>
                <% } %>
            </div>

            <div style="text-align: center; margin-top: 20px;">
                <form action="CheckOutURL?service=confirmOrder" method="POST" style="display: inline-block;">
                    <input type="hidden" name="service" value="confirmOrder" />
                    <button type="submit" class="btn btn-primary">Confirm Order</button>
                </form>

                <a href="CartURL?service=showCart" class="btn btn-secondary">Back to Cart</a>
            </div>
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

        .checkout-container {
            margin: 20px auto;
            max-width: 1400px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.1);
            border-radius: 15px;
            overflow: visible;
            background: #ffffff;
            padding: 30px;
            position: relative;
        }

        .checkout-title {
            color: #3a3a3a;
            font-size: 2.5em;
            padding-bottom: 15px;
            border-bottom: 3px solid #8b7355;
            text-transform: uppercase;
            letter-spacing: 2px;
            margin: 20px 0;
            text-align: center;
            font-weight: bold;
            text-shadow: 2px 2px 4px rgba(0,0,0,0.1);
        }

        h3 {
            color: #3a3a3a;
            font-size: 1.8em;
            padding-bottom: 15px;
            border-bottom: 3px solid #8b7355;
            text-transform: uppercase;
            letter-spacing: 2px;
            margin-top: 0;
        }

        .cart-items {
            margin-top: 30px;
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

        .grand-total {
            font-weight: bold;
            background-color: #f5f5f5;
        }

        .btn {
            padding: 12px 25px;
            background: #8b7355;
            color: white;
            text-align: center;
            text-decoration: none;
            border: none;
            border-radius: 10px;
            cursor: pointer;
            font-weight: 600;
            font-size: 1em;
            text-transform: uppercase;
            letter-spacing: 1px;
            margin: 20px 10px;
            display: inline-block;
        }

        .btn:hover {
            background: #6d5a43;
        }

        .btn-secondary {
            background: #3a3a3a;
        }

        .btn-secondary:hover {
            background: #2d2d2d;
        }

        @media screen and (max-width: 768px) {
            .container {
                padding: 15px;
            }

            table {
                font-size: 0.9em;
            }

            .btn {
                width: 100%;
                margin: 10px 0;
            }
        }
    </style>
</html>
