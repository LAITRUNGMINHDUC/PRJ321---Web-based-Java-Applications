/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package more.account;

import javax.ejb.Remote;

/**
 *
 * @author duclt
 */
@Remote
public interface AccountSessionBeanRemote {

    boolean checkLogin(String username, String password);

    Account getAccountByUsername(String username);
    
}
