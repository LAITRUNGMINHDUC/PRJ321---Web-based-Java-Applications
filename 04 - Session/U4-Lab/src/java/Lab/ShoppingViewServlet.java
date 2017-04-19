/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab;

import Lab.Products.ProductsDAO;
import Lab.Products.ProductsDTO;
import Lab.Utils.ENV;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author duclt
 */
public class ShoppingViewServlet extends HttpServlet {

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

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Map<ProductsDTO, Integer> listShop = new HashMap<>();

            String cookieName = null;
            ProductsDAO dao = new ProductsDAO();

            //GET luon danh sach cac san pham sap mua...
            for (int i = 0; i < cookies.length; i++) {
                cookieName = cookies[i].getName();
                if (cookieName.contains("Pro-")) {
                    String ProductID = cookieName.replace("Pro-", "");
                    int value = Integer.parseInt(cookies[i].getValue());
                    try {
                        listShop.put(dao.getProduct(ProductID), value);
                    } catch (NamingException | SQLException ex) {
                        Logger.getLogger(ShoppingViewServlet.class.getName()).
                                log(Level.SEVERE, null, ex);
                        ex.printStackTrace();
                    }
                }
            }

            HttpSession session = request.getSession();
            session.setAttribute("CART", listShop);
            response.sendRedirect(ENV.PAGE_SHOPPING_CART);
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
