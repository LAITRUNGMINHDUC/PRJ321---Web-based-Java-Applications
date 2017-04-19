/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass;

import ass.account.AccountSessionBeanLocal;
import ass.account.TblAccount;
import ass.transaction.TblTransaction;
import ass.transaction.TransactionSessionBeanLocal;
import ass.transaction.TransactionSessionBeanRemote;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import project.utils.Function;
import utils.ENV;

/**
 *
 * @author duclt
 */
public class PrintTransactionServlet extends HttpServlet {

    @EJB
    private AccountSessionBeanLocal accountSessionBean;

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

        TransactionSessionBeanRemote transactionSessionBean = null;

        try {
            Context context = new InitialContext();
            Object obj = context.lookup("TransactionJNDI");
            transactionSessionBean = (TransactionSessionBeanRemote) obj;
        } catch (NamingException ex) {
            Logger.getLogger(PrintTransactionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        String url = ENV.PAGE_LOGIN;

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("USER");

        String[] arrTypeTrans = (String[]) session.getAttribute("ARRTYPE");
        if (arrTypeTrans == null) {
            arrTypeTrans = new String[]{"Withdrawn", "Deposit", "Transfer", "Profit"};
            session.setAttribute("ARRTYPE", arrTypeTrans);
        }

        try {
            if (username != null) {
                String txtFrom = request.getParameter("txtFrom");
                String txtTo = request.getParameter("txtTo");

                request.setAttribute("FROM", txtFrom);
                request.setAttribute("TO", txtTo);

                Date dateFrom = Function.validDate(txtFrom);
                Date dateTo = Function.validDate(txtTo);

                List<String> errList = new ArrayList<String>();

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

                TblAccount dto = accountSessionBean.getAccountById(username);
                request.setAttribute("USERINFO", dto);

                if (errList.size() > 0) {
                    request.setAttribute("ERROR", errList);
                    url = ENV.PAGE_PRINT;
                }

                if (dateFrom != null && dateTo != null && dateTo.compareTo(dateFrom) >= 0) {
                    List<TblTransaction> list = transactionSessionBean.makeListTransaction(username, dateFrom, dateTo);
                    url = ENV.PAGE_PRINT;
                    request.setAttribute("PRINTRESULT", list);
                }
            } else {
                url = ENV.CONTROLLER;
            }
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
