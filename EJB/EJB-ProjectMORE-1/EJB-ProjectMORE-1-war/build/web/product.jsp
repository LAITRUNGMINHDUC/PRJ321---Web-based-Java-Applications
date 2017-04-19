<%-- 
    Document   : product
    Created on : Mar 8, 2017, 10:41:49 AM
    Author     : duclt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="Controller">
            Search name: <input type="text" name="txtSearchValue" value="${requestScope.SEARCHVALUE}" /> 
            <input type="submit" value="Search" name="btAction" />
        </form>

        <h1>Result</h1>
        <c:set var="SEARCHRESULT" value="${requestScope.SEARCHRESULT}" />
        <c:if test="${not empty SEARCHRESULT}">
            Name: ${SEARCHRESULT.productName} <br>
            Price: ${SEARCHRESULT.price} 
            <form action="Controller">
                Quantity: <input type="text" name="txtQuantity" value="" />                                
                <input type="hidden" name="txtProductId" value="${SEARCHRESULT.productId}" />
                <input type="hidden" name="txtProductName" value="${SEARCHRESULT.productName}" />
                <input type="hidden" name="txtProductPrice" value="${SEARCHRESULT.price}" />                                
                <input type="submit" value="AddList" name="btAction" />                
            </form>
        </c:if>

        <c:if test="${not empty NOTI}">
            ${requestScope.NOTI}
        </c:if>
        <a href="Controller?btAction=ViewList">View List</a>
    </body>
</html>
