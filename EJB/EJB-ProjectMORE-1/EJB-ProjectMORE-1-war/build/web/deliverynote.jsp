<%-- 
    Document   : deliverynote
    Created on : Mar 8, 2017, 10:42:02 AM
    Author     : duclt
--%>

<%@page import="java.util.Map"%>
<%@page import="more.product.TblProduct"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Delivery Notes</h1>
        <c:set var="LISTPRODUCT" value="${sessionScope.LISTPRODUCT}"/>       
        <c:if test="${not empty LISTPRODUCT}">
            <form action="Controller" method="POST">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Total</th>
                            <th>Action (Delete)</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" varStatus="counter" items="${LISTPRODUCT}">
                            <tr>
                                <td>${counter.count}</td>
                                <td>
                                    ${dto.key.productName}
                                    <input type="hidden" name="txtProductId" value="${dto.key.productId}" />
                                    <input type="hidden" name="txtProductName" value="${dto.key.productName}" />
                                </td>
                                <td>                                    
                                    ${dto.key.price}
                                    <input type="hidden" name="txtProductPrice" value="${dto.key.price}" />
                                </td>
                                <td>                                    
                                    <input type="text" name="txtProductQuantity" value="${dto.value}" />
                                </td>
                                <td>${dto.key.price * dto.value}</td>
                                <td><a href="Controller?btAction=Remove&txtProductId=${dto.key.productId}">X</a></td>                                                        
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                Customer Name: <input type="text" name="txtCustomerName" value="" /> <br>
                Address: <input type="text" name="txtAddress" value="" /> <br> <br>

                <input type="submit" value="Update" name="btAction" /> 
                <input type="submit" value="Delivery" name="btAction" />
            </form>

        </c:if>

        <c:if test="${empty LISTPRODUCT}">
            No delivery note to show
        </c:if>
    </body>
</html>
