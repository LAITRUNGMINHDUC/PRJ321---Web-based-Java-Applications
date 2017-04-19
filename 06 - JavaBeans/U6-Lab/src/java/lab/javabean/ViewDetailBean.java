/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.javabean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import lab.account.AccountDAO;
import lab.account.AccountDTO;

/**
 *
 * @author duclt
 */
public class ViewDetailBean implements Serializable{
    private String username;
    private String password;
    private String lastname;
    private boolean isAdmin;
    
    public void checkInfo() {
        AccountDAO dao = new AccountDAO();
        try {
            AccountDTO dto = dao.checkInfo(username);
            if (dto != null) {
                this.lastname = dto.getLastname();
                this.password = dto.getPassword();
                this.isAdmin = dto.isIsAdmin();
            }
        } catch (NamingException ex) {
            Logger.getLogger(ViewDetailBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ViewDetailBean.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
   

    public ViewDetailBean(String username, String password, String lastname, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.isAdmin = isAdmin;
    }

    public ViewDetailBean() {
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    
    
}
