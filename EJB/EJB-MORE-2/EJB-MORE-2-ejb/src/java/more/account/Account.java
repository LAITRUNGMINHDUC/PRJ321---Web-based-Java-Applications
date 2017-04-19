/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package more.account;

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
@Table(name = "Account")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
    @NamedQuery(name = "Account.findByAccountId", query = "SELECT a FROM Account a WHERE a.accountId = :accountId"),
    @NamedQuery(name = "Account.findByPin", query = "SELECT a FROM Account a WHERE a.pin = :pin"),
    @NamedQuery(name = "Account.findByBalance", query = "SELECT a FROM Account a WHERE a.balance = :balance"),
    @NamedQuery(name = "Account.findByBenefit", query = "SELECT a FROM Account a WHERE a.benefit = :benefit"),
    @NamedQuery(name = "Account.findByIsExpired", query = "SELECT a FROM Account a WHERE a.isExpired = :isExpired"),
    @NamedQuery(name = "Account.findByUserLevel", query = "SELECT a FROM Account a WHERE a.userLevel = :userLevel"),
    @NamedQuery(name = "Account.findByCustomerName", query = "SELECT a FROM Account a WHERE a.customerName = :customerName"),
    @NamedQuery(name = "Account.findByAddress", query = "SELECT a FROM Account a WHERE a.address = :address"),
    
    @NamedQuery(name = "Account.findByAccountIdAndPin", query = "SELECT a FROM Account a WHERE a.accountId = :accountId AND a.pin = :pin AND a.isExpired = :isExpired")
})
public class Account implements Serializable {

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
    @Basic(optional = false)
    @Column(name = "userLevel")
    private int userLevel;
    @Basic(optional = false)
    @Column(name = "customerName")
    private String customerName;
    @Basic(optional = false)
    @Column(name = "address")
    private String address;

    public Account() {
    }

    public Account(String accountId) {
        this.accountId = accountId;
    }

    public Account(String accountId, String pin, double balance, double benefit, boolean isExpired, int userLevel, String customerName, String address) {
        this.accountId = accountId;
        this.pin = pin;
        this.balance = balance;
        this.benefit = benefit;
        this.isExpired = isExpired;
        this.userLevel = userLevel;
        this.customerName = customerName;
        this.address = address;
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

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
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
        hash += (accountId != null ? accountId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.accountId == null && other.accountId != null) || (this.accountId != null && !this.accountId.equals(other.accountId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "more.Account[ accountId=" + accountId + " ]";
    }

}
