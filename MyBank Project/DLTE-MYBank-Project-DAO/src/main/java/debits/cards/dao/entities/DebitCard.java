package debits.cards.dao.entities;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class DebitCard {
    @NotNull(message= "{card.number.null}")
    private Long debitCardNumber;
    @NotNull(message= "{account.name.null}")
    private Long accountNumber;
    @NotNull(message= "{customer.id.null}")
    private Integer customerId;
    @NotNull(message= "{card.cvv.null}")
    private Integer debitCardCvv;
    @NotNull(message= "{card.pin.null}")
    private Integer debitCardPin;
    @Digits(integer = 4, fraction = 0, message = "{card.num.valid}")
    @NotNull(message= "{card.expiry.null}")
    private Date debitCardExpiry;
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
