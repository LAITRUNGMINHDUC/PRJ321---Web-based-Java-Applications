/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.studentDAO.RegistratorDAO;
import sample.studentDAO.RegistratorInsertError;

/**
 *
 * @author TuanHDSE62146
 */
public class CreateNewAccountServlet extends HttpServlet {

    private final String insertErrorPage = "createNewAccount.jsp";
    private final String loginPage = "createNewAccount.html";

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullName = request.getParameter("txtFullName");
        String url = insertErrorPage;
        RegistratorInsertError errors = new RegistratorInsertError();
        boolean bErr = false;
        try {
            if (username.trim().length() < 6 || username.trim().length() > 20) {
                bErr = true;
                errors.setUsernameLengthErr("Username length requires 6 - 20 chars");
            }
            if (password.trim().length() < 6 || password.trim().length() > 30) {
                bErr = true;
                errors.setPasswordLengthErr("Password length requires 6 - 30 chars");
            } else if (!confirm.trim().equals(password.trim())) {
                bErr = true;
                errors.setConfirmNotMatch("Confirm password not match");
            }
            if (fullName.trim().length() < 2 || password.trim().length() > 50) {
                bErr = true;
                errors.setFullnameLengthErr("Fullname length requires 2 - 50 chars");
            }
            if (bErr) {
                request.setAttribute("INSERTERR", errors);
            } else {
                RegistratorDAO dao = new RegistratorDAO();
                boolean result = dao.insertRecord(username, password, fullName, false);
                if (result){
                    url = loginPage;
                }
            }
        } catch (NamingException e) {
            log("CreateAccountServlet _ Naming" + e.getMessage());
        } catch (SQLException e) {
            log("CreateAccountServlet _ SQL" + e.getMessage());            
            errors.setUsernameIsExisted(username + " is existed in System");
            request.setAttribute("INSERTERR", errors);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
