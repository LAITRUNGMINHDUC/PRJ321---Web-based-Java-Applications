/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package more.product;

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
public class ProductSessionBean implements
        ProductSessionBeanLocal, ProductSessionBeanRemote {

    @PersistenceContext(unitName = "EJB-ProjectMORE-1-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public TblProduct findByName(String searchValue) {
        String jpql = "TblProduct.findByProductName";
        Query query = em.createNamedQuery(jpql);
        query.setParameter("productName", searchValue);
        try {
            TblProduct prod = (TblProduct) query.getSingleResult();
            return prod;
        } catch (NoResultException e) {
            return null;
        }
    }
}
