package basics.service;

import java.util.Date;

//loanNumber, loanAmount, loanDate, loanStatus(Open/Closed), borrowerName, borrowerContact
public class Loan {
    private Integer loanNumber;
    private Double loanAmount;
    private Date loanDate;
    private String loanStatus;
    private String borrowerName;
    private Long phonenumber;

    public Loan(Integer loanNumber, Double loanAmount, Date loanDate, String loanStatus, String borrowerName, Long phonenumber) {
        this.loanNumber = loanNumber;
        this.loanAmount = loanAmount;
        this.loanDate = loanDate;
        this.loanStatus = loanStatus;
        this.borrowerName = borrowerName;
        this.phonenumber = phonenumber;
    }

    public Loan() {
    }

    public Integer getLoanNumber() {
        return loanNumber;
    }

    public void setLoanNumber(Integer loanNumber) {
        this.loanNumber = loanNumber;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmoun) {
        this.loanAmount = loanAmoun;
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

    public Long getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(Long phonenumber) {
        this.phonenumber = phonenumber;
    }
}
