/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab;

import Lab.Products.ProductsDAO;
import Lab.Products.ProductsDTO;
import Lab.Utils.DBUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author duclt
 */
public class DisplayServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>All Products</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>All Products</h1>");

            Connection c = DBUtils.makeConnection();
            ProductsDAO dao = new ProductsDAO();
            List<ProductsDTO> listProducts = dao.LoadAll(c);

            if (listProducts != null) {
                out.println("<table border='1'>");
                out.println("<thead>");
                out.println("<tr>");
                out.println("<th>Code</th>");
                out.println("<th>Name</th>");
                out.println("<th>Description</th>");
                out.println("<th>Price</th>");
                out.println("<th>Category</th>");
                out.println("</tr>");
                out.println("</thead>");
                out.println("<tbody>");

                for (ProductsDTO x : listProducts) {
                    out.println("<tr>");
                    out.println("<th>"
                            + x.getCode()
                            + "</th>");
                    out.println("<th>"
                            + x.getName()
                            + "</th>");
                    out.println("<th>"
                            + x.getDescription()
                            + "</th>");
                    out.println("<th>"
                            + x.getPrice()
                            + "</th>");
                    out.println("<th>"
                            + x.getCategory()
                            + "</th>");
                    out.println("</tr>");
                }
                out.println("</tbody>");
                out.println("</table>");
            } else {
                out.println("<h2>Nothing to show</h2>");
            }

            out.println("</body>");
            out.println("</html>");
        } catch (NamingException | SQLException ex) {
            ex.printStackTrace();
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
