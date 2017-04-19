<%-- 
    Document   : transaction
    Created on : Feb 19, 2017, 11:19:50 AM
    Author     : duclt
--%>

<%@page import="project.utils.Function"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transaction Page</title>        
    </head>
    <body>

        <%
            String username = Function.cookieValue(request, "USER");
            //Username == Empty ==> Haven't Login ==> Login Page (Default in Controller)
            if (username == null) {
                response.sendRedirect("Controller");
            }
        %>

        Welcome, <%= username%> <br>

        <h1>Transaction Page</h1>
        <h3>Please choose transaction type below</h3>
        <form action="Controller">
            <input type="radio" name="rdbTransaction" value="Print Transaction" checked="checked" /> Print Transaction<br>
            <input type="radio" name="rdbTransaction" value="Transfer" /> Transfer<br>
            <input type="submit" value="Transaction" name="btAction" />
        </form>

        <h3>            
            <a href="Controller?btAction=No">Logout</a> <br>
        </h3>

    </body>
</html>
