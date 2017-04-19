/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package more.account;

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

    @PersistenceContext(unitName = "EJB-MORE-2-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public boolean checkLogin(String username, String password) {
        String jpql = "Account.findByAccountIdAndPin";
        Query query = em.createNamedQuery(jpql);
        query.setParameter("accountId", username);
        query.setParameter("pin", password);
        query.setParameter("isExpired", false);
        try {
            Object obj = query.getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    @Override
    public Account getAccountByUsername(String username) {
        Account acc = em.find(Account.class, username);
        if (acc != null) {
            return acc;
        }
        return null;
    }

}
