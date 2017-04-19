<%-- 
    Document   : index
    Created on : Mar 1, 2017, 9:43:48 PM
    Author     : duclt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Your Cart</title>
    </head>
    <body>
        <h1>Your cart includes</h1>

        <c:set var="cart" value="${sessionScope.CART}" />
        <c:if test="${not empty cart}">
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
                <form action="CenterServlet">
                    <c:forEach items="${cart.items}" var="hashMap" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${hashMap.key}</td>
                            <td>${hashMap.value}</td>
                            <td>
                                <input type="checkbox" name="chkItems" value="${hashMap.key}" />
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="3">
                            <a href="shopping.html">Add More Items to Cart</a>
                        </td>
                        <td>
                            <input type="submit" value="Remove" name="btAction" />
                        </td>
                    </tr>
                </form>
                <tr>
                    <td></td>
                    <td></td>
                </tr>
            </tbody>
        </table>
    </c:if>

    <c:if test="${empty cart}">
        <h2>No cart found</h2>
    </c:if>

</body>
</html>
