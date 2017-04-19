<%-- 
    Document   : datagrid
    Created on : Feb 15, 2017, 11:30:17 AM
    Author     : duclt
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="dataSource" required="true"%>
<%@attribute name="sqlStatement" required="true"%>
<%@tag dynamic-attributes="paramList"%>
<%-- Dynamic-attributes: Sorted collection --%>

<%-- any content can be specified here e.g.: --%>

<%-- Create connection --%>
<c:catch var="ex">
    <sql:setDataSource dataSource="${dataSource}" var="con"/>
    <c:if test="${not empty con}">
        <sql:query var="rs" dataSource="${con}">
            ${sqlStatement}
            <c:forEach var="entry" items="${paramList}">
                <sql:param value="${entry.value}"/>
            </c:forEach>
        </sql:query>

        <%-- Code table --%>
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
    ${ex}
    </font>
</c:if>

