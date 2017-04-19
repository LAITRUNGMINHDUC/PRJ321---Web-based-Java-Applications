/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab.Orders;

import Lab.Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author duclt
 */
public class OrdersDAO {

    public boolean addOrder(OrdersDTO dto) throws NamingException, SQLException {
        Connection c = DBUtils.makeConnection();
        String SQL = "INSERT INTO Orders (CustomerName, ProductIDList, QuantityList, PriceList) "
                + "VALUES (?, ?, ?, ?) ";
        PreparedStatement stm = c.prepareStatement(SQL);
        stm.setString(1, dto.getCustomerName());
        stm.setString(2, dto.getProductIDList());
        stm.setString(3, dto.getQuantityList());
        stm.setString(4, dto.getPriceList());

        if (stm.executeUpdate() > 0) {
            return true;
        }
        return false;
    }
}
