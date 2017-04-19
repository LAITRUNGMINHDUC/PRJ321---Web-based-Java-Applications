/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author duclt
 */
public class RegistrationDAO implements Serializable {

    /**
     *
     * @param username
     * @param password
     * @param ds
     * @return
     */
    public boolean checkLogin(String username, String password, DataSource ds) {
        Connection c = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        if (ds != null) {
            try {
                c = ds.getConnection();
                if (c != null) {
                    String SQL = "SELECT * FROM Registration WHERE Username = ? AND Password = ?";
                    stm = c.prepareStatement(SQL);
                    stm.setString(1, username);
                    stm.setString(2, password);
                    rs = stm.executeQuery();
                    if (rs.next()) {
                        return true;
                    }
                }
                return false;
            } catch (SQLException ex) {
                Logger.getLogger(RegistrationDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    c.close();
                    stm.close();
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RegistrationDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    private List<RegistrationDTO> listAccounts;

    public List<RegistrationDTO> getListAccounts() {
        return listAccounts;
    }

    public void searchLastname(String searchValue, DataSource ds) {
        Connection c = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        if (ds != null) {
            try {
                c = ds.getConnection();
                if (c != null) {
                    String SQL = "SELECT * FROM Registration WHERE Lastname LIKE ?";
                    stm = c.prepareStatement(SQL);
                    stm.setString(1, "%" + searchValue + "%");
                    rs = stm.executeQuery();

                    while (rs.next()) {
                        String username = rs.getString("username");
                        String password = rs.getString("password");
                        boolean role = rs.getBoolean("isAdmin");

                        RegistrationDTO dto = new RegistrationDTO(username, password, searchValue, role);

                        if (this.listAccounts == null) {
                            this.listAccounts = new ArrayList<RegistrationDTO>();
                        }

                        this.listAccounts.add(dto);
                    }

                }
            } catch (SQLException ex) {
                Logger.getLogger(RegistrationDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    c.close();
                    stm.close();
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RegistrationDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
