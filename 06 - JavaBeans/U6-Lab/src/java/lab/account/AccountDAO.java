/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import lab.utils.DBUtils;

/**
 *
 * @author duclt
 */
public class AccountDAO {

    public boolean checkLogin(String username, String password)
            throws NamingException, SQLException {
        Connection c = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            c = DBUtils.makeConnection();
            String SQL = "SELECT * FROM TBL_Account WHERE Username = ? AND Password = ?";
            stm = c.prepareStatement(SQL);
            stm.setString(1, username);
            stm.setString(2, password);
            rs = stm.executeQuery();

            return rs.next();
        } finally {
            DBUtils.closeConnection(c, stm, rs);
        }
    }

    public AccountDTO checkInfo(String username)
            throws NamingException, SQLException {
        Connection c = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            c = DBUtils.makeConnection();
            String SQL = "SELECT * FROM TBL_Account WHERE Username = ?";
            stm = c.prepareStatement(SQL);
            stm.setString(1, username);
            rs = stm.executeQuery();

            if (rs.next()) {
                String password = rs.getString("Password");
                String lastname = rs.getString("Lastname");
                boolean isAdmin = rs.getBoolean("isAdmin");

                AccountDTO dto = new AccountDTO(username, password, lastname, isAdmin);
                return dto;
            }
        } finally {
            DBUtils.closeConnection(c, stm, rs);
        }
        return null;
    }

    private List<String> listLastName = null;

    public List<String> getListLastName() {
        return listLastName;
    }

    public void makeListLastName(String lastname) throws NamingException, SQLException {
        String SQL = "SELECT * FROM TBL_Account WHERE Lastname LIKE ?";
        Connection c = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            c = DBUtils.makeConnection();
            stm = c.prepareStatement(SQL);
            stm.setString(1, "%" + lastname + "%");
            rs = stm.executeQuery();

            while (rs.next()) {
                if (listLastName == null) {
                    listLastName = new ArrayList();
                }
                listLastName.add(rs.getString("Username"));
            }
        } finally {
            DBUtils.closeConnection(c, stm, rs);
        }
    }

    public void makeListLastName() throws NamingException, SQLException {
        String SQL2 = "SELECT * FROM TBL_Account";
        Connection c = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            c = DBUtils.makeConnection();
            stm = c.prepareStatement(SQL2);

            rs = stm.executeQuery();

            while (rs.next()) {
                if (listLastName == null) {
                    listLastName = new ArrayList();
                }
                listLastName.add(rs.getString("Username"));
            }
        } finally {
            DBUtils.closeConnection(c, stm, rs);
        }
    }

}
