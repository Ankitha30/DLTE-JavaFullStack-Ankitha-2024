package debits.cards.dao.entities;

import javax.validation.constraints.NotNull;

public class Customer {

    @NotNull(message = "{customer.id.null}")
    private Integer customerId;
    @NotNull(message= "{customer.name.null}")
    private String customerName;
    @NotNull(message= "{customer.address.null}")
    private String customerAddress;
    @NotNull(message= "{customer.status.null}")
    private String customerStatus;
    @NotNull(message= "{customer.contact.null}")
    private Long customerContact;
    @NotNull(message= "{user.name.null}")
    private String username;
    @NotNull(message= "{password.null}")
    private String password;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
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

    public Long getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(Long customerContact) {
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
