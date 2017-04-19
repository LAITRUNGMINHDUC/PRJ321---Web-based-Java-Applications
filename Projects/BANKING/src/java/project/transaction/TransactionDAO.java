/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import project.utils.DBUtils;

/**
 *
 * @author duclt
 */
public class TransactionDAO {

    public List<TransactionDTO> makeListTransaction(String fromDate, String toDate, String username) throws NamingException, SQLException {
        Connection c = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        List<TransactionDTO> list = null;

        try {
            String SQL = "SELECT * FROM tbl_transaction WHERE "
                    + "(transDate BETWEEN ? AND ?) AND accountID = ? AND status = '0'";
            c = DBUtils.makeConnection();
            stm = c.prepareStatement(SQL);
            stm.setString(1, fromDate);
            stm.setString(2, toDate);
            stm.setString(3, username);
            rs = stm.executeQuery();

            while (rs.next()) {
                String transID = rs.getString("transID");
                String transDate = rs.getDate("transDate").toString();
                int type = rs.getInt("type");
                double amount = rs.getDouble("amount");
                String reason = rs.getString("reason");
                String accountID = username;
                boolean status = rs.getBoolean("status");

                TransactionDTO dto = new TransactionDTO(transID, transDate, type, amount, reason, accountID, status);
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(dto);
            }

            return list;
        } finally {
            DBUtils.closeConnection(c, stm, rs);
        }
    }

    public boolean hideTransaction(String TransID) throws NamingException, SQLException {
        Connection c = null;
        PreparedStatement stm = null;

        try {
            c = DBUtils.makeConnection();
            if (c != null) {
                String SQL = "UPDATE tbl_transaction SET status = '1' WHERE transID = ?";
                stm = c.prepareStatement(SQL);
                stm.setString(1, TransID);
                int rowsAffected = stm.executeUpdate();
                c.commit();
                return rowsAffected > 0;
            }
        } finally {
            DBUtils.closeConnection(c, stm, null);
        }

        return false;
    }

    public boolean writeTransaction(double amount, String username) throws NamingException, SQLException {
        Connection c = null;
        PreparedStatement stm = null;

        try {
            c = DBUtils.makeConnection();
            if (c != null) {
                String SQL = "INSERT INTO tbl_transaction (type, amount, reason, accountID) "
                        + "VALUES ('2', ?, 'NO REASON', ?)";
                stm = c.prepareStatement(SQL);
                stm.setDouble(1, amount);
                stm.setString(2, username);
                return stm.executeUpdate() > 0;
            }
        } finally {
            DBUtils.closeConnection(c, stm, null);
        }
        return false;
    }
}
