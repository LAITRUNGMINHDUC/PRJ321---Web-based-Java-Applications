<%-- 
    Document   : ShoppingList
    Created on : Feb 6, 2017, 9:01:34 PM
    Author     : duclt
--%>

<%@page import="Lab.Products.ProductsDTO"%>
<%@page import="java.util.List"%>
<%@page import="Lab.Utils.FUNC"%>
<%@page import="Lab.Utils.ENV"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Store</title>
    </head>

    <body>
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

        <h3>
            <font color="green">
            <%
                String addNoti = FUNC.checkCookie(request, "NOTI");
                if (addNoti != null) {
                    out.println(addNoti);
                }
            %>
            </font>
        </h3>


        <h1>Products list</h1>

        <%            List<ProductsDTO> listProducts
                    = (List<ProductsDTO>) request.getAttribute("listProducts");
            if (listProducts == null) {
        %>
        <h3>No items to choose</h3>
        <%
        } else {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Product ID</th>
                    <th>Product Name</th>
                    <th>Quantity Per Unit</th>
                    <th>Price(USD)</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (ProductsDTO dto : listProducts) {
                %>
                <tr>                    
                    <td><%= ++count%></td>
                    <td><%= dto.getProductID()%></td>
                    <td> 
                        <a href="<%= ENV.DISPATCH_ADD_TO_CART + dto.getProductID()%>">
                            <%= dto.getProductName()%>
                        </a>
                    </td>
                    <td><%= dto.getQuantityPerUnit()%></td>
                    <td><%= dto.getPrice()%></td>
                </tr>

                <%
                    }
                %>
            </tbody>
        </table>
        <h3>Total: <%= count%> products in the store</h3>
        <a href="<%= ENV.DISPATCH_VIEW_CART%>"><h2>View my shopping cart</h2></a>
        <%
            }
        %>

    </body>
</html>
