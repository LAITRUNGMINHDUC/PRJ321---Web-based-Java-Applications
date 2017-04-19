<%-- 
    Document   : createNewAccount
    Created on : Jan 20, 2017, 11:22:21 AM
    Author     : TuanHDSE62146
--%>

<%@page import="sample.studentDAO.RegistratorInsertError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Account</title>
    </head>
    <body>
        <h1>Create New Account</h1>
        <form action="TestServlet" method="POST">

            <c:set var="errors" value="${requestScope.INSERTER}"/>
            Username* <input type="text" name="txtUsername" value="${param.txtUsername}" />(6 - 20 chars)<br/>

            <c:if test="${not empty errors.usernameLengthErr}">
                <font color="red">
                ${errors.usernameLengthErr}
                </font>
            </c:if>

            Password* <input type="password" name="txtPassword" value="" />(6 - 30 chars)<br/>
            <c:if test="${not empty errors.passwordLengthErr}">
                <font color="red">
                ${errors.passwordLengthErr}
                </font>
            </c:if>

            Confirm* <input type="password" name="txtConfirm" value="" />

            <c:if test="${not empty errors.confirmNotMatch}">
                <font color="red">
                ${errors.confirmNotMatch}
                </font>
            </c:if>

            (<br/>
            Full Name <input type="text" name="txtFullName" value="${param.txtFullName}" />(2 - 50 chars)<br/>

            <c:if test="${not empty errors.fullnameLengthErr}">
                <font color="red">
                ${errors.fullnameLengthErr}
                </font>
            </c:if>

            <c:if test="${not empty errors.usernameIsExisted}">
                <font color="red">
                ${errors.usernameIsExisted}
                </font>
            </c:if>


            <input type="submit" value="Create New Account" name="btnLogin" />
            <input type="reset" value="Reset" />
        </form>

        <%-- <form action="TestServlet" method="POST">
            Username* <input type="text" name="txtUsername"  value="<%= request.getParameter("txtUsername")%>" />(6 - 20 chars)<br/>
            <font color="red">
                <% 
                    RegistratorInsertError errors = (RegistratorInsertError) request.getAttribute("INSERTERR");
                    if (errors != null){
                        if (errors.getUsernameLengthErr()!= null){
                            %>
                                <%= errors.getUsernameLengthErr()%><br/>
            <%
                        }
                    } // end if errors
                %>
            </font>
            Password* <input type="password" name="txtPassword" value="" />(6 - 30 chars)<br/>
            <font color="red">
                <% 
                    errors = (RegistratorInsertError) request.getAttribute("INSERTERR");
                    if (errors != null){
                        if (errors.getPasswordLengthErr()!= null){
                            %>
                                <%= errors.getPasswordLengthErr()%><br/>
            <%
                        }
                    } // end if errors
                %>
            </font>
            Confirm* <input type="password" name="txtConfirm" value="" />(<br/>
            <font color="red">
                <% 
                    errors = (RegistratorInsertError) request.getAttribute("INSERTERR");
                    if (errors != null){
                        if (errors.getConfirmNotMatch()!= null){
                            %>
                                <%= errors.getConfirmNotMatch()%><br/>
            <%
                        }
                    } // end if errors
                %>
            </font>
            Full Name <input type="text" name="txtFullName" value="<%= request.getParameter("txtFullName")%>" />(2 - 50 chars)<br/>
            <font color="red">
                <% 
                    errors = (RegistratorInsertError) request.getAttribute("INSERTERR");
                    if (errors != null){
                        if (errors.getFullnameLengthErr()!= null){
                            %>
                                <%= errors.getFullnameLengthErr()%><br/>
            <%
                        }
                    } // end if errors
                %>
            </font>
            <input type="submit" value="Create New Account" name="btnLogin" />
            <input type="reset" value="Reset" />
        </form><br/>
        <font color="red">
                <% 
                    errors = (RegistratorInsertError) request.getAttribute("INSERTERR");
                    if (errors != null){
                        if (errors.getUsernameIsExisted()!= null){
                            %>
                                <%= errors.getUsernameIsExisted()%><br/>
            <%
                        }
                    } // end if errors
                %>
            </font>--%>

    </body>
</html>
