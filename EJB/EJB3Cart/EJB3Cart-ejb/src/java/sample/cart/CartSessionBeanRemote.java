/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.cart;

import javax.ejb.Remote;

/**
 *
 * @author duclt
 */
@Remote
public interface CartSessionBeanRemote {

    public String getCustomerId();

    public void setCustomerId(String customerID);

    public java.util.HashMap<String, Integer> getItems();

    void addItemToCart(String title);

    void remoteItemFromCart(String title);
    
}
