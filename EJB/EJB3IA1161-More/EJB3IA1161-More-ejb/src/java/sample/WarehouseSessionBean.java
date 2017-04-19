/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author duclt
 */
@Stateless
public class WarehouseSessionBean implements WarehouseSessionBeanLocal,
        WarehouseSessionBeanRemote {

    @PersistenceContext(unitName = "EJB3IA1161-More-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public boolean addProduct(String productId, int quantity, int price) {
        TblWarehouse ware = new TblWarehouse();
        ware.setImportDate(new Date());
        ware.setPrice(price);
        ware.setProductId(productId);
        ware.setQuantity(quantity);
        em.persist(ware);
        return true;
    }

    
}
