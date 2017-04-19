/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Demo.Registration;

import Demo.Utils.DBUtils;
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

    private List<RegistrationDTO> listAccounts;

    public void searchLastname(String searchValue) throws NamingException {
        try {
            Connection c = DBUtils.makeConnection();
            String SQL = "SELECT * FROM Registration WHERE Lastname LIKE ?";
            PreparedStatement stm = c.prepareStatement(SQL);
            stm.setString(1, "%" + searchValue + "%");
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                String lastname = rs.getString("Lastname");
                boolean role = rs.getBoolean("isAdmin");

                RegistrationDTO dto
                        = new RegistrationDTO(username, password, lastname, role);

                if (this.listAccounts == null) {
                    this.listAccounts = new ArrayList<>();
                }

                this.listAccounts.add(dto);
            }
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
    }

    public List<RegistrationDTO> getListAccounts() {
        return listAccounts;
    }

    public boolean deleteRecord(String username) throws SQLException, NamingException {
        String SQL = "DELETE FROM Registration WHERE Username = ?";
        try (Connection c = DBUtils.makeConnection();
                PreparedStatement stm = c.prepareStatement(SQL);) {
            System.out.println(username);
            stm.setString(1, username);
            int row = stm.executeUpdate();
            if (row > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean updateRecord(String username, String password, boolean role) throws SQLException, NamingException {
        String SQL = "UPDATE Registration SET Password = ?, isAdmin = ? WHERE Username = ?";

        try (Connection c = DBUtils.makeConnection();
                PreparedStatement stm = c.prepareStatement(SQL);) {
            stm.setString(1, password);
            stm.setBoolean(2, role);
            stm.setString(3, username);
            int row = stm.executeUpdate();
            if (row > 0) {
                return true;
            }
        }
        return false;
    }

}
