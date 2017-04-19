/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.registration;

import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author duclt
 */
@Remote
public interface RegistrationSessionBeanRemote {

    boolean checkLogin(String username, String password);

    List searchLastname(String searchValue);

    boolean deleteAccount(String username);

    boolean updatePassRole(String username, String password, String lastname, boolean role);

    boolean insertAccount(String username, String password, String lastname, boolean role);
    
}
