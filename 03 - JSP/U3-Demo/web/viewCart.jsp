<%-- 9c
    Document   : viewCart
    Created on : Jan 18, 2017, 11:44:47 AM
    Author     : duclt
--%>

<%@page import="java.util.Map"%>
<%@page import="Demo.Cart.CartObj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <h1>Your Cart includes</h1>

        <%
            if (session != null) {
                CartObj cart = (CartObj) session.getAttribute("CART");

                if (cart != null) {
                    if (cart.getItems() != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Title</th>
                    <th>Quantity</th>
                </tr>
            </thead>
            <tbody>
                <%
                    Map<String, Integer> items = cart.getItems();
                    int count = 0;
                    for (Map.Entry item : items.entrySet()) {
                %>

                <tr>
                    <td>
                        <%= ++count%>                        
                    </td>
                    <td>
                        <%= item.getKey()%>                        
                    </td>
                    <td>
                        <%= item.getValue()%>                        
                    </td>
                </tr>

                <%
                    }
                %>

            </tbody>
        </table>

        <%
                        return;
                    }
                }
            }// end of session


        %>

    </body>
</html>
