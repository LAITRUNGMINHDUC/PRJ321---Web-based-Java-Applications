/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.account;

import java.io.Serializable;

/**
 *
 * @author duclt
 */
public class AccountDTO implements Serializable {

    private String username;
    private String password;
    private String lastname;
    private boolean isAdmin;

    public AccountDTO(String username, String password, String lastname, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.isAdmin = isAdmin;
    }

    public AccountDTO() {
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
