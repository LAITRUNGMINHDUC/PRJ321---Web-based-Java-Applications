/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import project.account.AccountDAO;
import project.account.AccountDTO;
import project.transaction.TransactionDAO;
import project.utils.ENV;
import project.utils.Function;

/**
 *
 * @author duclt
 */
public class ProcessTransferServlet extends HttpServlet {

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

        String username = Function.cookieValue(request, "USER");
        if (username != null) {
            String txtAmount = request.getParameter("txtAmount");
            String txtAccount = request.getParameter("txtAccount");

            String urlErrDispatcher = ENV.DISPATCHER + "Transfer"
                    + "&txtAmount=" + txtAmount
                    + "&txtAccount=" + txtAccount;
            RequestDispatcher rd = null;
            List<String> errList = new ArrayList<>();

            //// Catch empty first --> If catch failed ==> Die
            if (txtAmount.isEmpty()) {
                String err = "Transfer Amount Field MUST NOT empty";
                errList.add(err);
            }
            if (txtAccount.isEmpty()) {
                String err = "Account Field MUST  NOT empty";
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
            AccountDAO dao = new AccountDAO();
            AccountDTO dtoAccount = null;
            AccountDTO dtoUser = null;
            String urlErrDirect = ENV.PAGE_ERROR;
            try {

                dtoAccount = dao.getAccountDTO(txtAccount);
                if (dtoAccount == null) { // Die if unavailable
                    String err = "Unavailable Account in Bank";
                    request.setAttribute("ERROR", err);
                    rd = request.getRequestDispatcher(urlErrDirect);
                    rd.forward(request, response);
                    return;
                }

                dtoUser = dao.getAccountDTO(username);
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

                if (dao.updateAccountDTO(txtAccount, balanceAcc)
                        && dao.updateAccountDTO(username, balanceUser)) {
                    String noti = "Transfer successfully";
                    request.setAttribute("NOTI", noti);
                    String url = ENV.DISPATCHER + "Transfer";

                    //Write Transaction for User Account
                    TransactionDAO daoTrans = new TransactionDAO();
                    daoTrans.writeTransaction(amount, username);

                    //Get new DTO                    
                    AccountDTO dto = dao.getAccountDTO(username);
                    request.setAttribute("USERINFO", dto);
                    rd = request.getRequestDispatcher(url);
                    rd.forward(request, response);
                } else {
                    // Transfer that bai
                    // Roll-back
                    balanceUser = dtoUser.getBalance() + amount;
                    balanceAcc = dtoAccount.getBalance() - amount;
                    dao.updateAccountDTO(txtAccount, balanceAcc);
                    dao.updateAccountDTO(username, balanceUser);

                    String err = "Something error during the transaction. Roll-back";
                    request.setAttribute("ERROR", err);
                    rd = request.getRequestDispatcher(urlErrDirect);
                    rd.forward(request, response);
                }
            } catch (NamingException ex) {
                log("Exception DataSource: " + ex.getMessage());
            } catch (SQLException ex) {
                log("Exception SQL: " + ex.getMessage());
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
