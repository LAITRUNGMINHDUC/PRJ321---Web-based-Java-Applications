/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package more.delivery;

import javax.ejb.Remote;

/**
 *
 * @author duclt
 */
@Remote
public interface DeliverySessionBeanRemote {

    boolean addNote(String productId, float price, int quantity, String customerName, String address);

}
