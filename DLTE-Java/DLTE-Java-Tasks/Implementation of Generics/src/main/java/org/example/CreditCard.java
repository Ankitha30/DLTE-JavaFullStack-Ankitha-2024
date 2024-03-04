package org.example;

import java.util.Date;

public class CreditCard {
    private Long creditCardNumber;
    private String creditCardHolder;
    private Date creditCardExpiry;
    private Integer creditCardCvv;
    private Integer creditCardLimit;

    public Long getCreditCardNumber() {
        return creditCardNumber;
    }

    public CreditCard() {
    }

    public CreditCard(Long creditCardNumber, String creditCardHolder, Date creditCardExpiry, Integer creditCardCvv, Integer creditCardLimit, Integer creditCardPin) {
        this.creditCardNumber = creditCardNumber;
        this.creditCardHolder = creditCardHolder;
        this.creditCardExpiry = creditCardExpiry;
        this.creditCardCvv = creditCardCvv;
        this.creditCardLimit = creditCardLimit;

//        this.dateOfBillPayment = dateOfBillPayment;
        this.creditCardPin = creditCardPin;
    }

    public void setCreditCardNumber(Long creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCreditCardHolder() {
        return creditCardHolder;
    }

    public void setCreditCardHolder(String creditCardHolder) {
        this.creditCardHolder = creditCardHolder;
    }

    public Date getCreditCardExpiry() {
        return creditCardExpiry;
    }

    public void setCreditCardExpiry(Date creditCardExpiry) {
        this.creditCardExpiry = creditCardExpiry;
    }

    public Integer getCreditCardCvv() {
        return creditCardCvv;
    }

    public void setCreditCardCvv(Integer creditCardCvv) {
        this.creditCardCvv = creditCardCvv;
    }

    public Integer getCreditCardLimit() {
        return creditCardLimit;
    }

    public void setCreditCardLimit(Integer creditCardLimit) {
        this.creditCardLimit = creditCardLimit;
    }



    public Integer getCreditCardPin() {
        return creditCardPin;
    }

    public void setCreditCardPin(Integer creditCardPin) {
        this.creditCardPin = creditCardPin;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "creditCardNumber=" + creditCardNumber +
                ", creditCardHolder='" + creditCardHolder + '\'' +
                ", creditCardExpiry=" + creditCardExpiry +
                ", creditCardCvv=" + creditCardCvv +
                ", creditCardLimit=" + creditCardLimit +
//                ", dateOfBillGeneration=" + dateOfBillGeneration +
//                ", dateOfBillPayment=" + dateOfBillPayment +
                ", creditCardPin=" + creditCardPin +"\n"+
                '}';
    }

//    private Date dateOfBillGeneration;
    private Date dateOfBillPayment;
    private Integer creditCardPin;
}
