/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab.Products;

/**
 *
 * @author duclt
 */
public class ProductsDTO {

    private String Code;
    private String Name;
    private String Description;
    private float Price;
    private String Category;

    public ProductsDTO(String Code, String Name, String Description, float Price, String Category) {
        this.Code = Code;
        this.Name = Name;
        this.Description = Description;
        this.Price = Price;
        this.Category = Category;
    }

    public ProductsDTO() {
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float Price) {
        this.Price = Price;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

}
