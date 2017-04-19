/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab;

import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author duclt
 */
@Remote
public interface AccountSessionBeanRemote {

    boolean createAccount(String id, String fullName);

    Account findAccount(String id);

    List findLikeFullName(String searchValue);

    public String getAccountId();

    public int getBalance();

    boolean updateBalance(String id, double balance);

    boolean deleteAccount(String id);

}
