/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.javabean;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import lab.account.AccountDAO;
import lab.utils.DBUtils;

/**
 *
 * @author duclt
 */
public class LoginBean implements Serializable {

    private String username;
    private String password;

    public boolean checkLogin() {
        AccountDAO dao = new AccountDAO();
        try {
            if (dao.checkLogin(username, password)) {
                return true;
            }
        } catch (NamingException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public LoginBean(String username, String password, String lastname, boolean isAdmin) {
        this.username = username;
        this.password = password;

    }

    public LoginBean() {
    }
    
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

 }
