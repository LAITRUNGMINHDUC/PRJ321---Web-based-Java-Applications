/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author duclt
 */
@Local
public interface ProductSessionBeanLocal {

    boolean addProduct(String productId, String productName, int price, String description);

    TblProduct findProduct(String productId);

    List findProductLikeName(String productName);
    
}
