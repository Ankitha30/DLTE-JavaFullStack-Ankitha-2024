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
    private Long debitCardNumber;

    @NotNull(message= "{account.name.null}")
    @Range(min = 10000000000000L,max = 99999999999999L,message = "{card.acc.valid}")
    @Digits(integer=14,fraction = 0,message = "{card.acc.valid}")
    private Long accountNumber;

    @NotNull(message= "{customer.id.null}")
    @Digits(integer =6,fraction = 0,message = "{card.customer.valid}")
    private Integer customerId;

    @Digits(integer = 3, fraction = 0, message = "{card.cvv.valid}")
    @Positive(message = "{positive.number}")
    @NotNull(message= "{card.cvv.null}")
    private Integer debitCardCvv;

    @Digits(integer = 4,fraction = 0,message = "{card.pin.invalid}")
    @NotNull(message= "{card.pin.null}")
    private Integer debitCardPin;

    @NotNull(message= "{card.expiry.null}")
    private Date debitCardExpiry;

    @Pattern(regexp = "^(block)$", message = "{card.status.valid}")
    @NotNull(message= "{card.status.null}")
    private String debitCardStatus;

    @NotNull(message= "{domestic.limit.null}")
    private double domesticLimit;

    @NotNull(message= "{international.limit.null}")
    private double internationalLimit;

    public DebitCard(Long debitCardNumber, Long accountNumber, Integer customerId, Integer debitCardCvv, Integer debitCardPin, Date debitCardExpiry, String debitCardStatus, double domesticLimit, double internationalLimit) {
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

    public Long getDebitCardNumber() {
        return debitCardNumber;
    }

    public void setDebitCardNumber(Long debitCardNumber) {
        this.debitCardNumber = debitCardNumber;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getDebitCardCvv() {
        return debitCardCvv;
    }

    public void setDebitCardCvv(Integer debitCardCvv) {
        this.debitCardCvv = debitCardCvv;
    }

    public Integer getDebitCardPin() {
        return debitCardPin;
    }

    public void setDebitCardPin(Integer debitCardPin) {
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
