<%-- 
    Document   : viewCart
    Created on : Jan 18, 2017, 11:44:42 AM
    Author     : TuanHDSE62146
--%>

<%@page import="java.util.Map"%>
<%@page import="sample.cart.CartObj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <h1>Your Cart includes </h1>

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
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
            <form action="TestServlet">
                <%
                    Map<String, Integer> items = cart.getItems();
                    int count = 0;
                    for (Map.Entry item : items.entrySet()) {
                %>
                <tr>
                    <td><%= ++count%></td>
                    <td><%= item.getKey()%></td>
                    <td><%= item.getValue()%></td>
                    <td>
                        <input type="checkbox" name="chkItem" value="<%= item.getKey()%>" />
                    </td>
                </tr>
                <%
                    }// end for
                %>
                <tr>
                    <td colspan="3">
                        <a href="bookstore.html">Add more items to your cart</a>
                    </td>
                    <td>
                        <input type="submit" value="Remove Seleted Item" name="btnLogin" />
                    </td>
                </tr>
            </form>
        </tbody>
    </table>

    <%
                    return;
                }
            }
        }//end of session
%>
    <h2>No Cart is existed</h2>
</body>
</html>
