package spring.boot.jdbc.springbootjdbc.model;

import java.util.Date;

/*
Entity: Transaction Id,
transaction date,
transaction by(sender),
 transaction to(receiver),
 transaction amount,
 transaction for(remarks)
 */
public class Transaction {
    private int transactionId;
    private Date date;
    private String transactionBy,transactionTo;
    private Double transactionAmount;
    private String remarks;

    public Transaction(int transactionId, Date date, String transactionBy, String transactionTo, Double transactionAmount, String remarks) {
        this.transactionId = transactionId;
        this.date = date;
        this.transactionBy = transactionBy;
        this.transactionTo = transactionTo;
        this.transactionAmount = transactionAmount;
        this.remarks = remarks;
    }

    public Transaction() {
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", date=" + date +
                ", transactionBy='" + transactionBy + '\'' +
                ", transactionTo='" + transactionTo + '\'' +
                ", transactionAmount=" + transactionAmount +
                ", remarks='" + remarks + '\'' +
                '}';
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTransactionBy() {
        return transactionBy;
    }

    public void setTransactionBy(String transactionBy) {
        this.transactionBy = transactionBy;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
