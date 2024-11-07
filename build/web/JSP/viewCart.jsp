<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entity.Cart" %>
<%@ page import="java.util.Vector" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Your Shopping Cart</title>
        <link rel="stylesheet" type="text/css" href="css/cart.css">
    </head>
    <body>
        <div class="cart-container">
            <h2>Your Shopping Cart</h2>
            <%
                Vector<Cart> vectorCart = (Vector<Cart>) request.getAttribute("vectorCart");
                if (vectorCart != null && !vectorCart.isEmpty()) {
            %>
            <form method="POST" action="CartURL?service=updateQuantity">
                <table border="0">
                    <tr>
                        <th style="width: 10%;">Product ID</th>
                        <th style="width: 30%;">Product Name</th>
                        <th style="width: 10%;">Quantity</th>
                        <th style="width: 15%;">Price</th>
                        <th style="width: 15%;">Total</th>
                        <th style="width: 20%;">Action</th>
                    </tr>
                    <%
                        double grandTotal = 0;
                        for (Cart item : vectorCart) {
                            double total = item.getQuantity() * item.getList_price();
                            grandTotal += total;
                    %>
                    <tr>
                        <td><%= item.getProduct_id() %></td>
                        <td><%= item.getProduct_name() %></td>
                        <td>
                            <!-- Input field for quantity -->
                            <input type="number" name="quantity_<%= item.getProduct_id() %>" min="1" value="<%= item.getQuantity() %>" />
                        </td>
                        <td>$<%= String.format("%.2f", item.getList_price()) %></td>
                        <td>$<%= String.format("%.2f", total) %></td>
                        <td><a href="CartURL?service=removeCart&pid=<%= item.getProduct_id() %>">Remove</a></td>
                    </tr>
                    <% } %>
                    <tr class="grand-total-row">
                        <td colspan="4">Grand Total</td>
                        <td colspan="2">$<%= String.format("%.2f", grandTotal) %></td>
                    </tr>
                </table>
                <button class = "update" type="submit">Update Cart</button>
            </form>
            <a href="CheckOutURL" class="checkout">Checkout</a>

            <% } else { %>
            <p>Your cart is empty.</p>
            <% } %>
        </div>
    </body>
</html>