package org.example;

import java.util.Date;

/*
Transaction: dateOfTransaction, amountInTransaction, to, remarks(Family, Education, Emergency, Bills, Friend)

Resource class :

ArrayList

Implement multithreading in resource class which has following analysis methods

Analysis: CLI in run method

Filter based on given ranges of date
least amount transferred
maximum amount transferred
number of transaction made to particular beneficiary
filter based on particular remarks

using concurrency lock make the resource as thread safe

MAIN:

Create ScheduledExecutorService with period of 5 and schedule for 30 seconds
 */
public class Transaction {
    private Date dateOfTransaction;

    @Override
    public String toString() {
        return "Transaction{" +
                "dateOfTransaction=" + dateOfTransaction +
                ", amountInTransaction=" + amountInTransaction +
                ", beneficiary='" + beneficiary + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }

    public Date getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(Date dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    public Integer getAmountInTransaction() {
        return amountInTransaction;
    }

    public void setAmountInTransaction(Integer amountInTransaction) {
        this.amountInTransaction = amountInTransaction;
    }

    public String getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(String beneficiary) {
        this.beneficiary = beneficiary;
    }

    public String getRemarks() {
        return remarks;
    }

    public Transaction() {
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Transaction(Date dateOfTransaction, Integer amountInTransaction, String beneficiary, String remarks) {
        this.dateOfTransaction = dateOfTransaction;
        this.amountInTransaction = amountInTransaction;
        this.beneficiary = beneficiary;
        this.remarks = remarks;
    }

    private Integer amountInTransaction;
    private String beneficiary;
    private String remarks;
}