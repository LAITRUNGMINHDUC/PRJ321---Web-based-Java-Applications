/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package more;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import more.product.ProductSessionBeanLocal;
import more.product.TblProduct;

/**
 *
 * @author duclt
 */
public class UpdateServlet extends HttpServlet {

    @EJB
    private ProductSessionBeanLocal productSessionBean;

    private final String deliveryPage = "deliverynote.jsp";

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
        String[] productId = request.getParameterValues("txtProductId");
        String[] productName = request.getParameterValues("txtProductName");
        String[] productPrice = request.getParameterValues("txtProductPrice");
        String[] productQuantity = request.getParameterValues("txtProductQuantity");

        String customerName = request.getParameter("txtCustomerName");
        String address = request.getParameter("txtAddress");

        if (productId != null && productName != null
                && productPrice != null && productQuantity != null) {
            HttpSession session = request.getSession();
            Map<TblProduct, Integer> listProduct = (Map<TblProduct, Integer>) session.getAttribute("LISTPRODUCT");

            for (int i = 0; i < productId.length; i++) {
                TblProduct prod = new TblProduct(productId[i], productName[i], Double.parseDouble(productPrice[i]));
                listProduct.put(prod, Integer.parseInt(productQuantity[i]));
            }

            session.setAttribute("LISTPRODUCT", listProduct);
            request.setAttribute("CUSTOMERNAME", customerName);
            request.setAttribute("ADDRESS", address);
            
            RequestDispatcher rd = request.getRequestDispatcher(deliveryPage);
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
