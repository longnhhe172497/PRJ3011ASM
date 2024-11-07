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
                <h2>Checkout</h2>
                <% if (customer != null) { %>
                <p><strong>First Name:</strong> <%= customer.getFirst_name() %>!</p>
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

            <form action="OrderController" method="POST">
                <input type="hidden" name="service" value="confirmOrder" />
                <button type="submit" class="btn btn-primary">Confirm Order</button>
            </form>
            <a href="CartURL?service=showCart" class="btn btn-secondary">Back to Cart</a>
        </div>
    </body>
</html>
