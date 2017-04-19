/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.account;

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

    @PersistenceContext(unitName = "EJB-PPROJECT-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public boolean checkLogin(String username, String password) {
        Query query = em.createNamedQuery("TblAccount.findByAccountIDAndPin");
        query.setParameter("accountID", username);
        query.setParameter("pin", password);
        try {
            Object obj = query.getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    @Override
    public TblAccount findAccount(String username) {
        TblAccount acc = em.find(TblAccount.class, username);
        if (acc != null) {
            return acc;
        }
        return null;
    }

    @Override
    public boolean updateAccount(String username, double balance) {
        TblAccount acc = em.find(TblAccount.class, username);
        if (acc != null) {            
            acc.setBalance(balance);
            em.merge(acc);
            return true;
        }
        return false;
    }
    
    
    

}
