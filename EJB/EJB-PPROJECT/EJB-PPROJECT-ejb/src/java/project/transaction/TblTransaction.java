/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.transaction;

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
@Table(name = "tbl_transaction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblTransaction.findAll", query = "SELECT t FROM TblTransaction t"),
    @NamedQuery(name = "TblTransaction.findByTransID", query = "SELECT t FROM TblTransaction t WHERE t.transID = :transID"),
    @NamedQuery(name = "TblTransaction.findByTransDate", query = "SELECT t FROM TblTransaction t WHERE t.transDate = :transDate"),
    @NamedQuery(name = "TblTransaction.findByType", query = "SELECT t FROM TblTransaction t WHERE t.type = :type"),
    @NamedQuery(name = "TblTransaction.findByAmount", query = "SELECT t FROM TblTransaction t WHERE t.amount = :amount"),
    @NamedQuery(name = "TblTransaction.findByReason", query = "SELECT t FROM TblTransaction t WHERE t.reason = :reason"),
    @NamedQuery(name = "TblTransaction.findByAccountID", query = "SELECT t FROM TblTransaction t WHERE t.accountID = :accountID"),
    @NamedQuery(name = "TblTransaction.findByStatus", query = "SELECT t FROM TblTransaction t WHERE t.status = :status"),

    @NamedQuery(name = "TblTransaction.findListTransactionBetweenTwoDate",
            query = "SELECT t FROM TblTransaction t WHERE (t.transDate BETWEEN :fromDate AND :toDate) "
            + "AND t.accountID = :accountID AND t.status = :status")

})
public class TblTransaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Basic(optional = false)
    @Column(name = "transID")
    private Integer transID;    
    @Basic(optional = false)
    @Column(name = "transDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transDate;
    @Basic(optional = false)
    @Column(name = "type")
    private int type;
    @Basic(optional = false)
    @Column(name = "amount")
    private double amount;
    @Basic(optional = false)
    @Column(name = "reason")
    private String reason;
    @Basic(optional = false)
    @Column(name = "accountID")
    private String accountID;
    @Basic(optional = false)
    @Column(name = "status")
    private boolean status;

    public TblTransaction() {
    }

    public TblTransaction(Integer transID) {
        this.transID = transID;
    }

    public TblTransaction(Integer transID, Date transDate, int type, double amount, String reason, String accountID, boolean status) {
        this.transID = transID;
        this.transDate = transDate;
        this.type = type;
        this.amount = amount;
        this.reason = reason;
        this.accountID = accountID;
        this.status = status;
    }

    public Integer getTransID() {
        return transID;
    }

    public void setTransID(Integer transID) {
        this.transID = transID;
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (transID != null ? transID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblTransaction)) {
            return false;
        }
        TblTransaction other = (TblTransaction) object;
        if ((this.transID == null && other.transID != null) || (this.transID != null && !this.transID.equals(other.transID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "project.transaction.TblTransaction[ transID=" + transID + " ]";
    }

}
