/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import javax.ejb.Local;

/**
 *
 * @author duclt
 */
@Local
public interface WarehouseSessionBeanLocal {    

    boolean addProduct(String productId, int quantity, int price);
    
}
