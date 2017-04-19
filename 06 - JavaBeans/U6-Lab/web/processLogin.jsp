<%-- 
    Document   : processLogin
    Created on : Feb 13, 2017, 9:26:55 PM
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
        <h1>Processing.....</h1>

        <jsp:useBean id="loginAttr" class="lab.javabean.LoginBean" scope="session" />
        <jsp:setProperty name="loginAttr" property="username" param="username" />
        <jsp:setProperty name="loginAttr" property="password" param="password" />

        <c:if test="${not empty loginAttr.username and not loginAttr.password}">
            <c:if test="${loginAttr.checkLogin()}">
                <c:redirect url="view.jsp" />            
            </c:if>            
        </c:if>

        <font color="red">
        <h2>Invalid username or password or blank...</h2>
        <h3>
            Click <a href="index.jsp">here</a> to try again
        </h3>
        </font>        

    </body>
</html>
