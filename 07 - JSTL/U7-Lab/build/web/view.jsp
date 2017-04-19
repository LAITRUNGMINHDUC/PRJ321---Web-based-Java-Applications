<%-- 
    Document   : welcome
    Created on : Feb 10, 2017, 10:53:09 AM
    Author     : duclt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="myTag"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:useBean id="loginAttr" class="lab.javabean.LoginBean" scope="session"/>
        <font color="red">
        Welcome, <jsp:getProperty name="loginAttr" property="username"/>
        </font>

        <font color="blue">
        Welcome, ${sessionScope.loginAttr.username}
        <%--
            Sai SessionScope --> Chuoi trong
            Sai login --> Chuoi trong
            Sai username --> 500 Err
        --%>
        </font>

        <h1>MVC1 Demo</h1>
        Par1: ${param.par1} <br>
        Par2: ${param.par2} <br>


        <h1>Form HTML</h1>

        <form action="view.jsp">
            Search Value <input type="text" name="txtSearchValue" 
                                value="${param.txtSearchValue}" /> <br>
            <input type="submit" value="Search" />
        </form>

        <c:set var="searchValue" value="${param.txtSearchValue}"/>

        <h3>SHOW SEARCH</h3>
        <c:if test="${not empty searchValue}">
            <c:catch var="ex">
                <sql:setDataSource var="con" dataSource="JAVALAB" />
                <c:if test="${not empty con}">
                    <sql:query dataSource="${con}" var="rs">
                        SELECT Username AS ID, Password AS Secret, Lastname AS Fullname, isAdmin AS Role
                        FROM Registration
                        WHERE Lastname LIKE ? 
                        <sql:param value="%${searchValue}%"/>
                    </sql:query>

                    <c:if test="${rs.rowCount gt 0}">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                        <c:forEach var="colName" items="${rs.columnNames}">
                                        <th>
                                            ${colName}
                                        </th>
                                    </c:forEach>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${rs.rowsByIndex}" var="row" varStatus="counter">
                                    <tr>
                                        <td>
                                            ${counter.count}
                                        </td>
                                        <c:forEach var="col" items="${row}">
                                            <td>
                                                ${col}
                                            </td>
                                        </c:forEach>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>

                    </c:if>
                </c:if>
            </c:catch>

            <c:if test="${not empty ex}">
                <font color="red">
                Errors occur: ${ex}
                </font>
            </c:if>

        </c:if>



        <h3>SHOW ALL DB</h3>
        <myTag:datagrid
            uri="jdbc:sqlserver://localhost:1433;databaseName=JAVALAB"
            user="sa"
            pass="123456789"
            sql="SELECT * FROM Registration"/>

    </body>
</html>
