package web.services;

import java.util.Date;

/*
Create Java EE Rest web service with Servlets which incorporates with Transaction Entity of Java execution and perform following services by considering array list:

Create Transaction: POST service
Read All: GET service
Read Transaction which contains amount of given range(min and max) as parameter: GET service
 */
public class Transaction {
    private double amountInTransaction;
    private Date dateOfTransaction;
    private String beneficiary;
    private String remarks;

    public Transaction(double amountInTransaction, Date dateOfTransaction, String beneficiary, String remarks) {
        this.amountInTransaction = amountInTransaction;
        this.dateOfTransaction = dateOfTransaction;
        this.beneficiary = beneficiary;
        this.remarks = remarks;
    }

    public Transaction() {
    }

    public double getAmountInTransaction() {
        return amountInTransaction;
    }

    public void setAmountInTransaction(double amountInTransaction) {
        this.amountInTransaction = amountInTransaction;
    }

    public Date getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(Date dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
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

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "amountInTransaction=" + amountInTransaction +
                ", dateOfTransaction=" + dateOfTransaction +
                ", beneficiary='" + beneficiary + '\'' +
                ", remarks='" + remarks + '\'' +"\n"+
                '}';
    }
}
