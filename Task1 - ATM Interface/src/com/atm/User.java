package com.atm;

import java.util.ArrayList;
import java.util.List;

class User {
    private String userID;
    private String pin;
    private double balance;
    private List<String> transactionHistory;

    public User(String userID, String pin, double balance) {
        this.userID = userID;
        this.pin = pin;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    public String getUserID() {
        return userID;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposit: +$" + amount);
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactionHistory.add("Withdrawal: -$" + amount);
            return true;
        }
        return false;
    }

    public void transfer(User receiver, double amount) {
        if (withdraw(amount)) {
            receiver.deposit(amount);
            transactionHistory.add("Transfer to " + receiver.getUserID() + ": -$" + amount);
        }
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }
}
