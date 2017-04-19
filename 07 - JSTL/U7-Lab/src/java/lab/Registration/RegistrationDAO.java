/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.Registration;

import lab.Utils.DBUtils;
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
public class RegistrationDAO {

    public boolean checkLogin(String username, String password)
            throws /*ClassNotFoundException,*/ SQLException, NamingException {
        String SQL = "SELECT * FROM Registration WHERE Username = ? AND Password =?";
        try {
            Connection c = DBUtils.makeConnection();
            PreparedStatement stm = c.prepareStatement(SQL);
            stm.setString(1, username);
            stm.setString(2, password);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
        return false;
    }
}
