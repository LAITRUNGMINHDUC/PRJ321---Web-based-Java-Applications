<%-- 
    Document   : viewDetails
    Created on : Jan 19, 2017, 5:48:42 PM
    Author     : duclt
--%>

<%@page import="Lab.Account.AccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ViewDetails Page</title>
    </head>
    <body>
        <h1>Information Details</h1>        
        <%
            AccountDTO dto = (AccountDTO) request.getAttribute("USER");
            if (dto != null) {
                out.println("Username: " + dto.getUsername() + "<br>");
                out.println("Lastname: " + dto.getLastname() + "<br>");
                String roleStr = "User";
                if (dto.isRole()) {
                    roleStr = "Admin";
                }
                out.println("Role: " + roleStr);
            } else {
        %>
        <p>No permission to Access</p> 
        <%        
            }
        %>        
        <h2>Click <a href="Controller?btAction=Search">
                here</a> to return search pages</h2>
    </body>
</html>
