<%-- 
    Document   : search
    Created on : Mar 6, 2017, 10:57:52 AM
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
        <h1>Import Page</h1>

        <form action="Controller">
            Search: <input type="text" name="txtSearchValue" value="${requestScope.SEARCHVALUE}" /> 
            <input type="submit" value="Search" name="btAction" />            
        </form>

        <c:if test="${not empty requestScope.NOTI }">
            <h4>${requestScope.NOTI}</h4>
        </c:if>

        <h3>Result</h3>
        <c:set var="SEARCHRESULT" value="${requestScope.SEARCHRESULT}" />
        <c:if test="${not empty SEARCHRESULT}">
            <form action="Controller" method="POST">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Name</th>
                            <th>Price</th> <!-- Text input -->
                            <th>Quantity</th> <!-- Text input -->
                            <th>Action</th> <!-- Checkbox -->
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${SEARCHRESULT}">
                            <tr>
                                <td>${dto.productID}</td>
                                <td>${dto.productName}</td>
                                <td>
                                    <input type="text" name="txtPrice_${dto.productID}" value="${dto.price}" />
                                </td>
                                <td>
                                    <input type="text" name="txtQuantity_${dto.productID}" value="" />
                                </td>
                                <td>
                                    <input type="checkbox" name="chkItem" value="${dto.productID}" checked="checked" />
                                </td>                                
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <input type="text" name="txtSearchValue" hidden="true" value="${requestScope.SEARCHVALUE}" /> 
                <input type="submit" value="Import" name="btAction" />
            </form>
        </c:if>

        <c:if test="${empty SEARCHRESULT && not empty requestScope.SEARCHVALUE}">
            Nothing to Show
        </c:if>
    </body>
</html>
