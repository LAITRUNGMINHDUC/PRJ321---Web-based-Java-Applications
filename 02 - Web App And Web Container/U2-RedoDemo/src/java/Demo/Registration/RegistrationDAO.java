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

/**
 *
 * @author duclt
 */
public class RegistrationDAO {

    public boolean checkLogin(String username, String password)
            throws ClassNotFoundException, SQLException {
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
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private List<RegistrationDTO> listAccounts;

    public void searchLastname(String searchValue) {
        try {
            Connection c = DBUtils.makeConnection();
            String SQL = "SELECT * FROM Registration WHERE Lastname = ?";
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
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public List<RegistrationDTO> getListAccounts() {
        return listAccounts;
    }
}
