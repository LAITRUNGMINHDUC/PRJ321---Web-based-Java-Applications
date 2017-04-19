/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author duclt
 */
@Entity
@Table(name = "tbl_Warehouse")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblWarehouse.findAll", query = "SELECT t FROM TblWarehouse t"),
    @NamedQuery(name = "TblWarehouse.findById", query = "SELECT t FROM TblWarehouse t WHERE t.id = :id"),
    @NamedQuery(name = "TblWarehouse.findByProductId", query = "SELECT t FROM TblWarehouse t WHERE t.productId = :productId"),
    @NamedQuery(name = "TblWarehouse.findByImportDate", query = "SELECT t FROM TblWarehouse t WHERE t.importDate = :importDate"),
    @NamedQuery(name = "TblWarehouse.findByQuantity", query = "SELECT t FROM TblWarehouse t WHERE t.quantity = :quantity"),
    @NamedQuery(name = "TblWarehouse.findByPrice", query = "SELECT t FROM TblWarehouse t WHERE t.price = :price")})
public class TblWarehouse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue //Persist xuong DB thi ko can dua PK --> Khi insert thanh cong thi tu dong lay PK tu DB len Persist
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "productId")
    private String productId;
    @Basic(optional = false)
    @Column(name = "importDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date importDate;
    @Basic(optional = false)
    @Column(name = "quantity")
    private int quantity;
    @Basic(optional = false)
    @Column(name = "price")
    private int price;

    public TblWarehouse() {
    }

    public TblWarehouse(Integer id, String productId, Date importDate, int quantity, int price) {
        this.id = id;
        this.productId = productId;
        this.importDate = importDate;
        this.quantity = quantity;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblWarehouse)) {
            return false;
        }
        TblWarehouse other = (TblWarehouse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sample.TblWarehouse[ id=" + id + " ]";
    }
    
}
