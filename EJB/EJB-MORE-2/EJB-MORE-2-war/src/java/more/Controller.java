/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package more;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author duclt
 */
public class Controller extends HttpServlet {

    private final String loginPage = "login.html";
    private final String loginServlet = "LoginServlet";

    private final String menuPage = "menu.html";
    private final String transactionPage = "transaction.jsp";
    private final String withdrawnPage = "withdrawn.jsp";
    private final String depositPage = "deposit.jsp";

    private final String transactionServlet = "TransactionServlet";
    private final String withdrawnServlet = "WithdrawnServlet";
    private final String depositServlet = "DepositServlet";

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
        String btAction = request.getParameter("btAction");
        String url = loginPage;

        if (btAction != null) {
            if (btAction.equals("Login")) {
                url = loginServlet;
            } else if (btAction.equals("Menu")) {
                url = menuPage;
            } else if (btAction.equals("Deposit")) {
                url = depositPage;
            } else if (btAction.equals("Withdrawn")) {
                url = withdrawnPage;
            } else if (btAction.equals("Transaction")) {
                url = transactionPage;
            } else if (btAction.equals("SaveDeposit")) {
                url = depositServlet;
            } else if (btAction.equals("SaveWithdrawn")) {
                url = withdrawnServlet;
            } else if (btAction.equals("SaveTransaction")) {
                url = transactionServlet;
            }
        }

        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
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
