/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab.Customers;

import Lab.Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author duclt
 */
public class CustomersDAO {
    
    public boolean checkLogin(String username, String password) throws NamingException, SQLException {
        
        Connection c = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        String SQL = "SELECT * FROM Customers WHERE Username = ? AND Password = ?";
        
        try {
            c = DBUtils.makeConnection();
            stm = c.prepareStatement(SQL);
            stm.setString(1, username);
            stm.setString(2, password);
            rs = stm.executeQuery();
            return rs.next();
        } finally {
            DBUtils.closeConnection(c, stm, rs);
        }
    }
}
