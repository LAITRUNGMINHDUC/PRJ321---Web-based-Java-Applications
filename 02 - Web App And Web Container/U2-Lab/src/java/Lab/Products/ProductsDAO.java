/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab.Products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author duclt
 */
public class ProductsDAO {

    public boolean Save(Connection c, ProductsDTO dto) throws SQLException {
        String SQL = "INSERT INTO Products VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stm = c.prepareStatement(SQL);
        stm.setString(1, dto.getCode());
        stm.setString(2, dto.getName());
        stm.setString(3, dto.getDescription());
        stm.setFloat(4, dto.getPrice());
        stm.setString(5, dto.getCategory());

        return stm.executeUpdate() > 0;
    }

    public List<ProductsDTO> LoadAll(Connection c) throws SQLException {
        String SQL = "SELECT * FROM Products";
        PreparedStatement stm = c.prepareStatement(SQL);
        ResultSet rs = stm.executeQuery();

        List<ProductsDTO> listProducts = new ArrayList<>();

        while (rs.next()) {
            String Code = rs.getString("Code");
            String Name = rs.getString("Name");
            String Description = rs.getString("Description");
            float Price = rs.getFloat("Price");
            String Category = rs.getString("Category");

            ProductsDTO dto = new ProductsDTO(Code, Name, Description, Price, Category);
            listProducts.add(dto);
        }

        if (listProducts.isEmpty()) {
            return null;
        }
        return listProducts;
    }
}
