package basics.services;

import lombok.Data;

import java.util.Date;

@Data
public class CreditCard {
    public  CreditCard(){
        System.out.println("Initialize card properties manually");
    }

    public CreditCard(Long creditCardNumber, String creditCardHolder, Integer creditCvv, Date creditCardExpiry, Date dateOfBillPayment, Date dateofBilGeneration, Integer creditCardSavings, Integer creditCardPin, Integer creditCardLimit) {
        this.creditCardNumber = creditCardNumber;
        this.creditCardHolder = creditCardHolder;
        this.creditCvv = creditCvv;
        CreditCardExpiry = creditCardExpiry;
        DateOfBillPayment = dateOfBillPayment;
        DateofBilGeneration = dateofBilGeneration;
        this.creditCardSavings = creditCardSavings;
        CreditCardPin = creditCardPin;
        CreditCardLimit = creditCardLimit;
    }
    private Long creditCardNumber;
    private String  creditCardHolder;
    private Integer creditCvv;
    private Date CreditCardExpiry;
    private Date DateOfBillPayment;
    private Date DateofBilGeneration;
    private Integer creditCardSavings;
    private Integer CreditCardPin;
    private Integer CreditCardLimit;
//
//    public Long getCreditCardNumber() {
//        return creditCardNumber;
//    }
//
//    public String getCreditCardHolder() {
//        return creditCardHolder;
//    }
//
//    public Integer getCreditCardSavings() {
//        return creditCardSavings;
//    }
//
//
//
//    public Integer getCreditCvv() {
//        return creditCvv;
//    }
//
//    public Date getCreditCardExpiry() {
//        return CreditCardExpiry;
//    }
//
//    public Date getDateOfBillPayment() {
//        return DateOfBillPayment;
//    }
//
//    public Date getDateofBilGeneration() {
//        return DateofBilGeneration;
//    }
//
//
//    public void setCreditCardNumber(Long creditCardNumber) {
//        this.creditCardNumber = creditCardNumber;
//    }
//
//    public void setCreditCardHolder(String creditCardHolder) {
//        this.creditCardHolder = creditCardHolder;
//    }
//
//    public void setCreditCardSavings(Integer creditCardSavings) {
//        this.creditCardSavings = creditCardSavings;
//    }
//
//
//
//    public void setCreditCvv(Integer creditCvv) {
//        this.creditCvv = creditCvv;
//    }
//
//    public void setCreditCardExpiry(Date creditCardExpiry) {
//        CreditCardExpiry = creditCardExpiry;
//    }
//
//    public void setDateOfBillPayment(Date dateOfBillPayment) {
//        DateOfBillPayment = dateOfBillPayment;
//    }
//
//    public void setDateofBilGeneration(Date dateofBileGneration) {
//        DateofBilGeneration = DateofBilGeneration;
//    }
//
//    public void setCreditCardPin(Integer creditCardPin) {
//        CreditCardPin = creditCardPin;
//    }
//
//    public Integer getCreditCardPin() {
//        return CreditCardPin;
//    }
//
//
//
//    public void setCreditCardLimit(Integer creditCardLimit) {
//        CreditCardLimit = creditCardLimit;
//    }
//
//    public Integer getCreditCardLimit() {
//        return CreditCardLimit;
//    }


}
