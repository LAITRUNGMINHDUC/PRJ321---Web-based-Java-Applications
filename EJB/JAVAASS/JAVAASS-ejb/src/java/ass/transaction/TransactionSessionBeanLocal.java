/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass.transaction;

import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author duclt
 */
@Local
public interface TransactionSessionBeanLocal {

    List makeListTransaction(String accountId, Date fromDate, Date toDate);

    boolean hideTransaction(String transactionId);

    boolean addTransaction(String accountId, double amount, String reason, int type);
    
}
