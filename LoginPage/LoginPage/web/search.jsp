<%-- 
    Document   : search
    Created on : Jan 13, 2017, 11:11:32 AM
    Author     : TuanHDSE62146
--%>

<%@page import="sample.studentDAO.RegistratorDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>
        <font color="red">
            <%
                Cookie[] cookies = request.getCookies();
                if (cookies != null){
                    String username = "";
                    for (Cookie cookie : cookies) {
                            String temp = cookie.getName();
                            if (!temp.equals("JSESSIONID")){
                                username = temp;
                            }
                        }
                    %>
                        Welcome, <%= username%>
                    <%
                }
            %>
        </font>
        <h1>Search Page</h1>
        <form action="TestServlet">
            Search Value <input type="text" name="txtSearchValue" value="" /> <br/>
            <input type="submit" value="Search" name="btnLogin" />
        </form>
        <br/>
        <%
            String searchValue = request.getParameter("txtSearchValue");
            if (searchValue != null) {
                List<RegistratorDTO> result = (List<RegistratorDTO>) request.getAttribute("SEARCHRESULT");
                if (result != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Lastname</th>
                    <th>Role</th>                           
                    <th>Delete</th>                           
                    <th>Update</th>                           
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (RegistratorDTO dto : result) {
                        String urlRewriting = "TestServlet?btnLogin=Delete&pk=" + dto.getUsername() + "&lastSearchValue=" + searchValue;
                %>
            <form action="TestServlet">
                <tr>
                    <td>
                        <%= ++count%>
                        .</td>
                    <td>
                        <%= dto.getUsername()%>
                        <input type="hidden" name="txtUsername" value="<%= dto.getUsername()%>" />
                    </td>
                    <td>
                        <input type="text" name="txtPassword" value="<%= dto.getPassword()%>" />
                    </td>
                    <td>
                        <%= dto.getLastname()%>
                    </td>
                    <td>
                        <input type="checkbox" name="chkRole" value="ADMIN" 
                               <%
                                   if (dto.isRole()) {
                               %>checked="checked"<%
                                            }
                               %>
                               />
                    </td>        
                    <td>
                        <a href="<%= urlRewriting%>">Delete</a>
                    </td>
                    <td>
                        <input type="hidden" name="lastSearchValue" value="<%= searchValue%>" />
                        <input type="submit" value="Update" name="btnLogin"/>
                    </td>
                </tr>
            </form>
            <%
                }
            %>
        </tbody>
    </table>

    <%
    } else {
    %>
    <h2>No record is matched</h2>
    <%
            }
        } //end if searchValue
%>
</body>
</html>
