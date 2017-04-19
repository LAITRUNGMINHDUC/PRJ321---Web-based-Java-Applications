<%-- 
    Document   : index
    Created on : Feb 6, 2017, 9:07:23 PM
    Author     : duclt
--%>

<%@page import="Lab.Utils.ENV"%>
<%@page import="Lab.Utils.FUNC"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Shopping Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>    
    <h3>
        <font color="red">
        <%
            String cookie = FUNC.checkCookie(request,"USERNAME");
            if (cookie != null) {
                response.sendRedirect(ENV.DISPATCH_SHOPPING_LIST);
            }
        %>
        </font>
    </h3>

    <body>        
        <h1>Login to Shopping</h1>
        <form action="Controller" method="POST">
            Username: <input type="text" name="txtUsername" value="" /> <br>
            Password: <input type="password" name="txtPassword" value="" /> <br>
            <input type="submit" value="Login" name="btAction" />
            <input type="reset" value="Reset" />            
        </form>                        
    </body>
</html>