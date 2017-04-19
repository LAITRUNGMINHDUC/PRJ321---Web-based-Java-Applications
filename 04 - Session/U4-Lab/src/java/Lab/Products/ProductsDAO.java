/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab.Products;

import Lab.Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author duclt
 */
public class ProductsDAO {

    private List<ProductsDTO> listProducts;

    public void loadListProduct() throws NamingException, SQLException {
        Connection c = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        String SQL = "SELECT * FROM Products";
        try {
            c = DBUtils.makeConnection();
            stm = c.prepareStatement(SQL);
            rs = stm.executeQuery();

            while (rs.next()) {
                if (listProducts == null) {
                    listProducts = new ArrayList<>();
                }

                String ProductID = rs.getString("ProductID");
                String ProductName = rs.getString("ProductName");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                int Price = rs.getInt("Price");
                ProductsDTO dto = new ProductsDTO(ProductID, ProductName, QuantityPerUnit, Price);
                listProducts.add(dto);
            }

        } finally {
            DBUtils.closeConnection(c, stm, rs);
        }

    }

    public ProductsDTO getProduct(String searchTerm) throws NamingException, SQLException {
        Connection c = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        String SQL = "SELECT * FROM Products WHERE ProductID = ?";
        try {
            c = DBUtils.makeConnection();
            stm = c.prepareStatement(SQL);
            stm.setString(1, searchTerm);
            rs = stm.executeQuery();

            if (rs.next()) {
                String ProductID = rs.getString("ProductID");
                String ProductName = rs.getString("ProductName");
                String QuantityPerUnit = rs.getString("QuantityPerUnit");
                int Price = rs.getInt("Price");
                ProductsDTO dto = new ProductsDTO(ProductID, ProductName, QuantityPerUnit, Price);

                return dto;
            } else {
                return null;
            }

        } finally {
            DBUtils.closeConnection(c, stm, rs);
        }

    }

    public List<ProductsDTO> getListProducts() {
        return listProducts;
    }
}
