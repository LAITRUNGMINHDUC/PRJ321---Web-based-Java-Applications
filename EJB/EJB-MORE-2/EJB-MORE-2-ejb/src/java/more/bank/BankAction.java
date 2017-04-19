/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package more.bank;

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
@Table(name = "BankAction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BankAction.findAll", query = "SELECT b FROM BankAction b"),
    @NamedQuery(name = "BankAction.findById", query = "SELECT b FROM BankAction b WHERE b.id = :id"),
    @NamedQuery(name = "BankAction.findByDateAction", query = "SELECT b FROM BankAction b WHERE b.dateAction = :dateAction"),
    @NamedQuery(name = "BankAction.findByTypeAction", query = "SELECT b FROM BankAction b WHERE b.typeAction = :typeAction"),
    @NamedQuery(name = "BankAction.findByAmount", query = "SELECT b FROM BankAction b WHERE b.amount = :amount"),
    @NamedQuery(name = "BankAction.findByAccountId", query = "SELECT b FROM BankAction b WHERE b.accountId = :accountId"),
    @NamedQuery(name = "BankAction.findByTransferId", query = "SELECT b FROM BankAction b WHERE b.transferId = :transferId"),
    @NamedQuery(name = "BankAction.findByReason", query = "SELECT b FROM BankAction b WHERE b.reason = :reason")})
public class BankAction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "dateAction")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAction;
    @Basic(optional = false)
    @Column(name = "typeAction")
    private int typeAction;
    @Basic(optional = false)
    @Column(name = "amount")
    private double amount;
    @Basic(optional = false)
    @Column(name = "accountId")
    private String accountId;
    @Basic(optional = false)
    @Column(name = "transferId")
    private String transferId;
    @Basic(optional = false)
    @Column(name = "reason")
    private String reason;

    public BankAction() {
    }

    public BankAction(Integer id) {
        this.id = id;
    }

    public BankAction(Date dateAction, int typeAction, double amount, String accountId, String transferId, String reason) {        
        this.dateAction = dateAction;
        this.typeAction = typeAction;
        this.amount = amount;
        this.accountId = accountId;
        this.transferId = transferId;
        this.reason = reason;
    }

    public Integer getId() {
        return id;
    }

    public Date getDateAction() {
        return dateAction;
    }

    public void setDateAction(Date dateAction) {
        this.dateAction = dateAction;
    }

    public int getTypeAction() {
        return typeAction;
    }

    public void setTypeAction(int typeAction) {
        this.typeAction = typeAction;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getTransferId() {
        return transferId;
    }

    public void setTransferId(String transferId) {
        this.transferId = transferId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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
        if (!(object instanceof BankAction)) {
            return false;
        }
        BankAction other = (BankAction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "more.BankAction[ id=" + id + " ]";
    }
    
}
