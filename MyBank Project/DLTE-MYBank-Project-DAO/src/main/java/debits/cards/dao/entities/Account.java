package debits.cards.dao.entities;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Account {


    @NotNull(message = "{account.id.null}")
    private Integer accountId;
    @NotNull(message = "{customer.id.null}")
    private Integer customerId;
    @NotNull(message = "{account.type.null}")
    @Pattern(regexp = "^[a-zA-Z\\s]*$",message = "{account.type.valid}")
    private String accountType;
    @NotNull(message = "{account.num.null}")
    @Digits(integer = 14, fraction = 0, message = "{account.num.valid}")
    private Long accountNumber;
    @NotNull(message = "{account.status.null}")
    private String accountStatus;
    @NotNull(message = "{account.balance.null}")
    private Double accountBalance;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }
}
