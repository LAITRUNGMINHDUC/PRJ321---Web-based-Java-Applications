/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package more;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import more.delivery.DeliverySessionBeanLocal;
import more.delivery.TblDeliveryNotes;
import more.delivery.TblDeliveryNotesPK;
import more.product.ProductSessionBeanLocal;

/**
 *
 * @author duclt
 */
public class DeliveryServlet extends HttpServlet {

    @EJB
    private ProductSessionBeanLocal productSessionBean;

    @EJB
    private DeliverySessionBeanLocal deliverySessionBean;

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
        String[] productPrice = request.getParameterValues("txtProductPrice");
        String[] productQuantity = request.getParameterValues("txtProductQuantity");

        String customerName = request.getParameter("txtCustomerName");
        String address = request.getParameter("txtAddress");

        for (int i = 0; i < productId.length; i++) {
            deliverySessionBean.addNote(productId[i],
                    Float.parseFloat(productPrice[i]),
                    Integer.parseInt(productQuantity[i]), customerName, address);
        }

        HttpSession session = request.getSession();
        session.invalidate();
        
        RequestDispatcher rd = request.getRequestDispatcher("product.jsp");
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
