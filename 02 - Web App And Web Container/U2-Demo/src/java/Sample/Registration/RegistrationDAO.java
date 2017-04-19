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
import java.util.ArrayList;
import java.util.List;

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

    private List<RegistrationDTO> listAccounts;

    public List<RegistrationDTO> getListAccounts() {
        return listAccounts;
    }

    public void searchLastname(String searchValue)
            throws SQLException, ClassNotFoundException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBUtils.makeConnection();

            if (con != null) {
                String sql = "SELECT * FROM Registration WHERE Lastname = ?";

                stm = con.prepareStatement(sql);

                stm.setString(1, "%" + searchValue + "%");

                rs = stm.executeQuery();

                while (rs.next()) {
                    String username = rs.getString("Username");
                    String password = rs.getString("Password");
                    String lastname = rs.getString("Lastname");
                    boolean role = rs.getBoolean("isAdmin");

                    RegistrationDTO dto
                            = new RegistrationDTO(username, password, lastname, role);

                    if (this.listAccounts == null) {
                        this.listAccounts = new ArrayList<RegistrationDTO>();
                    }

                    this.listAccounts.add(dto);
                }
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
}
