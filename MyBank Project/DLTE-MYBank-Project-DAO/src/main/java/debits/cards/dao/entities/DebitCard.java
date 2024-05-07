package debits.cards.dao.entities;

import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Component

public class DebitCard {

    private long debitCardNumber;
    private long accountNumber;
    private int customerId;
    private int debitCardCvv;
    @Digits(integer = 4, fraction = 0, message = "{card.pin.valid}")
    @Range(min = 1000L, max = 9999L, message = "{card.pin.valid}")
    @NotNull(message = "{card.pin.null}")
    private int debitCardPin;
    private Date debitCardExpiry;
    private String debitCardStatus;
    private double domesticLimit;
    private double internationalLimit;

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


}
