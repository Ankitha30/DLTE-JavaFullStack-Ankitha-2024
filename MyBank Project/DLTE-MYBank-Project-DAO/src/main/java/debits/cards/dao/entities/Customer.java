package debits.cards.dao.entities;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Customer {
    @Digits(integer = 10, fraction = 0, message = "{card.customer.valid}")
//    @NotNull(message = "{customer.id.null}")
    private int customerId;

    @Pattern(regexp = "^[a-zA-Z ]+$", message = "{customer.name.valid}")
    @NotNull(message= "{customer.name.null}")
    private String customerName;

    @NotNull(message= "{customer.address.null}")
    private String customerAddress;

    @NotNull(message= "{customer.status.null}")
    private String customerStatus;

    @Digits(integer = 10, fraction = 0, message = "{contact.valid}")
    @NotNull(message= "{customer.contact.null}")
    private long customerContact;

    @NotNull(message= "{user.name.null}")
    private String username;

    @NotNull(message= "{password.null}")
    private String password;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }

    public long getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(long customerContact) {
        this.customerContact = customerContact;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
