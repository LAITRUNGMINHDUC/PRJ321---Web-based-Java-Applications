/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab.Orders;

import java.io.Serializable;

/**
 *
 * @author duclt
 */
public class OrdersDTO implements Serializable {

    private String CustomerName;
    private String ProductIDList;
    private String QuantityList;
    private String PriceList;

    public OrdersDTO(String CustomerName, String ProductIDList, String QuantityList, String PriceList) {
        this.CustomerName = CustomerName;
        this.ProductIDList = ProductIDList;
        this.QuantityList = QuantityList;
        this.PriceList = PriceList;
    }

    public OrdersDTO() {
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public String getProductIDList() {
        return ProductIDList;
    }

    public void setProductIDList(String ProductIDList) {
        this.ProductIDList = ProductIDList;
    }

    public String getQuantityList() {
        return QuantityList;
    }

    public void setQuantityList(String QuantityList) {
        this.QuantityList = QuantityList;
    }

    public String getPriceList() {
        return PriceList;
    }

    public void setPriceList(String PriceList) {
        this.PriceList = PriceList;
    }
    
    
}
