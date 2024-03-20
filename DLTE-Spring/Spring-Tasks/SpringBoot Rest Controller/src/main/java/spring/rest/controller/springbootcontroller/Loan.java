package spring.rest.controller.springbootcontroller;

import java.util.Date;

public class Loan {
    private Long number;
    private Integer loanAmount;
//    private Date loanDate;
    private String loanStatus;
    private String borrowerName;

    public Loan() {
    }

    public Loan(Long number, Integer loanAmount, String loanStatus, String borrowerName) {
        this.number = number;
        this.loanAmount = loanAmount;
        this.loanStatus = loanStatus;
        this.borrowerName = borrowerName;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Integer getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Integer loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "number=" + number +
                ", loanAmount=" + loanAmount +
                ", loanStatus='" + loanStatus + '\'' +
                ", borrowerName='" + borrowerName + '\'' +
                '}';
    }
}
