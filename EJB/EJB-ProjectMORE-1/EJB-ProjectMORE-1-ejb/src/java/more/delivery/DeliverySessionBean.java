/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package more.delivery;

import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author duclt
 */
@Stateless
public class DeliverySessionBean implements
        DeliverySessionBeanLocal, DeliverySessionBeanRemote {

    @PersistenceContext(unitName = "EJB-ProjectMORE-1-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public boolean addNote(String productId, float price, int quantity, String customerName, String address) {

        TblDeliveryNotes note = new TblDeliveryNotes();
        TblDeliveryNotesPK PK = new TblDeliveryNotesPK(productId, new Date());

        note.setAddress(address);
        note.setCustomerName(customerName);
        note.setPrice(price);
        note.setQuantity(quantity);
        note.setTblDeliveryNotesPK(PK);

        try {
            em.persist(note);
            return true;
        } catch (Exception e) {            
            return false;
        }
    }

}
