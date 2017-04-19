<%-- 
    Document   : view
    Created on : Jan 19, 2017, 5:48:31 PM
    Author     : duclt
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="Lab.Account.AccountDTO"%>
<%@page import="Lab.Utils.AppENV"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Page</title>
    </head>
    <body>
        <font color ="red">
        <%
            Cookie[] cookies = request.getCookies();
            String USERNAME = null;
            if (cookies != null) {
                for (Cookie x : cookies) {
                    String tmp = x.getName();
                    if (!tmp.equals("JSESSIONID")) {
                        USERNAME = tmp;
                    }
                }
            }

            if (USERNAME != null) {
                out.println("Hello " + USERNAME);
            } else {
                response.sendRedirect(AppENV.PAGE_LOGIN);
            }
        %>
        </font>
        <h1>Information Page</h1>
        <form action="Controller">
            Lastname <input type="text" name="txtLastname" value="" /> <br>
            <input type="submit" value="Search" name="btAction" />
        </form>

        <%
            List<AccountDTO> listResult
                    = (List<AccountDTO>) request.getAttribute("RESULT");
            if (listResult != null) {
        %>
        <h2>Result of Searching</h2>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Username</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (AccountDTO dto : listResult) {
                        String url = "Controller?btAction=View&txtUsername=" + dto.getUsername();
                %>
                <tr>
                    <td><%= ++count%></td>
                    <td><%= dto.getUsername()%></td>
                    <td><a href="<%= url%>">ViewDetail</a></td>
                </tr>
                <%             }
                %>
            </tbody>
        </table>

        <%            } else {
                out.println("<h2>No data for search query</h2>");
            }
        %>
    </body>
</html>
