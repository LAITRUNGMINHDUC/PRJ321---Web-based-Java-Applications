/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Demo.Dispatcher;

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
public class DispatcherServlet extends HttpServlet {

    private String loginServlet = "LoginServlet";
    private String loginPage = "login.html";
    private String viewCartPage = "viewCart.jsp";
    private String searchServlet = "SearchServlet";
    private String deleteRecordServlet = "DeleteRecordServlet";
    private String updatePassRoleServlet = "UpdatePassRoleServlet";
    private String nullServlet = "NullServlet";
    private String addItemServlet = "AddItemServlet";

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
        RequestDispatcher rd = null;

        if (btAction != null) {
            if (btAction.equals("Login")) {
                rd = request.getRequestDispatcher(loginServlet);
            } else if (btAction.equals("Search")) {
                rd = request.getRequestDispatcher(searchServlet);
            } else if (btAction.equals("Delete")) {
                rd = request.getRequestDispatcher(deleteRecordServlet);
            } else if (btAction.equals("Update")) {
                rd = request.getRequestDispatcher(updatePassRoleServlet);
            } else if (btAction.equals("Add Book to Your Cart")) {
                rd = request.getRequestDispatcher(addItemServlet);
            } else if (btAction.equals("View Your Cart")) {
                rd = request.getRequestDispatcher(viewCartPage);
            }

            //Forward Request scopes
            if (rd != null) {
                rd.forward(request, response);
            }
        } else {
            rd = request.getRequestDispatcher(nullServlet);
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
