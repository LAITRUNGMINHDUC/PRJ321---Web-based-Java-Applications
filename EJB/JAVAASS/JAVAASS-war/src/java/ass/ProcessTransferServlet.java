/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass;

import ass.account.AccountSessionBeanLocal;
import ass.account.TblAccount;
import ass.transaction.TransactionSessionBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.ENV;

/**
 *
 * @author duclt
 */
public class ProcessTransferServlet extends HttpServlet {

    @EJB
    private TransactionSessionBeanLocal transactionSessionBean;

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

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("USER");

        if (username != null) {            
            String txtAmount = request.getParameter("txtAmount").trim();
            String txtAccount = request.getParameter("txtAccount").trim();

            String urlErrDispatcher = ENV.DISPATCHER + "Transfer";
            request.setAttribute("AMOUNT", txtAmount);
            request.setAttribute("ACCOUNT", txtAccount);

            RequestDispatcher rd = null;
            List<String> errList = new ArrayList<String>();

            //// Catch empty first --> If catch failed ==> Die
            if (txtAmount.isEmpty()) {
                String err = "Transfer Amount Field MUST NOT empty";
                errList.add(err);
            }
            if (txtAccount.isEmpty()) {
                String err = "Account Field MUST  NOT empty";
                errList.add(err);
            } else if (txtAccount.equals(username)) {
                String err = "You cannot transfer money to yourself";
                errList.add(err);
            }

            if (errList.size() > 0) {
                request.setAttribute("ERROR", errList);
                rd = request.getRequestDispatcher(urlErrDispatcher);
                rd.forward(request, response);
                return;
            }
            //// End catch Empty

            //// Parse amount to double and catch Error --> Die if Error
            double amount = -1;
            try {
                amount = Double.parseDouble(txtAmount);
                if (amount < 1000) {
                    String err = "Value of Transfer Amount Field MUST > 1000 (VND)";
                    errList.add(err);
                }
            } catch (NumberFormatException e) {
                String err = "Value of Transfer Amount Field have error: " + e.getMessage();
                errList.add(err);
            }
            if (errList.size() > 0) {
                request.setAttribute("ERROR", errList);
                rd = request.getRequestDispatcher(urlErrDispatcher);
                rd.forward(request, response);
                return;
            }
            //// End catch Parse amount

            //// Get ACCOUNT TRANSFER
            TblAccount dtoAccount = null;
            TblAccount dtoUser = null;
            String urlErrDirect = ENV.PAGE_ERROR;
            try {

                dtoAccount = accountSessionBean.getAccountById(txtAccount);
                if (dtoAccount == null) { // Die if unavailable
                    String err = "Unavailable Account in Bank";
                    request.setAttribute("ERROR", err);
                    rd = request.getRequestDispatcher(urlErrDirect);
                    rd.forward(request, response);
                    return;
                }

                dtoUser = accountSessionBean.getAccountById(username);
                double balanceUser = dtoUser.getBalance() - amount;
                double balanceAcc = dtoAccount.getBalance() + amount;

                if (balanceUser < 0) {
                    //Stop here --> Error value < current balance --> Die
                    String err = "User Balance is lower than the Transfer Amount Field";
                    request.setAttribute("ERROR", err);
                    rd = request.getRequestDispatcher(urlErrDirect);
                    rd.forward(request, response);
                    return;
                }

                if (accountSessionBean.updateBalance(txtAccount, balanceAcc)
                        && accountSessionBean.updateBalance(username, balanceUser)) {
                    String noti = "Transfer successfully";
                    request.setAttribute("NOTI", noti);
                    String url = ENV.DISPATCHER + "Transfer";

                    //Write Transaction for User Account                                        
                    transactionSessionBean.addTransaction(username, amount, "NO REASON", 2);

                    //Get new DTO                    
                    TblAccount dto = accountSessionBean.getAccountById(username);
                    request.setAttribute("USERINFO", dto);
                    rd = request.getRequestDispatcher(url);
                    rd.forward(request, response);
                }
            } finally {

            }

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
