/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author duclt
 */
@Stateless
public class AccountSessionBean implements AccountSessionBeanLocal,
        AccountSessionBeanRemote {

    private String accountId;
    private int balance;

    @PersistenceContext(unitName = "EJB3-Lab11-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public boolean createAccount(String id, String fullName) {
        Account acc = em.find(Account.class, id);
        if (acc == null) {
            acc = new Account(id, fullName, 0, true);
            em.persist(acc);
            return true;
        }
        return false;
    }

    @Override
    public Account findAccount(String id) {
        return em.find(Account.class, id);
    }

    @Override
    public List findLikeFullName(String searchValue) {
        String jpql = "Account.findLikeFullName";
        
        Query query = em.createNamedQuery(jpql);
        query.setParameter("fullName", "%" + searchValue + "%");

        List result = query.getResultList();

        return result;
    }

    @Override
    public String getAccountId() {
        return accountId;
    }

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public boolean updateBalance(String id, double balance) {
        Account acc = em.find(Account.class, id);
        if (acc != null) {
            acc.setBalance(balance);
            em.merge(acc);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteAccount(String id) {
        Account acc = em.find(Account.class, id);        
        if (acc != null) {
            em.merge(acc);
            em.remove(acc);
            return true;
        }        
        return false;
    }
    
    

}
