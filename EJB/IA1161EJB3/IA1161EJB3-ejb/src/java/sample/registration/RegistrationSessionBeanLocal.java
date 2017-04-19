/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.registration;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author duclt
 */
@Local
public interface RegistrationSessionBeanLocal {

    boolean checkLogin(String username, String password);

    List<RegistrationDTO> searchLastname(String searchValue);
    
}
