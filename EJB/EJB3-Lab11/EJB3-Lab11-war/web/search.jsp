<%-- 
    Document   : search
    Created on : Mar 4, 2017, 2:49:57 PM
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
        <h1>Hello ... ${sessionScope.USERNAME} </h1>

        <h1>Search Page</h1>
        <form action="Controller">
            Search: <input type="text" name="txtSearchValue" value="${requestScope.SEARCHVALUE}" />
            <input type="submit" value="Search" name="btAction" />
        </form>

        <h3>${requestScope.NOTI}</h3>
        
        <a href="menu.jsp">Menu</a>

        <h1>Result</h1>

        <c:set var="result" value="${requestScope.SEARCHRESULT}" />

        <c:if test="${not empty result}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>ID</th>
                        <th>Fullname</th>
                        <th>Balance</th>
                        <th>Role</th>
                        <th>Update</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${result}" var="dto" varStatus="counter">
                        <tr>
                    <form action="Controller" method="POST">
                        <td>${counter.count}</td>
                        <td>${dto.id}</td>
                        <td>${dto.fullName}</td>
                        <td>
                            <input type="text" name="txtBalance" value="${dto.balance}" />                                                                
                        </td>
                        <td>                                
                            <c:if test="${dto.role == true}">
                                Admin
                            </c:if>
                            <c:if test="${dto.role == false}">
                                User
                            </c:if>
                        </td>
                        <td> 
                            <input type="hidden" name="txtUsername" value="${dto.id}"/>                        
                            <input type="hidden" name="txtSearchValue" value="${requestScope.SEARCHVALUE}"/> 
                            <input type="submit" value="Update" name="btAction" /> 
                        </td>
                    </form>
                    <td>
                        <form action="Controller" method="GET">
                            <input type="hidden" name="txtUsername" value="${dto.id}"/>                        
                            <input type="hidden" name="txtSearchValue" value="${requestScope.SEARCHVALUE}"/> 
                            <input type="submit" value="Delete" name="btAction" />
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</c:if>

<c:if test="${empty result && not empty requestScope.SEARCHVALUE}">
    <h3>No result with that search</h3>
</c:if>

</body>
</html>
