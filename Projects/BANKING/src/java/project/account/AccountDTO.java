/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.account;

import java.io.Serializable;

/**
 *
 * @author duclt
 */
public class AccountDTO implements Serializable {

    private String accountID;
    private String pin;
    private double balance;
    private double benefit;
    private boolean expired;

    public AccountDTO() {
    }

    public AccountDTO(String accountID, String pin, double balance, double benefit, boolean expired) {
        this.accountID = accountID;
        this.pin = pin;
        this.balance = balance;
        this.benefit = benefit;
        this.expired = expired;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
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

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

}
