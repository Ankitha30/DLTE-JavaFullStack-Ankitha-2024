package web;

import app.mybank.entity.Account;
import app.mybank.entity.Transaction;

import java.util.List;

public class GroupOfServices {
    private List<Transaction> transaction;
    private  List<Account> accountList;

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    @Override
    public String toString() {
        return "GroupOfServices{" +
                "transaction=" + transaction +
                ", accountList=" + accountList +
                '}';
    }

    public GroupOfServices(List<Transaction> transaction, List<Account> accountList) {
        this.transaction = transaction;
        this.accountList = accountList;
    }


    public List<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(List<Transaction> transaction) {
        this.transaction = transaction;
    }

    public GroupOfServices() {
    }

    public GroupOfServices(List<Transaction> transaction) {
        this.transaction = transaction;
    }
}
