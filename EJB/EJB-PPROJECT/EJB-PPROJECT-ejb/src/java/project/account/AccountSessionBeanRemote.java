/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.account;

import javax.ejb.Remote;

/**
 *
 * @author duclt
 */
@Remote
public interface AccountSessionBeanRemote {

    boolean checkLogin(String username, String password);

    TblAccount findAccount(String username);

    boolean updateAccount(String username, double balance);

}
