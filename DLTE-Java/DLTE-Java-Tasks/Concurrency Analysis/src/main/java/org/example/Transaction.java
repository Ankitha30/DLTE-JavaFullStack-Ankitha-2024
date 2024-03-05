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
    public Date getDateOfTransaction() {
        return dateOfTransaction;
    }

    public Transaction() {
    }

    public void setDateOfTransaction(Date dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    @Override
    public String toString() {
        return "Transaction{" +
                "dateOfTransaction=" + dateOfTransaction +
                ", remarks='" + remarks + '\'' +
                ", amountInTransaction=" + amountInTransaction +
                ", beneficiary='" + beneficiary + '\'' +
                '}';
    }

    private Date dateOfTransaction;
    private String remarks;

    public Transaction(Date dateOfTransaction, String remarks, Integer amountInTransaction, String beneficiary) {
        this.dateOfTransaction = dateOfTransaction;
        this.remarks = remarks;
        this.amountInTransaction = amountInTransaction;
        this.beneficiary = beneficiary;
    }

    private Integer amountInTransaction;
    private String beneficiary;
}
