/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package more.product;

import javax.ejb.Remote;

/**
 *
 * @author duclt
 */
@Remote
public interface ProductSessionBeanRemote {

    TblProduct findByName(String searchValue);
    
}
