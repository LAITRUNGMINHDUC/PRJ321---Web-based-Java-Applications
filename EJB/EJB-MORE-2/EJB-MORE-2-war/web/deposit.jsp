<%-- 
    Document   : deposit
    Created on : Mar 10, 2017, 11:31:14 AM
    Author     : duclt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Deposit Page</title>
    </head>
    <body>
        <h1>Hello ${sessionScope.USERNAME}</h1>
        <h1>Deposit Page</h1>

        <h3>${requestScope.NOTI}</h3>

        <form action="Controller" method="POST">
            Amount: <input type="text" name="txtAmount" value="" /> <br>
            Reason: <input type="text" name="txtReason" value="" /> <br>
            <input type="submit" value="SaveDeposit" name="btAction" />
        </form>

    </body>
</html>
