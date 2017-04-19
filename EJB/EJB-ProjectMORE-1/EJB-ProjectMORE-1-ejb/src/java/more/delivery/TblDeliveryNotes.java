/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package more.delivery;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author duclt
 */
@Entity
@Table(name = "tbl_DeliveryNotes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblDeliveryNotes.findAll", query = "SELECT t FROM TblDeliveryNotes t"),
    @NamedQuery(name = "TblDeliveryNotes.findByProductId", query = "SELECT t FROM TblDeliveryNotes t WHERE t.tblDeliveryNotesPK.productId = :productId"),
    @NamedQuery(name = "TblDeliveryNotes.findByDeliveryDate", query = "SELECT t FROM TblDeliveryNotes t WHERE t.tblDeliveryNotesPK.deliveryDate = :deliveryDate"),
    @NamedQuery(name = "TblDeliveryNotes.findByPrice", query = "SELECT t FROM TblDeliveryNotes t WHERE t.price = :price"),
    @NamedQuery(name = "TblDeliveryNotes.findByQuantity", query = "SELECT t FROM TblDeliveryNotes t WHERE t.quantity = :quantity"),
    @NamedQuery(name = "TblDeliveryNotes.findByCustomerName", query = "SELECT t FROM TblDeliveryNotes t WHERE t.customerName = :customerName"),
    @NamedQuery(name = "TblDeliveryNotes.findByAddress", query = "SELECT t FROM TblDeliveryNotes t WHERE t.address = :address")})
public class TblDeliveryNotes implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TblDeliveryNotesPK tblDeliveryNotesPK;
    @Basic(optional = false)
    @Column(name = "price")
    private double price;
    @Basic(optional = false)
    @Column(name = "quantity")
    private int quantity;
    @Basic(optional = false)
    @Column(name = "customerName")
    private String customerName;
    @Basic(optional = false)
    @Column(name = "address")
    private String address;

    public TblDeliveryNotes() {
    }

    public TblDeliveryNotes(TblDeliveryNotesPK tblDeliveryNotesPK) {
        this.tblDeliveryNotesPK = tblDeliveryNotesPK;
    }

    public TblDeliveryNotes(TblDeliveryNotesPK tblDeliveryNotesPK, double price, int quantity, String customerName, String address) {
        this.tblDeliveryNotesPK = tblDeliveryNotesPK;
        this.price = price;
        this.quantity = quantity;
        this.customerName = customerName;
        this.address = address;
    }

    public TblDeliveryNotes(String productId, Date deliveryDate) {
        this.tblDeliveryNotesPK = new TblDeliveryNotesPK(productId, deliveryDate);
    }

    public TblDeliveryNotesPK getTblDeliveryNotesPK() {
        return tblDeliveryNotesPK;
    }

    public void setTblDeliveryNotesPK(TblDeliveryNotesPK tblDeliveryNotesPK) {
        this.tblDeliveryNotesPK = tblDeliveryNotesPK;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tblDeliveryNotesPK != null ? tblDeliveryNotesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblDeliveryNotes)) {
            return false;
        }
        TblDeliveryNotes other = (TblDeliveryNotes) object;
        if ((this.tblDeliveryNotesPK == null && other.tblDeliveryNotesPK != null) || (this.tblDeliveryNotesPK != null && !this.tblDeliveryNotesPK.equals(other.tblDeliveryNotesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "more.delivery.TblDeliveryNotes[ tblDeliveryNotesPK=" + tblDeliveryNotesPK + " ]";
    }
    
}
