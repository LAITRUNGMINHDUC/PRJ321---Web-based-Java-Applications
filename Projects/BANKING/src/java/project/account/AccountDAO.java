/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.account;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import project.utils.DBUtils;

/**
 *
 * @author duclt
 */
public class AccountDAO implements Serializable {

    public boolean checkLogin(String username, String password)
            throws NamingException, SQLException {

        Connection c = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            String SQL = "SELECT * FROM tbl_account WHERE accountID = ? AND pin = ?";

            c = DBUtils.makeConnection();
            stm = c.prepareStatement(SQL);

            stm.setString(1, username);
            stm.setString(2, password);

            rs = stm.executeQuery();

            if (rs.next()) {
                return true;
            }
            return false;
        } finally {
            DBUtils.closeConnection(c, stm, rs);
        }
    }

    private AccountDTO dto;

    public AccountDTO getDto() {
        return dto;
    }

    public AccountDTO getAccountDTO(String username) throws NamingException, SQLException {

        Connection c = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            String SQL = "SELECT * FROM tbl_account WHERE accountID = ?";

            c = DBUtils.makeConnection();
            stm = c.prepareStatement(SQL);

            stm.setString(1, username);

            rs = stm.executeQuery();

            if (rs.next()) {
                String pin = rs.getString("pin");
                double balance = rs.getDouble("balance");
                double benefit = rs.getDouble("benefit");
                boolean expired = rs.getBoolean("isExpired");
                return new AccountDTO(username, pin, balance, benefit, expired);
            }
        } finally {
            DBUtils.closeConnection(c, stm, rs);
        }
        return null;
    }

    public boolean updateAccountDTO(String accountID, double balance) throws NamingException, SQLException {
        Connection c = null;
        PreparedStatement stm = null;

        try {
            c = DBUtils.makeConnection();
            if (c != null) {
                c.setAutoCommit(false);
                String SQL = "UPDATE tbl_account SET balance = ? WHERE accountID = ?";
                stm = c.prepareStatement(SQL);
                stm.setDouble(1, balance);
                stm.setString(2, accountID);
                try {
                    int count = stm.executeUpdate();
                    c.commit();
                    return count > 0;
                } catch (SQLException e) {
                    c.rollback();
                }
                //http://stackoverflow.com/questions/17331132/throwing-exceptions-as-well-as-catching-exceptions
                //Exception in catch is greater priority to THROW                
            }
        } finally {
            DBUtils.closeConnection(c, stm, null);
        }
        return false;
    }

}
