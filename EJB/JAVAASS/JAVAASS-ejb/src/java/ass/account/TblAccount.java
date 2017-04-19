/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass.account;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author duclt
 */
@Entity
@Table(name = "tbl_account")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblAccount.findAll", query = "SELECT t FROM TblAccount t"),
    @NamedQuery(name = "TblAccount.findByAccountId", query = "SELECT t FROM TblAccount t WHERE t.accountId = :accountId"),
    @NamedQuery(name = "TblAccount.findByPin", query = "SELECT t FROM TblAccount t WHERE t.pin = :pin"),
    @NamedQuery(name = "TblAccount.findByBalance", query = "SELECT t FROM TblAccount t WHERE t.balance = :balance"),
    @NamedQuery(name = "TblAccount.findByBenefit", query = "SELECT t FROM TblAccount t WHERE t.benefit = :benefit"),
    @NamedQuery(name = "TblAccount.findByIsExpired", query = "SELECT t FROM TblAccount t WHERE t.isExpired = :isExpired"),
    
    @NamedQuery(name = "TblAccount.findByAccountIdAndPin", query = "SELECT t FROM TblAccount t WHERE t.accountId = :accountId AND t.pin = :pin")
})
public class TblAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "accountId")
    private String accountId;
    @Basic(optional = false)
    @Column(name = "pin")
    private String pin;
    @Basic(optional = false)
    @Column(name = "balance")
    private double balance;
    @Basic(optional = false)
    @Column(name = "benefit")
    private double benefit;
    @Basic(optional = false)
    @Column(name = "isExpired")
    private boolean isExpired;

    public TblAccount() {
    }

    public TblAccount(String accountId) {
        this.accountId = accountId;
    }

    public TblAccount(String accountId, String pin, double balance, double benefit, boolean isExpired) {
        this.accountId = accountId;
        this.pin = pin;
        this.balance = balance;
        this.benefit = benefit;
        this.isExpired = isExpired;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBenefit() {
        return benefit;
    }

    public void setBenefit(double benefit) {
        this.benefit = benefit;
    }

    public boolean getIsExpired() {
        return isExpired;
    }

    public void setIsExpired(boolean isExpired) {
        this.isExpired = isExpired;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accountId != null ? accountId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblAccount)) {
            return false;
        }
        TblAccount other = (TblAccount) object;
        if ((this.accountId == null && other.accountId != null) || (this.accountId != null && !this.accountId.equals(other.accountId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ass.TblAccount[ accountId=" + accountId + " ]";
    }

}
