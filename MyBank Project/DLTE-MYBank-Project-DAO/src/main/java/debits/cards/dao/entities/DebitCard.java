package debits.cards.dao.entities;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.Date;

public class DebitCard {
    @NotNull(message= "{card.number.null}")
    @Range(min = 3692468135796670L,max = 9999999999999999L,message = "{card.debit.valid}")
    @Digits(integer=16,fraction = 0,message = "{card.debit.valid}")
    private long debitCardNumber;

    @NotNull(message= "{account.name.null}")
    @Range(min = 10000000000000L,max = 99999999999999L,message = "{card.acc.valid}")
    @Digits(integer=14,fraction = 0,message = "{card.acc.valid}")
    private long accountNumber;

//    @NotNull(message= "{customer.id.null}")
//    @Digits(integer =6,fraction = 0,message = "{card.customer.valid}")
//    @Range(min = 100000L,max = 999999L,message = "{card.customer.valid}")
    private int customerId;

    @Digits(integer = 3, fraction = 0, message = "{card.cvv.valid}")
    @Range(min = 100L,max = 999L,message = "{card.cvv.valid}")
    @Positive(message = "{positive.number}")
    @NotNull(message= "{card.cvv.null}")
    private int debitCardCvv;

    @Digits(integer = 4,fraction = 0,message = "{card.pin.invalid}")
    @Range(min = 1000L,max = 9999L,message = "{card.pin.valid}")
    @NotNull(message= "{card.pin.null}")
    private int debitCardPin;

    @NotNull(message= "{card.expiry.null}")
    private Date debitCardExpiry;

    @Pattern(regexp = "^(block)$", message = "{card.status.valid}")
    @NotNull(message= "{card.status.null}")
    private String debitCardStatus;

    @NotNull(message= "{domestic.limit.null}")
    private double domesticLimit;

    @NotNull(message= "{international.limit.null}")
    private double internationalLimit;

    public DebitCard(long debitCardNumber, long accountNumber, int customerId, int debitCardCvv, int debitCardPin, Date debitCardExpiry, String debitCardStatus, double domesticLimit, double internationalLimit) {
        this.debitCardNumber = debitCardNumber;
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.debitCardCvv = debitCardCvv;
        this.debitCardPin = debitCardPin;
        this.debitCardExpiry = debitCardExpiry;
        this.debitCardStatus = debitCardStatus;
        this.domesticLimit = domesticLimit;
        this.internationalLimit = internationalLimit;
    }

    public DebitCard() {
    }

    public long getDebitCardNumber() {
        return debitCardNumber;
    }

    public void setDebitCardNumber(long debitCardNumber) {
        this.debitCardNumber = debitCardNumber;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getDebitCardCvv() {
        return debitCardCvv;
    }

    public void setDebitCardCvv(int debitCardCvv) {
        this.debitCardCvv = debitCardCvv;
    }

    public int getDebitCardPin() {
        return debitCardPin;
    }

    public void setDebitCardPin(int debitCardPin) {
        this.debitCardPin = debitCardPin;
    }

    public Date getDebitCardExpiry() {
        return debitCardExpiry;
    }

    public void setDebitCardExpiry(Date debitCardExpiry) {
        this.debitCardExpiry = debitCardExpiry;
    }

    public String getDebitCardStatus() {
        return debitCardStatus;
    }

    public void setDebitCardStatus(String debitCardStatus) {
        this.debitCardStatus = debitCardStatus;
    }

    public double getDomesticLimit() {
        return domesticLimit;
    }

    public void setDomesticLimit(double domesticLimit) {
        this.domesticLimit = domesticLimit;
    }

    public double getInternationalLimit() {
        return internationalLimit;
    }

    public void setInternationalLimit(double internationalLimit) {
        this.internationalLimit = internationalLimit;
    }

    @Override
    public String toString() {
        return "DebitCard{" +
                "debitCardNumber=" + debitCardNumber +
                ", accountNumber=" + accountNumber +
                ", customerId=" + customerId +
                ", debitCardCvv=" + debitCardCvv +
                ", debitCardPin=" + debitCardPin +
                ", debitCardExpiry=" + debitCardExpiry +
                ", debitCardStatus='" + debitCardStatus + '\'' +
                ", domesticLimit=" + domesticLimit +
                ", internationalLimit=" + internationalLimit +
                '}';
    }




    }
