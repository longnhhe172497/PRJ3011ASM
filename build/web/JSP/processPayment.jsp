<%-- 
    Document   : processPayment
    Created on : Nov 7, 2024, 2:36:46 AM
    Author     : HOANG LONG
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Payment</title>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
    </head>
    <body>
        <div class="success-container">
            <div class="success-icon">✓</div>
            <h2>Payment Successful!</h2>
            <p>Thank you for shopping with us! Your order has been confirmed.</p>
            <div class="buttons">
                <a href="ProductURL" class="btn">Back to Home</a>
            </div>
        </div>

        <style>
            body {
                font-family: 'Poppins', sans-serif;
                margin: 0;
                padding: 0;
                min-height: 100vh;
                display: flex;
                align-items: center;
                justify-content: center;
                background: #f5f5f5;
            }

            .success-container {
                background: white;
                padding: 40px;
                border-radius: 20px;
                box-shadow: 0 10px 30px rgba(0,0,0,0.1);
                text-align: center;
                max-width: 500px;
                width: 90%;
            }

            .success-icon {
                width: 80px;
                height: 80px;
                background: #4CAF50;
                border-radius: 50%;
                display: flex;
                align-items: center;
                justify-content: center;
                margin: 0 auto 20px;
                color: white;
                font-size: 40px;
                font-weight: bold;
            }

            h2 {
                color: #333;
                margin-bottom: 20px;
                font-size: 28px;
            }

            p {
                color: #666;
                margin-bottom: 30px;
                font-size: 16px;
                line-height: 1.6;
            }

            .buttons {
                display: flex;
                gap: 15px;
                justify-content: center;
            }

            .btn {
                padding: 12px 25px;
                background: #8b7355;
                color: white;
                text-decoration: none;
                border-radius: 8px;
                font-weight: 600;
                transition: all 0.3s ease;
            }

            .btn:hover {
                background: #6d5a43;
                transform: translateY(-2px);
            }
        </style>
    </body>
</html>
