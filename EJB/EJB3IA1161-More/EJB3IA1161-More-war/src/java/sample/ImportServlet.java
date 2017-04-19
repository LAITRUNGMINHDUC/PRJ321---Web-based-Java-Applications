/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author duclt
 */
public class ImportServlet extends HttpServlet {

    private final String searchDispatcher = "Controller?btAction=Search&txtSearchValue=";

    @EJB
    private WarehouseSessionBeanLocal warehouseSessionBean;

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
        String[] listCheck = request.getParameterValues("chkItem");
        String searchValue = request.getParameter("txtSearchValue");
        String url = searchDispatcher + searchValue;
        String noti = "";
        try {
            if (listCheck != null) {
                for (String proId : listCheck) {
                    int price = Integer.parseInt(request.getParameter("txtPrice_" + proId));
                    int quantity = Integer.parseInt(request.getParameter("txtQuantity_" + proId));
                    warehouseSessionBean.addProduct(proId, quantity, price);
                    noti = noti + proId + ";";
                }
                RequestDispatcher rd = request.getRequestDispatcher(url);
                request.setAttribute("NOTI", "Import " + noti + " OK");
                rd.forward(request, response);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
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
