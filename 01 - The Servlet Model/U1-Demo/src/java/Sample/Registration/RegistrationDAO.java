/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sample.Registration;

import Sample.Utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author duclt
 */
public class RegistrationDAO {

    public boolean checkLogin(String username, String password)
            throws SQLException, ClassNotFoundException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = DBUtils.makeConnection();
            
            if (con != null) {
                String sql = "SELECT * FROM Registration WHERE Username = ? AND Password = ?";
                
                stm = con.prepareStatement(sql);
                
                // Câu lệnh Prepared Statement có thứ tự tham số đi từ 1
                stm.setString(1, username);
                stm.setString(2, password);
                
                rs = stm.executeQuery();
                
                if (rs.next()) {
                    return true;
                }
            }
            
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
