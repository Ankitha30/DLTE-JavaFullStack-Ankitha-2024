package com.thymeleaf.transaction.entity;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.ResourceBundle;

@Component
public class Transaction {
    ResourceBundle resourceBundle = ResourceBundle.getBundle("transaction");
    @NotNull(message = "{id.null}")
    @Digits(integer = 3, fraction = 0)
    private long transactionId;
    @NotNull(message = "{date.transaction}")
    private Date transactionDate;
    @NotBlank(message = "{transactionBy.null}")
    private String transactionBy;
    @NotBlank(message = "{transactionTo.null}")
    private String transactionTo;
    @NotNull(message = "{amount.null}")
    private double transactionAmount;
    @NotBlank(message = "{remarks.blank}")
    private String remarks;

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
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

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
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
                "transactionId=" + transactionId +
                ", transactionDate=" + transactionDate +
                ", transactionBy='" + transactionBy + '\'' +
                ", transactionTo='" + transactionTo + '\'' +
                ", transactionAmount=" + transactionAmount +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
