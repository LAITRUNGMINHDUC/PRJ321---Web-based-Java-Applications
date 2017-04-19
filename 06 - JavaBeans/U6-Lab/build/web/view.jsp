<%-- 
    Document   : view
    Created on : Feb 13, 2017, 9:27:02 PM
    Author     : duclt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Page</title>
    </head>
    <body>
        Welcome... ${sessionScope.loginAttr.username}

        <h1>Information Day</h1>
        <form action="view.jsp">
            Lastname: <input type="text" name="lastname" value="${param.lastname}" />
            <input type="submit" value="Search" name="btAction" />            
        </form>

        <jsp:useBean id="searchAttr" class="lab.javabean.SearchBean" scope="request" />
        <jsp:setProperty name="searchAttr" property="lastname" param="lastname" />                

        <h2>Result of Searching</h2>

        <c:set var="listName" value="${searchAttr.makeListLastName()}" />
        <c:if test="${not empty listName}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Username</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listName}"
                               var="dto" varStatus="counter" >
                        <tr>
                            <td>${counter.count}</td>
                            <td>${dto}</td>
                            <td>
                                <c:url var="detailLink" value="Controller">
                                    <c:param name="btAction" value="ViewDetail" />
                                    <c:param name="username" value="${dto}"/>
                                </c:url>
                                <a href="${detailLink}">View Detail</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>        
    </body>
</html>
