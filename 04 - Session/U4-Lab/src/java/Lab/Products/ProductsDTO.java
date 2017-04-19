/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab.Products;

import java.io.Serializable;

/**
 *
 * @author duclt
 */
public class ProductsDTO implements Serializable {

    private String ProductID;
    private String ProductName;
    private String QuantityPerUnit;
    private int Price;

    public ProductsDTO(String ProductID, String ProductName, String QuantityPerUnit, int Price) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.QuantityPerUnit = QuantityPerUnit;
        this.Price = Price;
    }

    public ProductsDTO() {
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String ProductID) {
        this.ProductID = ProductID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getQuantityPerUnit() {
        return QuantityPerUnit;
    }

    public void setQuantityPerUnit(String QuantityPerUnit) {
        this.QuantityPerUnit = QuantityPerUnit;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

}
