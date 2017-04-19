<%-- 
    Document   : search
    Created on : Jan 13, 2017, 11:11:40 AM
    Author     : duclt
--%>

<%@page import="Demo.Registration.RegistrationDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Search</h1>

        <font color="red">
        <%
//            Cookie[] cookies = request.getCookies();
//            if (cookies != null) {
//                String username = "";
//                for (Cookie cookie : cookies) {
//                    String tmp = cookie.getName();
//                    if (!tmp.equals("JSESSIONID")) {
//                        username = tmp;
//                    }
//                }
        %>
        Welcome ${sessionScope.USER}
        <%

        %>


        </font>

        <form name="Search Form" action="DispatcherServlet">
            <h2>Search Lastname: <input type="text" name="txtSearch" value="${param.txtSearch}" /> </h2>            
            <input type="submit" value="Search" name="btAction" />            
        </form>

        <c:set var="searchValue" value="${param.txtSearch}" />
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCHRESULT}"/>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Lastname</th>
                            <th>Role</th>
                            <th>Delete</th>                            
                            <th>Update</th>    
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="DispatcherServlet" method="POST">
                            <tr>
                                <td>${counter.count}</td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" value="${dto.username}" />
                                </td>
                                <td>
                                    ${dto.password}
                                    <input type="hidden" name="txtPassword" value="${dto.password}" />
                                </td>
                                <td>${dto.lastname}</td>
                                <td>
                                    ${dto.isAdmin}
                                    <input type="checkbox" name="chkRole" value="ADMIN" 
                                           <c:if test="${dto.isAdmin}">
                                               checked="checked"
                                           </c:if>
                                           />                                
                                </td>
                                <td> 
                                    <c:url var="delLink" value="DispatcherServlet">
                                        <c:param name="btAction" value="delete"/>
                                        <c:param name="PK" value="${dto.username}" />
                                        <c:param name="lastSearchValue" value="${param.txtSearch}" />
                                    </c:url>
                                    <a href="${delLink}">Delete</a> 
                                </td>
                                <td>
                                    <input type="submit" value="Update" name="btAction" />
                                </td>
                            </tr>                                                    
                        </form>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>
        <c:if test="${empty result}">
            <h2>No record is matched</h2>
        </c:if>            
    </c:if>

    <%-- 
    <%= request.getParameter("txtSearch")%>

        <br>
        <%
            String searchValue = request.getParameter("txtSearch");

            if (searchValue != null) {
                List<RegistrationDTO> result
                        = (List<RegistrationDTO>) request.getAttribute("SEARCHRESULT");

                if (result != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Lastname</th>
                    <th>Role</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>

                <%
                    int count = 0;
                    for (RegistrationDTO dto : result) {
                        String URLRewriting = "DispatcherServlet?btAction=Delete&PK="
                                + dto.getUsername() + "&lastSearchValue=" + searchValue;
                %>
                <tr>
            <form>
                <td> <%= ++count%> </td>
                <td><%= dto.getUsername()%>
                    <input type="hidden" name="txtUsername" value="<%= dto.getUsername()%>" />
                </td>
                <td>
                    <input type="text" name="txtPassword" value="<%= dto.getPassword()%>" /></td>
                <td><%= dto.getLastname()%></td>                    
                <td>
                    <input type="checkbox" name="chkRole" value="ADMIN"
                           <%
                               if (dto.isIsAdmin()) {
                           %>
                           checked="checked"
                           <%
                               }
                           %>
                           />
                    <%= dto.isIsAdmin()%>
                </td>
                <td><a href="<%= URLRewriting%>">Delete</a></td>
                <input type="hidden" name="lastSearchValue" value="<%= searchValue%>" />
                <td><input type="submit" name="btAction" value="Update"></td>
            </form>
        </tr>
        <%

            }
        %>

    </tbody>
</table>

<%
} else {
%>
<h2>No record is matched</h2>
<%
        }
    }
%>
    --%>

</body>
</html>
