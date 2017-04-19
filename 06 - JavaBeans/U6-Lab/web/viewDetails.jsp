<%-- 
    Document   : viewDetails
    Created on : Feb 13, 2017, 9:27:13 PM
    Author     : duclt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Details</title>
    </head>
    <body>
        Welcome... ${sessionScope.loginAttr.username}   

        <h1>Information Details</h1>

        <jsp:useBean id="userAttr" class="lab.javabean.ViewDetailBean" />
        <jsp:useBean id="sessionAttr" class="lab.javabean.ViewDetailBean" />

        <jsp:setProperty name="userAttr" param="username" property="username" />
        <jsp:setProperty name="sessionAttr" value="${sessionScope.loginAttr.username}" 
                         property="username" />

        ${userAttr.checkInfo()}
        ${sessionAttr.checkInfo()}

        <c:if test="${userAttr.username == sessionAttr.username}" >
            Username: ${sessionAttr.username}  <br>
            Password: ${sessionAttr.password} <br>
            Lastname: ${sessionAttr.lastname} <br>
            isAdmin: ${sessionAttr.isAdmin}<br>
        </c:if>

        <c:if test="${userAttr.username != sessionAttr.username and sessionAttr.isAdmin}" >
            Username: ${userAttr.username}  <br>
            Password: ${userAttr.password} <br>
            Lastname: ${userAttr.lastname} <br>
            isAdmin: ${userAttr.isAdmin}<br>
        </c:if>

        <c:if test="${userAttr.username != sessionAttr.username and not sessionAttr.isAdmin}" >
            No Permission
        </c:if>


        <%--        <jsp:useBean id="viewAttr" class="lab.javabean.ViewDetailBean" />
        <jsp:setProperty name="viewAttr" param="username" property="username" />
        ${viewAttr.checkInfo()}        
        <c:set var="usernameSession" value="${sessionScope.loginAttr.username}" />
        <c:set var="usernameDTO" value="${viewAttr.username}" />

        <c:if test="${usernameDTO eq usernameSession}">

            Username: ${sessionScope.loginAttr.username}  <br>
            Password: ${sessionScope.loginAttr.password} <br>
            Lastname: ${viewAttr.lastname} <br>
            isAdmin: ${viewAttr.isAdmin}<br>
        </c:if>

        <c:if test="${usernameDTO != usernameSession
                      and not viewAttr.isAdmin}">
              No Permission
        </c:if>   

        <c:if test="${usernameDTO != usernameSession
                      and viewAttr.isAdmin}">
              Username: ${viewAttr.username}  <br>
              Password: ${viewAttr.password} <br>
              Lastname: ${viewAttr.lastname} <br>
              isAdmin: ${viewAttr.isAdmin}<br>
        </c:if>   
        --%>
        <h3>Click <a href="view.jsp">here</a> to return Search page</h3>
    </body>
</html>
