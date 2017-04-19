/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.javabean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import lab.account.AccountDAO;

/**
 *
 * @author duclt
 */
public class SearchBean implements Serializable {

    private String lastname;

    public List<String> makeListLastName() throws NamingException, SQLException {
        AccountDAO dao = new AccountDAO();
        if (lastname != null) {
            dao.makeListLastName(lastname);
        }
        else {
            dao.makeListLastName();
        }
        
        if (dao.getListLastName() != null) {
            return dao.getListLastName();
        } else {
            return null;
        }
    }

    public SearchBean(String lastname) {
        this.lastname = lastname;
    }

    public SearchBean() {
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

}
