package org.example;

import java.util.Date;

public class Transaction {
    private Integer transactionId;
    private Date transactionDate;

    public Transaction(Integer transactionId, Date transactionDate, String transactionTo, Double transactionAmount, String transactionRemarks) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.transactionTo = transactionTo;
        this.transactionAmount = transactionAmount;
        this.transactionRemarks = transactionRemarks;
    }

    public Transaction() {
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionTo() {
        return transactionTo;
    }

    public void setTransactionTo(String transactionTo) {
        this.transactionTo = transactionTo;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionRemarks() {
        return transactionRemarks;
    }

    public void setTransactionRemarks(String transactionRemarks) {
        this.transactionRemarks = transactionRemarks;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", transactionDate=" + transactionDate +
                ", transactionTo='" + transactionTo + '\'' +
                ", transactionAmount=" + transactionAmount +
                ", transactionRemarks='" + transactionRemarks + '\'' +
                '}';
    }

    private String transactionTo;
    private Double transactionAmount;
    private String transactionRemarks;


}
