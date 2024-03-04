package basics.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

//   Transaction: dateOfTransaction, amountInTransaction, to, remarks(Family, Education, Emergency, Bills, Friend)

public class Transaction {
    private Date dateOfTransaction;

    public Transaction(Date dateOfTransaction, Integer amountInTransaction, String beneficiary, String remarks) {
        this.dateOfTransaction = dateOfTransaction;
        this.amountInTransaction = amountInTransaction;
        this.beneficiary = beneficiary;
        this.remarks = remarks;
    }

    public void setDateOfTransaction(Date dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    public void setAmountInTransaction(Integer amountInTransaction) {
        this.amountInTransaction = amountInTransaction;
    }

    public void setBeneficiary(String beneficiary) {
        this.beneficiary = beneficiary;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    private Integer amountInTransaction;
    private String beneficiary;

    public Date getDateOfTransaction() {
        return dateOfTransaction;
    }

    public Integer getAmountInTransaction() {
        return amountInTransaction;
    }

    public String getBeneficiary() {
        return beneficiary;
    }

    public String getRemarks() {
        return remarks;
    }

    public Transaction() {
        System.out.println("Initialize");
    }

    private String remarks;
}
