/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package more.bank;

import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author duclt
 */
@Remote
public interface BankSessionBeanRemote {

    boolean deposit(double amount, String accountId, String reason);

    boolean withdrawn(double amount, String accountId, String reason);

    List transaction(String accountId);
    
}
