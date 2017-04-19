<%-- 
    Document   : ShoppingCart
    Created on : Feb 6, 2017, 9:01:46 PM
    Author     : duclt
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="Lab.Products.ProductsDTO"%>
<%@page import="Lab.Utils.ENV"%>
<%@page import="Lab.Utils.FUNC"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>

        <h3>
            <font color="red">
            <%
                String cookieUser = FUNC.checkCookie(request, "USERNAME");
                if (cookieUser != null) {
                    out.println("Hello " + cookieUser);
                } else {
                    response.sendRedirect(ENV.PAGE_LOGIN);
                }
            %>
            </font>
        </h3>

        <h1>View of your shopping cart</h1>

        <%
            Map<ProductsDTO, Integer> listCart
                    = (HashMap<ProductsDTO, Integer>) session.getAttribute("CART");
            if (listCart != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Product ID</th>
                    <th>Product Name</th>
                    <th>Quantity Per Unit</th>
                    <th>Price(USD)</th>
                    <th>Quantity</th>
                    <th>Total</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    int total = 0;
                    for (Map.Entry<ProductsDTO, Integer> product : listCart.entrySet()) {
                        total = total + product.getKey().getPrice() * product.getValue();
                %>
                <tr>
                    <td><%= ++count%></td>
                    <td><%= product.getKey().getProductID()%></td>
                    <td><%= product.getKey().getProductName()%></td>
                    <td><%= product.getKey().getQuantityPerUnit()%></td>                    
                    <td><%= product.getKey().getPrice()%></td>
                    <td><%= product.getValue()%></td>
                    <td><%= product.getKey().getPrice() * product.getValue()%></td>
                </tr>    
                <%
                    }
                %>

                <tr>
                    <td colspan="4">Total: <%= count%> products selected</td>
                    <td colspan="2">Payment in USD: </td>
                    <td><%= total%></td>
                </tr>
            </tbody>
        </table>

        <%
            if (total == 0) {
        %>
        <a href="<%= ENV.DISPATCH_SHOPPING_LIST%>"><h3>Back to Buy</h3></a>
        <%
        } else {
        %>
        <a href="<%= ENV.DISPATCH_CHECKOUT%>"><h3>Checkout</h3></a>
        <%
            }
        %>



        <%        } else {
        %>
        <h3>No item in cart</h3>
        <%
            }
        %>



    </body>
</html>
