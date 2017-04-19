/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.account;

import javax.ejb.Local;

/**
 *
 * @author duclt
 */
@Local
public interface AccountSessionBeanLocal {

    boolean checkLogin(String username, String password);

    TblAccount findAccount(String username);

    boolean updateAccount(String username, double balance);
    
}
