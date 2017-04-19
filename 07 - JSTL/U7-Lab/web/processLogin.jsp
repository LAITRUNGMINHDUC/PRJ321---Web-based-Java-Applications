<%-- 
    Document   : processLogin
    Created on : Feb 8, 2017, 11:25:16 AM
    Author     : duclt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Process Login</title>
    </head>
    <body>
        <h1>Processing...</h1>

        <jsp:useBean id="loginAttr" class="lab.javabean.LoginBean" scope="session"/>
        <jsp:setProperty name="loginAttr"  property="username" />
        <jsp:setProperty name="loginAttr"  property="password" />

        <c:if test="${loginAttr.checkLogin()}">
            <jsp:forward page="view.jsp"/>            
        </c:if>

        <h2>
            <font color="red">
            Invalid username or password !!
            </font>
            Click <a href="index.jsp">here</a> to try again.
        </h2>    
    </body>
</html>
