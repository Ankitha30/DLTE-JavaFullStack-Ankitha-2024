package org.example;

import java.io.Serializable;
import java.util.Date;

/*
nterface "Activity" : abstract Create
Class "MyBankDatabase": ArrayList of CreditCard

implement Activity to MyBankDatabase

MAIN :

Create CreditCard object write in to the file as soon as object inserted into arraylist as append mode. when you execute Create method
 */
public class CreditCard  implements Serializable {

    private Long creditCardNumber;
    private String creditCardHolder;
    private Date creditCardExpiry;
    private Integer creditCardCvv;

    @Override
    public String toString() {
        return "CreditCard{" +
                "creditCardNumber=" + creditCardNumber +
                ", creditCardHolder='" + creditCardHolder + '\'' +
                ", creditCardExpiry=" + creditCardExpiry +
                ", creditCardCvv=" + creditCardCvv +
                ", creditCardLimit=" + creditCardLimit +
                '}';
    }

    public Long getCreditCardNumber() {
        return creditCardNumber;
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

    public CreditCard(Long creditCardNumber, String creditCardHolder, Date creditCardExpiry, Integer creditCardCvv, Integer creditCardLimit) {
        this.creditCardNumber = creditCardNumber;
        this.creditCardHolder = creditCardHolder;
        this.creditCardExpiry = creditCardExpiry;
        this.creditCardCvv = creditCardCvv;
        this.creditCardLimit = creditCardLimit;
    }

    private Integer creditCardLimit;
}
