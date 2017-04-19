/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.registration;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

/**
 *
 * @author duclt
 */
@Stateless
public class RegistrationSessionBean
        implements RegistrationSessionBeanLocal, RegistrationSessionBeanRemote {

    @Resource(name = "ds", mappedName = "java:IA1161EJB3")
    private DataSource ds;

    @Override
    public boolean checkLogin(String username, String password) {
        RegistrationDAO dao = new RegistrationDAO();
        boolean result = dao.checkLogin(username, password, ds);
        return result;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public List<RegistrationDTO> searchLastname(String searchValue) {

        RegistrationDAO dao = new RegistrationDAO();
        dao.searchLastname(searchValue, ds);

        List<RegistrationDTO> result = dao.getListAccounts();

        return result;
    }

}
