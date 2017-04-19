<%-- 
    Document   : index
    Created on : Feb 13, 2017, 9:06:12 PM
    Author     : duclt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <c:if test="${sessionScope.loginAttr.checkLogin()}">            
            <c:redirect url="view.jsp"/>
        </c:if>

        <h1>Login Page</h1>
        <form action="processLogin.jsp" method="POST">
            Username: <input type="text" name="username" value="" /> <br>
            Password: <input type="password" name="password" value="" /> <br>
            <input type="submit" value="Login" name="btAction" />             
        </form>
    </body>
</html>
