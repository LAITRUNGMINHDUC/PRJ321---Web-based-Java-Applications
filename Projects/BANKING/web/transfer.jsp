<%-- 
    Document   : transfer
    Created on : Feb 19, 2017, 10:57:46 AM
    Author     : duclt
--%>

<%@page import="java.util.List"%>
<%@page import="project.account.AccountDTO"%>
<%@page import="project.utils.Function"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transfer</title>
    </head>
    <body>
        <h1>Transfer Page</h1>

        <%
            String txtAmount = request.getParameter("txtAmount");
            String txtAccount = request.getParameter("txtAccount");

            if (txtAmount == null) {
                txtAmount = "";
            }
            if (txtAccount == null) {
                txtAccount = "";
            }

            String username = Function.cookieValue(request, "USER");
            //Username == Empty ==> Haven't Login ==> Login Page (Default in Controller)
            if (username == null) {
                response.sendRedirect("Controller");
            }
        %>

        <h3>Welcome, <%= username%></h3>

        <%
            String noti = (String) request.getAttribute("NOTI");
            if (noti != null) {
        %>
        <h4><%= noti%></h4>
        <%
            }
            AccountDTO dto = (AccountDTO) request.getAttribute("USERINFO");
            double balance = dto.getBalance();
        %>

        <font color="red">
        <%
            //Print error if have
            List<String> listErr = (List<String>) request.getAttribute("ERROR");
            if (listErr != null) {
                for (String x : listErr) {
                    out.println(x + "<br>");
                }
            }
        %>
        </font>

        <form action="Controller" method="POST">
            <h5>
                Your Balance: <%=balance%> <br>
                Transfer Amount (1000 VND) <input type="text" name="txtAmount" value="<%=txtAmount%>" /> <br>
                Account <input type="text" name="txtAccount" value="<%=txtAccount%>" />
            </h5>
            <input type="submit" value="ProcessTransfer" name="btAction" />
        </form>

        <h3>
            <a href="Controller">Back to Transaction Panel</a> <br>
            <a href="Controller?btAction=No">Logout</a> <br>
        </h3>

    </body>
</html>
