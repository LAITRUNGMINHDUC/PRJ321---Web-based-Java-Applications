/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab.Account;

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
public class AccountDAO {

    public boolean checkLogin(String username, String password)
            throws NamingException, SQLException {

        return getAccount(username, password) != null;
    }

    private List<AccountDTO> listResult;

    public List<AccountDTO> getListResult() {
        return listResult;
    }

    public void searchLastname(String lastname)
            throws NamingException, SQLException {

        listResult = null;
        String SQL = "SELECT Username, isAdmin FROM Account WHERE Lastname LIKE ?";

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.makeConnection();
            stm = con.prepareStatement(SQL);

            stm.setString(1, "%" + lastname + "%");
            rs = stm.executeQuery();

            while (rs.next()) {
                String username = rs.getString("Username");
                boolean role = rs.getBoolean("isAdmin");
                AccountDTO dto = new AccountDTO(username, lastname, role);
                if (listResult == null) {
                    listResult = new ArrayList<>();
                }
                listResult.add(dto);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public AccountDTO getAccount(String username, String password) throws NamingException, SQLException {
        String SQL = "SELECT * FROM Account WHERE Username = ? AND Password = ?";

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtils.makeConnection();
            stm = con.prepareStatement(SQL);

            stm.setString(1, username);
            stm.setString(2, password);

            rs = stm.executeQuery();

            try {
                if (rs.next()) {
                    boolean role = rs.getBoolean("isAdmin");
                    String lastname = rs.getString("Lastname");
                    return new AccountDTO(username, lastname, role);
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
