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

        <jsp:useBean id="loginAttr" class="sample.javabean.LoginBean" scope="session"/>
        <%-- scope="session" là tạo và lưu session --%>

        <%--
        <jsp:setProperty name="loginAttr" property="username" 
        value='<%= request.getParameter("txtUsername")%>' />
        <jsp:setProperty name="loginAttr" property="password" 
        value='<%= request.getParameter("txtPassword")%>' />
        --%>

        <%--
        <jsp:setProperty name="loginAttr" property="username" param="txtUsername" />
        <jsp:setProperty name="loginAttr" property="password" param="txtPassword" />
        --%>        
        
        <%--
        <jsp:setProperty name="loginAttr" property="username" />
        <jsp:setProperty name="loginAttr" property="password" />
        --%>

        <jsp:setProperty name="loginAttr" property="*" />

        <c:if test="${loginAttr.checkLogin()}">
            <jsp:forward page="welcome.jsp">
                <jsp:param name="par1" value="JSP" />
                <jsp:param name="par2" value="Forward" />            
            </jsp:forward>
        </c:if>

        <h2>
            <font color="red">
            Invalid username or password !!
            </font>
        </h2>    
        
        <%--
        <%
            if (loginAttr.checkLogin()) {
        %>
        <jsp:forward page="welcome.jsp">
            <jsp:param name="par1" value="JSP" />
            <jsp:param name="par2" value="Forward" />            
        </jsp:forward>
        <%
            }
            System.out.println("Di Den Day");
        %>        
        --%>

        <%--
        <%
            if (loginAttr.checkLogin()) {
                response.sendRedirect("welcome.jsp");
            }
            else {
                %>
                <h2>
                    <font color="red">
                        Invalid username or password !!
                    </font>
                </h2>
        <%
            }
        %>
        --%>

        <h2>Test</h2>
        <%--
        Username: <jsp:getProperty name="loginAttr" property="username" /> <br>
        Password: <jsp:getProperty name="loginAttr" property="password" /> <br>
        --%>

    </body>
</html>
