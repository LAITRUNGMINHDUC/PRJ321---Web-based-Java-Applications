/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import project.account.AccountDAO;
import project.account.AccountDTO;
import project.transaction.TransactionDAO;
import project.transaction.TransactionDTO;
import project.utils.ENV;
import project.utils.Function;

/**
 *
 * @author duclt
 */
public class PrintTransactionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = ENV.PAGE_LOGIN;
        String username = Function.cookieValue(request, "USER");

        if (username != null) {
            String txtFrom = request.getParameter("txtFrom");
            String txtTo = request.getParameter("txtTo");
            Date dateFrom = Function.validDate(txtFrom);
            Date dateTo = Function.validDate(txtTo);

            List<String> errList = new ArrayList<>();
            if (dateFrom == null) {
                String err = "From Date value MUST BE in format yyyy-MM-dd and NOT EMPTY";
                errList.add(err);
            }
            if (dateTo == null) {
                String err = "To Date value MUST BE in format yyyy-MM-dd and NOT EMPTY";
                errList.add(err);
            }
            if (dateFrom != null && dateTo != null && dateTo.compareTo(dateFrom) < 0) {
                String err = "From Date value MUST BE GREATER OR EQUAL to To Date value";
                errList.add(err);
            }

            try {
                AccountDAO dao = new AccountDAO();
                AccountDTO dto = dao.getAccountDTO(username);
                request.setAttribute("USERINFO", dto);

                if (errList.size() > 0) {
                    request.setAttribute("ERROR", errList);
                    url = ENV.PAGE_PRINT;
                }

                if (dateFrom != null && dateTo != null && dateTo.compareTo(dateFrom) >= 0) {
                    TransactionDAO daoTrans = new TransactionDAO();
                    List<TransactionDTO> list = daoTrans.makeListTransaction(txtFrom, txtTo, username);
                    url = ENV.PAGE_PRINT;
                    request.setAttribute("PRINTRESULT", list);
                }

            } catch (NamingException ex) {
                log("Exception DataSource: " + ex.getMessage());
            } catch (SQLException ex) {
                log("Exception SQL: " + ex.getMessage());
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else {
            response.sendRedirect("Controller");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
