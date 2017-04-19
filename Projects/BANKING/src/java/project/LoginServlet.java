/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import project.account.AccountDAO;
import project.utils.ENV;

/**
 *
 * @author duclt
 */
public class LoginServlet extends HttpServlet {

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
        Date currDate = new Date();
        Date sessionDate = (Date) session.getAttribute("DATESESSION");
        Integer timeLoginFailed = (Integer) session.getAttribute("LOGINFAILED");

        try {
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");

            AccountDAO dao = new AccountDAO();
            if (dao.checkLogin(username, password)) {
                Cookie cookie = new Cookie("USER", username);

                session.setAttribute("LOGINFAILED", null);
                session.setAttribute("DATESESSION", null);

                cookie.setMaxAge(15 * 60);
                response.addCookie(cookie);
                response.sendRedirect(ENV.PAGE_TRANSACTION);
            } else {
                if (timeLoginFailed == null) {
                    timeLoginFailed = 1;
                } else {
                    timeLoginFailed = timeLoginFailed + 1;
                }
                session.setAttribute("LOGINFAILED", timeLoginFailed);
                session.setAttribute("DATESESSION", currDate);
                response.sendRedirect(ENV.PAGE_ERRORLOGIN);
            }
        } catch (NamingException ex) {
            log("Exception DataSource: " + ex.getMessage());
        } catch (SQLException ex) {
            log("Exception SQL: " + ex.getMessage());
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
