<%-- 
    Document   : printTransaction
    Created on : Feb 19, 2017, 10:57:13 AM
    Author     : duclt
--%>

<%@page import="ass.account.TblAccount"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Print Transaction</title>
    </head>
    <body>
        Welcome, ${sessionScope.USER}        

        <c:if test="${not empty requestScope.USERINFO}">
            <c:set var="dto" value="${requestScope.USERINFO}" />
            <form action="Controller">
                <table border="0">
                    <tbody>
                        <tr>
                            <td>Print Transaction</td>
                            <td></td>
                            <td>Your Balance</td>
                            <td>${dto.balance}</td>
                            <td></td>
                        </tr>

                        <tr>
                            <td>From Date</td>
                            <td> <input type="text" name="txtFrom" value="${requestScope.FROM}" /> </td>
                            <td>To Date</td>
                            <td> <input type="text" name="txtTo" value="${requestScope.TO}" /> </td>
                            <td> <input type="submit" value="PrintTransaction" name="btAction" /> </td>
                        </tr>                
                    </tbody>
                </table>
            </form>
        </c:if>

        <h3>
            <a href="Controller">Back to Transaction Panel</a> <br>
            <a href="Controller?btAction=No">Logout</a> <br>
        </h3>

        <font color="red">        
        <c:if test="${not empty requestScope.ERROR}">
            <c:forEach var="err" items="${requestScope.ERROR}">
                <h4>${err}</h4>
            </c:forEach>
        </c:if>
        </font>


        <h2>Result</h2>

        <c:if test="${not empty requestScope.PRINTRESULT}">
            <c:set var="list" value="${requestScope.PRINTRESULT}" />
            <table border="1">
                <thead>
                    <tr>
                        <th>No. </th>
                        <th>Date</th>
                        <th>Type</th>
                        <th>Amount</th>
                        <th>Reason</th>
                        <th>Hide</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="arrType" value="${sessionScope.ARRTYPE}" />
                    <c:forEach items="${list}" var="dtoTrans" varStatus="counter">
                        <tr>
                            <td>${dtoTrans.transId}</td>
                            <td>${dtoTrans.transDate}</td>                                                                        
                            <td>${arrType[dtoTrans.type]}</td>
                            <td>${dtoTrans.amount}</td>
                            <td>${dtoTrans.reason}</td>
                            <td> 
                                <form action="Controller">
                                    <input type="hidden" name="txtFrom" value="${requestScope.FROM}" /> 
                                    <input type="hidden" name="txtTo" value="${requestScope.TO}" /> 
                                    <input type="hidden" name="TransID" value="${dtoTrans.transId}" /> 
                                    <input type="submit" value="Hide" name="btAction" />
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>            
        </c:if>

        <c:if test="${empty requestScope.PRINTRESULT && not empty requestScope.FROM}">
            <h3>Nothing to show between that date</h3>
        </c:if>
    </body>
</html>
