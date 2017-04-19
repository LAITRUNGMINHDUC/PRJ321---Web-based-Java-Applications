<%-- 
    Document   : errPage
    Created on : Feb 12, 2017, 12:42:25 PM
    Author     : duclt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Error Page</h1>
        <h3>Error occur due to 
            <%
                String err = (String) request.getAttribute("ERROR");
                if (err != null) {
                    out.println(err);
                }
            %>
        </h3>        
        <h2>Continue to other transaction?</h2>
        
        <form action="Controller" method="POST">
            <input type="submit" value="Yes" name="btAction" />
            <input type="submit" value="No" name="btAction" />
        </form>
    </body>
</html>
