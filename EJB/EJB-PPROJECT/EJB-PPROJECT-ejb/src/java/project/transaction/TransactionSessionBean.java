/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.transaction;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author duclt
 */
@Stateless
public class TransactionSessionBean implements
        TransactionSessionBeanLocal, TransactionSessionBeanRemote {

    @PersistenceContext(unitName = "EJB-PPROJECT-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public List findListTransaction(String fromDate, String toDate, String username) {
        String jqpl = "TblTransaction.findListTransactionBetweenTwoDate";
        Query query = em.createNamedQuery(jqpl);
        query.setParameter("fromDate", fromDate);
        query.setParameter("toDate", toDate);
        query.setParameter("accountID", username);
        query.setParameter("status", false);

        List<TblTransaction> result = query.getResultList();
        return result;
    }

    @Override
    public boolean hideTransaction(String transId) {
        TblTransaction trans = em.find(TblTransaction.class, transId);
        if (trans != null) {
            trans.setStatus(true);
            em.merge(trans);
            return true;
        }
        return false;
    }

    @Override
    public boolean writeTransaction(String username, int type, double amount, String reason) {
        try {
            TblTransaction trans = new TblTransaction();
            trans.setAccountID(username);
            trans.setAmount(amount);
            trans.setReason(reason);
            trans.setStatus(false);
            trans.setType(type);
            trans.setTransDate(new Date());

            em.persist(trans);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
