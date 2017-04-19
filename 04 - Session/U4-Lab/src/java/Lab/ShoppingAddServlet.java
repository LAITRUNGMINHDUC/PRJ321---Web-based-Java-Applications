/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab;

import Lab.Utils.ENV;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author duclt
 */
public class ShoppingAddServlet extends HttpServlet {

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
        String ProductID = request.getParameter("ProductID");

        int quantity = 1;
        if (ProductID != null) {
            try {
                ProductID = "Pro-" + ProductID;
                Cookie[] cookies = request.getCookies();
                if (cookies == null) { //Ko ton tai Cookie
                    Cookie cookie = new Cookie(ProductID, "1");
                    cookie.setMaxAge(60 * 10);
                    response.addCookie(cookie);
                } else {
                    boolean Found = false;
                    for (int i = 0; i < cookies.length; i++) {
                        if (cookies[i].getName().equals(ProductID)) {
                            Found = true;
                            String value = cookies[i].getValue();
                            quantity = Integer.parseInt(value) + 1;
                            Cookie cookie = new Cookie(ProductID, String.valueOf(quantity));
                            cookie.setMaxAge(60 * 10);
                            response.addCookie(cookie);
                            break;
                        }
                    }
                    if (!Found) {
                        Cookie cookie = new Cookie(ProductID, "1");
                        cookie.setMaxAge(60 * 10);
                        response.addCookie(cookie);
                    }
                }

                Cookie cookie = new Cookie("NOTI", "Add OK - "
                        + ProductID + " - Total: " + quantity);
                cookie.setMaxAge(60 * 10);
                response.addCookie(cookie);

                response.sendRedirect(ENV.DISPATCH_SHOPPING_LIST);
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
