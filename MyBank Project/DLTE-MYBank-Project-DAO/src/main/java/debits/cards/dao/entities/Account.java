package debits.cards.dao.entities;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Account {


    @NotNull(message = "{account.id.null}")
    private int accountId;
    @NotNull(message = "{customer.id.null}")
    private int customerId;
    @NotNull(message = "{account.type.null}")
    @Pattern(regexp = "^[a-zA-Z\\s]*$",message = "{account.type.valid}")
    private String accountType;
    @NotNull(message = "{account.num.null}")
    @Digits(integer = 14, fraction = 0, message = "{account.num.valid}")
    private long accountNumber;
    @NotNull(message = "{account.status.null}")
    private String accountStatus;
    @NotNull(message = "{account.balance.null}")
    private double accountBalance;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }
}
