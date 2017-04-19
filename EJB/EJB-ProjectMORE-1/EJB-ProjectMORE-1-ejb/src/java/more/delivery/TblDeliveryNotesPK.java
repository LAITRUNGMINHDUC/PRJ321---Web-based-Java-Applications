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
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author duclt
 */
@Embeddable
public class TblDeliveryNotesPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "productId")
    private String productId;
    @Basic(optional = false)
    @Column(name = "deliveryDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveryDate;

    public TblDeliveryNotesPK() {
    }

    public TblDeliveryNotesPK(String productId, Date deliveryDate) {
        this.productId = productId;
        this.deliveryDate = deliveryDate;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productId != null ? productId.hashCode() : 0);
        hash += (deliveryDate != null ? deliveryDate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblDeliveryNotesPK)) {
            return false;
        }
        TblDeliveryNotesPK other = (TblDeliveryNotesPK) object;
        if ((this.productId == null && other.productId != null) || (this.productId != null && !this.productId.equals(other.productId))) {
            return false;
        }
        if ((this.deliveryDate == null && other.deliveryDate != null) || (this.deliveryDate != null && !this.deliveryDate.equals(other.deliveryDate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "more.delivery.TblDeliveryNotesPK[ productId=" + productId + ", deliveryDate=" + deliveryDate + " ]";
    }
    
}
