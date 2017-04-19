<%-- 
    Document   : transfer
    Created on : Feb 19, 2017, 10:57:46 AM
    Author     : duclt
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transfer</title>
    </head>
    <body>
        <h1>Transfer Page</h1>

        <h3>Welcome, ${sessionScope.USER}</h3>

        <h4>${requestScope.NOTI}</h4>
        

        <font color="red">
        <c:forEach items="${requestScope.ERROR}" var="err">
            <h5>${err}</h5>
        </c:forEach>
        </font>

        <c:set var="dto" value="${requestScope.USERINFO}"/>        
        <form action="Controller" method="POST">
            <h5>
                Your Balance: ${dto.balance}  <br>
                Transfer Amount (1000 VND) <input type="text" name="txtAmount" value="${requestScope.AMOUNT}" /> <br>
                Account <input type="text" name="txtAccount" value="${requestScope.ACCOUNT}" />
            </h5>
            <input type="submit" value="ProcessTransfer" name="btAction" />
        </form>

        <h3>
            <a href="Controller">Back to Transaction Panel</a> <br>
            <a href="Controller?btAction=No">Logout</a> <br>
        </h3>

    </body>
</html>
