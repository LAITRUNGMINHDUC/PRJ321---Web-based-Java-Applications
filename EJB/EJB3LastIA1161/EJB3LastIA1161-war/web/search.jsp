<%-- 
    Document   : search
    Created on : Mar 1, 2017, 11:41:57 AM
    Author     : duclt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <h1>Search Page</h1>

        <form action="Controller" method="GET">
            Lastname: <input type="text" name="txtSearchValue" value="${requestScope.SEARCHVALUE}" />
            <input type="submit" value="Search" name="btAction" />
        </form>


        <h1>Result</h1>
        <c:set var="result" value="${requestScope.SEARCHRESULT}"/>

        <c:if test="${not empty result}"> 
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Username</th>
                        <th>Password</th>
                        <th>Lastname</th>
                        <th>is Admin</th>                        
                        <th>Update</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${result}" var="dto" varStatus="counter">                        
                        <tr>
                    <form action="Controller" method="POST">
                        <input type="text" name="txtSearchValue" hidden="true" value="${requestScope.SEARCHVALUE}" />

                        <td>${counter.count}</td>
                        <td>
                            <input type="text" name="txtUsername" value="${dto.username}" />                                
                        </td>
                        <td>                                
                            <input type="text" name="txtPassword" value="${dto.password}" />                                
                        </td>
                        <td>                                
                            <input type="text" name="txtLastname" value="${dto.lastname}" />                                
                        </td>

                        <td>
                            <c:if test="${dto.isAdmin == true}">
                                <input type="checkbox" name="chkAdmin" value="ON" checked="checked"/>                                                                        
                            </c:if>
                            <c:if test="${dto.isAdmin == false}">
                                <input type="checkbox" name="chkAdmin" value="ON"/>                                                                        
                            </c:if>
                        </td>

                        <td> <input type="submit" value="Update" name="btAction" /> </td>
                    </form>

                    <form action="Controller">
                        <input type="text" name="txtSearchValue" hidden="true" value="${requestScope.SEARCHVALUE}" />
                        <input type="text" hidden="true" name="txtUsername" value="${dto.username}" />
                        <input type="submit" value="Delete" name="btAction" />
                    </form>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</c:if>
</body>
</html>
