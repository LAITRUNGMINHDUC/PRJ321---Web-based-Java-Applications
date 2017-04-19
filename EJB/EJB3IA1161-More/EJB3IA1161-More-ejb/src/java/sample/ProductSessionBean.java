/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

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
public class ProductSessionBean implements ProductSessionBeanLocal,
        ProductSessionBeanRemote {

    @PersistenceContext(unitName = "EJB3IA1161-More-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public boolean addProduct(String productId, String productName, int price, String description) {

        TblProduct prod = em.find(TblProduct.class, productId);
        if (prod == null) {
            prod = new TblProduct(productId, productName, price, description);
            em.persist(prod);
            return true;
        }
        return false;
    }

    @Override
    public TblProduct findProduct(String productId) {
        TblProduct prod = em.find(TblProduct.class, productId);
        return prod;
    }

    @Override
    public List findProductLikeName(String productName) {
        String jqpl = "TblProduct.findLikeProductName";
        Query query = em.createNamedQuery(jqpl);
        query.setParameter("productName", "%" + productName + "%");
        List<TblProduct> result = query.getResultList();
        return result;
    }

}
