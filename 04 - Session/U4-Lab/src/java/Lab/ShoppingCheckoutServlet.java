/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab;

import Lab.Orders.OrdersDAO;
import Lab.Orders.OrdersDTO;
import Lab.Products.ProductsDTO;
import Lab.Utils.ENV;
import Lab.Utils.FUNC;
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
public class ShoppingCheckoutServlet extends HttpServlet {

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

        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        if (session != null) {
            Map<ProductsDTO, Integer> listCart
                    = (HashMap<ProductsDTO, Integer>) session.getAttribute("CART");

            String CustomerName = FUNC.checkCookie(request, "USERNAME");
            String ProductIDList = "";
            String QuantityList = "";
            String PriceList = "";

            for (Map.Entry<ProductsDTO, Integer> x : listCart.entrySet()) {
                ProductsDTO dto = x.getKey();
                ProductIDList = ProductIDList + dto.getProductID() + ";";
                QuantityList = QuantityList + x.getValue() + ";";
                PriceList = PriceList + dto.getPrice() + ";";

                Cookie cookie = new Cookie("Pro-" + dto.getProductID(), null);
                cookie.setMaxAge(0);
                //cookie.setPath("/");
                response.addCookie(cookie);
            }

            OrdersDAO dao = new OrdersDAO();
            OrdersDTO dto = new OrdersDTO(CustomerName, ProductIDList, QuantityList, PriceList);
            try {
                if (dao.addOrder(dto)) {
                    Cookie cookie = new Cookie("NOTI", "Add Orders OK");
                    cookie.setMaxAge(5);
                    response.addCookie(cookie);

                    //Delete Session
                    session.setAttribute("CART", null);
                } else {
                    Cookie cookie = new Cookie("NOTI", "Add Orders NOT OK");
                    cookie.setMaxAge(5);
                    response.addCookie(cookie);
                }
            } catch (NamingException | SQLException ex) {
                Logger.getLogger(ShoppingCheckoutServlet.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                response.sendRedirect(ENV.DISPATCH_SHOPPING_LIST);
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
