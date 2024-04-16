package debits.cards.dao.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Collection;

public class CardSecurity implements UserDetails {

    @Pattern(regexp = "^[a-zA-Z ]+$", message = "{customer.name.valid}")
    @NotNull(message= "{customer.name.null}")
    private String customerName;
    @NotNull(message= "{customer.address.null}")
    private String customerAddress;
    @NotNull(message= "{customer.status.null}")
    private String customerStatus;
    @Digits(integer = 10, fraction = 0, message = "{contact.valid}")
    @NotNull(message= "{customer.contact.null}")
    private Long customerContact;
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "{customer.name.valid}")
    @NotNull(message= "{customer.name.null}")
    private String username;
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "{customer.name.valid}")
    @NotNull(message= "{customer.name.null}")
    private String password;
    private final int maxAttempt = 3;
    private Integer attempts;

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

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMaxAttempt() {
        return maxAttempt;
    }

    public Integer getAttempts() {
        return attempts;
    }

    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
    }

}
