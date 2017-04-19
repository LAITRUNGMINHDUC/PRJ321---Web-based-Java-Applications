/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.studentDAO;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import sample.utils.DBUlti;

/**
 *
 * @author TuanHDSE62146
 */
public class StudentDAO implements Serializable{
    public boolean checLogin(String username, String password) throws SQLException, ClassNotFoundException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs;
        try {
            con = DBUlti.makeConnection();
            if (con != null){
                String sql = "SELECT * FROM Student WHERE username= ? AND password = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()){
                    return true;
                }
            }
        } finally {
            if (stm != null){
                stm.close();
            }
            if (con != null){
                con.close();
            }
        }
        return false;
    }
    
    private List<RegistratorDTO> listAccounts;

    public List<RegistratorDTO> getListAccounts() {
        return listAccounts;
    }
    
    public void searchLastName(String searchValue) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBUlti.makeConnection();
            if (con != null){
                String sql = "SELECT * FROM Student WHERE lastname Like ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                rs = stm.executeQuery();
                while (rs.next()){
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastname =  rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    
                    RegistratorDTO dto = 
                            new RegistratorDTO(username, password, lastname, role);
                    
                    if (this.listAccounts == null){
                        this.listAccounts = new ArrayList<RegistratorDTO>();
                    }
                    
                    this.listAccounts.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null){
                stm.close();
            }
            if (con != null){
                con.close();
            }
        }
    }
    
    public boolean deleteRecord(String username) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUlti.makeConnection();
            if (con != null){
                String sql = "DELETE FROM Student WHERE username=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                int row = stm.executeUpdate();
                if (row > 0){
                    return true;
                }
            }
        } finally {
            if (stm != null){
                stm.close();
            }
            if (con != null){
                con.close();
            }
        }
        return false;
    }
    
    public boolean updatePassRole(String username, String password, boolean role) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBUlti.makeConnection();
            if (con != null){
                String sql = "UPDATE Student SET password=?, isAdmin=? WHERE username=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, role);
                stm.setString(3, username);
                int row = stm.executeUpdate();
                if (row > 0){
                    return true;
                }
            }
        } finally {
            if (stm != null){
                stm.close();
            }
            if (con != null){
                con.close();
            }
        }
        return false;
    }

    public boolean insertRecord(String username, String password, String fullName, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
