/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
public class Controller extends HttpServlet {

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
        String url = ENV.PAGE_LOGIN;
        //Set RequestDispatcher to Login Page for default
        RequestDispatcher rd = null;

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("USER");

        //Module test time 3 times --> 5 minutes
        Date currDate = new Date();
        Date sessionDate = (Date) session.getAttribute("DATESESSION");
        Integer timeLoginFailed = (Integer) session.getAttribute("LOGINFAILED");
        if (sessionDate != null && timeLoginFailed != null) {
            long diff = currDate.getTime() - sessionDate.getTime();
            diff = diff / (1000 * 60);
            // Milisecond --> Second --> Minutes
            if (diff < 5 && timeLoginFailed >= 3) {
                response.sendRedirect(ENV.PAGE_ERRORLOCK);
                return;
            }
        }

        try {
            if (btAction != null && username != null) {
                if (btAction.equals("Transaction")) {
                    url = ENV.SERVLET_TRANSACTION;
                } else if (btAction.equals("PrintTransaction")) {
                    url = ENV.SERVLET_PRINT_TRANSACTION;
                } else if (btAction.equals("Hide")) {
                    url = ENV.SERVLET_HIDE_TRANSACTION;
                } else if (btAction.equals("Transfer")) {
                    url = ENV.SERVLET_TRANSFER;
                } else if (btAction.equals("ProcessTransfer")) {
                    url = ENV.SERVLET_PROCESS_TRANSFER;
                } else if (btAction.equals("Yes")) {
                    url = ENV.PAGE_TRANSACTION;
                } else if (btAction.equals("No")) {
                    url = ENV.SERVLET_LOGOUT;
                } else {
                    url = ENV.PAGE_TRANSACTION;
                }
            }

            if (btAction != null && username == null) {
                if (btAction.equals("Login")) {
                    url = ENV.SERVLET_LOGIN;
                } else {
                    url = ENV.PAGE_LOGIN;
                }
            }

            if (btAction == null && username == null) {
                url = ENV.PAGE_LOGIN;
            }

            if (btAction == null && username != null) {
                url = ENV.PAGE_TRANSACTION;
            }
        } finally {
            rd = request.getRequestDispatcher(url);
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
