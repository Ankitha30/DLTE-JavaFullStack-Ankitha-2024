package web;

import app.mybank.entity.Transaction;

import java.util.List;

public class GroupOfServices {
    private List<Transaction> transaction;

    @Override
    public String toString() {
        return super.toString();
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
