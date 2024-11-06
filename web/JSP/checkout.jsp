<%-- 
    Document   : checkout
    Created on : Nov 7, 2024, 2:34:07 AM
    Author     : HOANG LONG
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Vector,entity.Cart,entity.Customer" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Checkout</title>
        <link rel="stylesheet" type="text/css" href="css/cart.css">
    </head>
    <body>
        <div class="checkout-container">
            <h2>Checkout</h2>

            <h3>Customer Information</h3>
            
            <%
                // Hiển thị thông tin khách hàng nếu có
                Object customer = request.getAttribute("customer");
                if (customer != null) {
                    // Giả sử customer có các thuộc tính như name, email, etc.
                    out.println("<p>Name: " + customer.getName() + "</p>");
                    out.println("<p>Email: " + customer.getEmail() + "</p>");
                }
            %>

            <h3>Your Order</h3>
            <%
                Vector<Cart> vectorCart = (Vector<Cart>) request.getAttribute("vectorCart");
                if (vectorCart != null && !vectorCart.isEmpty()) {
            %>
            <table border="0">
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
                <tr>
                    <td colspan="3">Grand Total</td>
                    <td>$<%= String.format("%.2f", grandTotal) %></td>
                </tr>
            </table>
            <%
                } else {
                    out.println("<p>Your cart is empty.</p>");
                }
            %>

            <!-- Submit button to confirm the order (you can add order processing logic here) -->
            <form method="POST" action="CartURL?service=confirmOrder">
                <button type="submit">Confirm Order</button>
            </form>
        </div>
    </body>
</html>


