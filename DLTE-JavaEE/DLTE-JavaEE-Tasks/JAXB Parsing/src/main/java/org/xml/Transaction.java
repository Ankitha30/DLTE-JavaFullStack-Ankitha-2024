package org.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class Transaction {
    private Date dateOfTransaction;
    private Double amount;
    private String transactionTo;

    public Transaction() {
        // Default no-argument constructor
    }

    public Transaction(Date dateOfTransaction, Double amount, String transactionTo) {
        this.dateOfTransaction = dateOfTransaction;
        this.amount = amount;
        this.transactionTo = transactionTo;
    }

    @XmlElement
    public Date getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(Date dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    @XmlElement
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @XmlElement
    public String getTransactionTo() {
        return transactionTo;
    }

    public void setTransactionTo(String transactionTo) {
        this.transactionTo = transactionTo;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "dateOfTransaction=" + dateOfTransaction +
                ", amount=" + amount +
                ", transactionTo='" + transactionTo + '\'' +
                '}';
    }
}
