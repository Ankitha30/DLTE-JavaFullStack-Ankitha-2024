package example.lamda;
/*
Create Class's of following and perform operations listed

Loan: loanNumber, loanAmount, loanDate, loanStatus(Open/Closed), borrowerName, borrowerContact;

MyBank: Interface

data member: ArrayList of Loan objects: initialize this arraylist with hardcoded Loan objects
abstract method:
Perform filtering  by given range of dates
MAIN: implement MyBank method by Lambda expression
 */

import java.util.Date;

public class Loan {
    private Integer loanNumber;
    private Double loanAmount;private Date loanDate;
    private String loanStatus;
    private String borrowerName;
    private Long phoneNumber;

    public Loan(Integer loanNumber, Double loanAmount, Date loanDate, String loanStatus, String borrowerName, Long phoneNumber) {
        this.loanNumber = loanNumber;
        this.loanAmount = loanAmount;
        this.loanDate = loanDate;
        this.loanStatus = loanStatus;
        this.borrowerName = borrowerName;
        this.phoneNumber = phoneNumber;
    }

    public Integer getLoanNumber() {
        return loanNumber;
    }

    public void setLoanNumber(Integer loanNumber) {
        this.loanNumber = loanNumber;
    }

    public Loan() {
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanNumber=" + loanNumber +
                ", loanAmount=" + loanAmount +
                ", loanDate='" + loanDate + '\'' +
                ", loanStatus='" + loanStatus + '\'' +
                ", borrowerName='" + borrowerName + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
