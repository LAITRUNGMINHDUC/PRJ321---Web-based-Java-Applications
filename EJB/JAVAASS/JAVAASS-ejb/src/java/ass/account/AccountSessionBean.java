/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass.account;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author duclt
 */
@Stateless
public class AccountSessionBean implements
        AccountSessionBeanLocal, AccountSessionBeanRemote {

    @PersistenceContext(unitName = "JAVAASS-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")    
    @Override
    public boolean checkLogin(String accountId, String pin) {
        String jpql = "TblAccount.findByAccountIdAndPin";
        Query query = em.createNamedQuery(jpql);
        query.setParameter("accountId", accountId);
        query.setParameter("pin", pin);
        try {
            TblAccount acc = (TblAccount) query.getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    @Override
    public TblAccount getAccountById(String accountId) {
        TblAccount acc = em.find(TblAccount.class, accountId);
        if (acc.getIsExpired()) {
            return null;
        }
        return acc;
    }

    @Override
    public boolean updateBalance(String accountId, double balance) {
        TblAccount acc = em.find(TblAccount.class, accountId);
        if (acc != null) {
            acc.setBalance(balance);
            em.merge(acc);
            return true;
        }
        return false;
    }

}
