/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.cart;

import java.util.HashMap;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateful;
import javax.sql.DataSource;

/**
 *
 * @author duclt
 */
@Stateful
public class CartSessionBean implements CartSessionBeanLocal, CartSessionBeanRemote {

    private String customerId;
    private HashMap<String, Integer> items;

    @Resource(name = "IA1161")
    private DataSource IA1161;

    @PostConstruct
    public void construct() {
        this.items = new HashMap<String, Integer>();
        this.customerId = "00000";
    }

    @Override
    public void addItemToCart(String title) {
        int quantity = 1;
        if (items.containsKey(title)) {
            quantity = items.get(title) + 1;
        }
        items.put(title, quantity);
    }

    @Override
    public void remoteItemFromCart(String title) {
        if (items.containsKey(title)) {
            items.remove(title);
        }
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public HashMap<String, Integer> getItems() {
        return items;
    }
}
