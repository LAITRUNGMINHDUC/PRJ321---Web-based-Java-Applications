/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass.transaction;

import java.util.Date;
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
public class TransactionSessionBean implements TransactionSessionBeanLocal, TransactionSessionBeanRemote {

    @PersistenceContext(unitName = "JAVAASS-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public List makeListTransaction(String accountId, Date fromDate, Date toDate) {
        String jpql = "TblTransaction.findByTransDateBetweenAndStatusAndAccountId";
        Query query = em.createNamedQuery(jpql);
        query.setParameter("fromDate", fromDate);
        query.setParameter("toDate", toDate);
        query.setParameter("accountId", accountId);
        query.setParameter("status", false);
        return query.getResultList();
    }

    @Override
    public boolean hideTransaction(String transactionId) {
        Integer transId = Integer.parseInt(transactionId);
        TblTransaction trans = em.find(TblTransaction.class, transId);
        if (trans != null) {
            trans.setStatus(true);
            em.merge(trans);
            return true;
        }
        return false;
    }

    @Override
    public boolean addTransaction(String accountId, double amount, String reason, int type) {
        TblTransaction trans = new TblTransaction(new Date(), type, amount, reason, accountId, false);
        try {
            em.persist(trans);
            return true;
        } catch (Exception e) {
            return false;
        }        
    }

}
