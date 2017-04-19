/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.registration;

import java.util.List;
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
public class RegistrationSessionBean implements RegistrationSessionBeanLocal,
        RegistrationSessionBeanRemote {

    @PersistenceContext(unitName = "EJB3LastIA1161-ejbPU")
    private EntityManager em;

    @Override
    public boolean checkLogin(String username, String password) {
        String jpql = "SELECT reg FROM Registration reg WHERE reg.username=:username"
                + " AND reg.password=:password";

        Query query = em.createQuery(jpql);
        query.setParameter("username", username);
        query.setParameter("password", password);

        try {
            Registration reg = (Registration) query.getSingleResult();
            return true;
        } catch (NoResultException ex) {
            return false;
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public List searchLastname(String searchValue) {
        String jpql = "Registration.findByLikeLastname";

        Query query = em.createNamedQuery(jpql);
        query.setParameter("lastname", "%" + searchValue + "%");

        List result = query.getResultList();

        return result;
    }

    @Override
    public boolean deleteAccount(String username) {
        Registration reg = em.find(Registration.class, username);

        if (reg != null) {
            //Nếu chưa có trong tầm kiểm soát thì merge
            em.merge(reg);
            em.remove(reg);
            return true;
        }
        return false;
    }

    @Override
    public boolean updatePassRole(String username, String password, String lastname, boolean role) {
        Registration reg = em.find(Registration.class, username);

        if (reg != null) {
            reg.setPassword(password);
            reg.setIsAdmin(role);

            em.merge(reg);

            return true;
        }
        return false;
    }

    @Override
    public boolean insertAccount(String username, String password, String lastname, boolean role) {
        Registration reg = em.find(Registration.class, username);

        if (reg == null) {
            reg = new Registration();
            reg.setUsername(username);
            reg.setPassword(password);
            reg.setLastname(lastname);
            reg.setIsAdmin(role);

            // Insert dùng persist
            em.persist(reg);

            return true;
        }
        return false;
    }

}
