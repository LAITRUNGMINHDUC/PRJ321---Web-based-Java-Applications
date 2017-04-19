<%-- 
    Document   : index
    Created on : Jan 19, 2017, 5:43:13 PM
    Author     : duclt
--%>

<%@page import="Lab.Utils.AppENV"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>

        <% 
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie x: cookies) {
                    if (!x.getName().equals("JSESSIONID")) {
                        response.sendRedirect(AppENV.PAGE_VIEW);
                    }
                }                
            }
        %>

        <h1>Login Page</h1>
        <form action="Controller" method="POST">
            Username: <input type="text" name="txtUsername" value="" /> <br>
            Password: <input type="password" name="txtPassword" value="" /> <br>
            <input type="submit" value="Login" name="btAction" />
            <input type="reset" value="Reset" />
        </form>
    </body>
</html>
