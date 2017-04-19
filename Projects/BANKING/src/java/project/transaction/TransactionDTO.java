/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.transaction;

import java.io.Serializable;

/**
 *
 * @author duclt
 */
public class TransactionDTO implements Serializable {

    private String transID;
    private String transDate;
    private int type;
    private double amount;
    private String reason;
    private String accountID;
    private boolean status;

    public TransactionDTO(String transID, String transDate, int type, double amount, String reason, String accountID, boolean status) {
        this.transID = transID;
        this.transDate = transDate;
        this.type = type;
        this.amount = amount;
        this.reason = reason;
        this.accountID = accountID;
        this.status = status;
    }

    public TransactionDTO() {
    }

    public String getTransID() {
        return transID;
    }

    public void setTransID(String transID) {
        this.transID = transID;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
