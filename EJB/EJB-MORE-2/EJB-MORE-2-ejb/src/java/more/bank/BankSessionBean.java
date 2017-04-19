/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package more.bank;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import more.account.Account;

/**
 *
 * @author duclt
 */
@Stateless
public class BankSessionBean
        implements BankSessionBeanLocal, BankSessionBeanRemote {

    @PersistenceContext(unitName = "EJB-MORE-2-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public boolean deposit(double amount, String accountId, String reason) {
        Account acc = em.find(Account.class, accountId);
        if (acc != null) {
            amount = amount + acc.getBalance();
            acc.setBalance(amount);

            em.merge(acc);

            BankAction bank = new BankAction();
            bank.setAccountId(accountId);
            bank.setTransferId(accountId);
            bank.setAmount(amount);
            bank.setDateAction(new Date());
            bank.setReason(reason);
            bank.setTypeAction(1);

            em.persist(bank);

            return true;
        }
        return false;
    }

    @Override
    public boolean withdrawn(double amount, String accountId, String reason) {
        Account acc = em.find(Account.class, accountId);
        if (acc != null) {
            amount = acc.getBalance() - amount;
            acc.setBalance(amount);

            em.merge(acc);

            BankAction bank = new BankAction();
            bank.setAccountId(accountId);
            bank.setTransferId(accountId);
            bank.setAmount(amount);
            bank.setDateAction(new Date());
            bank.setReason(reason);
            bank.setTypeAction(2);

            em.persist(bank);

            return true;
        }
        return false;

    }

    @Override
    public List transaction(String accountId) {
        String jqpl = "BankAction.findByAccountId";
        Query query = em.createNamedQuery(jqpl);
        query.setParameter("accountId", accountId);
        List<BankAction> result = query.getResultList();
        return result;
    }

}
