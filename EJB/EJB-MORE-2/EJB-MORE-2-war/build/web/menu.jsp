<%-- 
    Document   : menu
    Created on : Mar 10, 2017, 11:22:13 AM
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
        <h1>Hello ${sessionScope.USERNAME}</h1>
        <h2>Your balance: ${requestScope.BALANCE}</h2>
        
        <form action="Controller">
            1: <input type="submit" value="Deposit" name="btAction" /> <br>
            2: <input type="submit" value="Withdrawn" name="btAction" /> <br>
            3: <input type="submit" value="Transaction" name="btAction" /> <br>
        </form>
        

    </body>
</html>
