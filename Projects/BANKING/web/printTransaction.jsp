<%-- 
    Document   : printTransaction
    Created on : Feb 19, 2017, 10:57:13 AM
    Author     : duclt
--%>

<%@page import="project.transaction.TransactionDTO"%>
<%@page import="java.util.List"%>
<%@page import="project.transaction.TransactionDAO"%>
<%@page import="project.account.AccountDAO"%>
<%@page import="project.account.AccountDTO"%>
<%@page import="project.utils.Function"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Print Transaction</title>
    </head>
    <body>

        <%
            String username = Function.cookieValue(request, "USER");

            //Username == Empty ==> Haven't Login ==> Login Page (Default in Controller)
            if (username == null) {
                response.sendRedirect("Controller");
            }
        %>

        Welcome, <%= username%>

        <%
            //1. Get AccountInfo(DTO)
            AccountDTO dto = (AccountDTO) request.getAttribute("USERINFO");

            //2. Get Parameter
            String txtFrom = request.getParameter("txtFrom");
            String txtTo = request.getParameter("txtTo");

            if (txtFrom == null) {
                txtFrom = "";
            }
            if (txtTo == null) {
                txtTo = "";
            }

        %>
        <table border="0">
            <tbody>
                <tr>
                    <td>Print Transaction</td>
                    <td></td>
                    <td>Your Balance</td>
                    <td><%= dto.getBalance()%></td>
                    <td></td>
                </tr>
            <form action="Controller">
                <tr>
                    <td>From Date</td>
                    <td> <input type="text" name="txtFrom" value="<%= txtFrom%>" /> </td>
                    <td>To Date</td>
                    <td> <input type="text" name="txtTo" value="<%= txtTo%>" /> </td>
                    <td> <input type="submit" value="PrintTransaction" name="btAction" /> </td>
                </tr>
            </form>
        </tbody>
    </table>

    <h3>
        <a href="Controller">Back to Transaction Panel</a> <br>
        <a href="Controller?btAction=No">Logout</a> <br>
    </h3>

    <font color="red">
    <%
        //3. Check Transaction Error
        List<String> listErr = (List<String>) request.getAttribute("ERROR");
        if (listErr != null) {
            for (String x : listErr) {
                out.println("<br>" + x + "<br>");
            }
        }

    %>
    </font>


    <h2>Result</h2>

    <%        //Get Database when Date Valid      
        List<TransactionDTO> list = (List<TransactionDTO>) request.getAttribute("PRINTRESULT");
        if (list != null) {
    %>
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
            <%
                for (TransactionDTO dtoTrans : list) {
            %>
            <tr>
                <td><%= dtoTrans.getTransID()%></td>
                <td><%= dtoTrans.getTransDate()%></td>
                <td><%
                    switch (dtoTrans.getType()) {
                        case 0:
                            out.println("Withdrawn");
                            break;
                        case 1:
                            out.println("Deposit");
                            break;
                        case 2:
                            out.println("Transfer");
                            break;
                        case 3:
                            out.println("Profit");
                            break;
                    }
                    %></td>
                <td><%= dtoTrans.getAmount()%></td>
                <td><%= dtoTrans.getReason()%></td>                
                <td> 
                    <form action="Controller">
                        <input type="hidden" name="txtFrom" value="<%= txtFrom%>" /> 
                        <input type="hidden" name="txtTo" value="<%= txtTo%>" /> 
                        <input type="hidden" name="TransID" value="<%= dtoTrans.getTransID()%>" /> 
                        <input type="submit" value="Hide" name="btAction" />
                    </form>
                </td>
            </tr>

            <%
                }
            %>
        </tbody>
    </table>

    <%
    } else if (listErr == null) {
    %>

    <h3>No data with that period time</h3 >    

    <%
        }
    %>




</body>
</html>
