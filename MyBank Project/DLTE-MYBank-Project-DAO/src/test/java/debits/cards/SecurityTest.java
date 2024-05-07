package debits.cards;


import debits.cards.dao.entities.Customer;
import debits.cards.dao.services.CardSecurityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class SecurityTest {

    @InjectMocks
    CardSecurityService cardSecurityService;
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application"); // Stubbing resource bundle
    @Mock
    private JdbcTemplate jdbcTemplate;

    Customer userDetails;

    @BeforeEach
    void setUp() {
        userDetails = new Customer();
    }


    @Test
    void testSigningUp() {
        Customer customer = new Customer();
        customer.setCustomerName("Anu S");
        customer.setCustomerAddress("Kallamundkur");
        customer.setCustomerStatus("active");
        customer.setCustomerContact(9900764532L);
        customer.setUsername("anu");
        customer.setPassword("anu123");

        // Stubbing jdbcTemplate update
        when(jdbcTemplate.update(
                "insert into mybank_app_customer (CUSTOMER_NAME, CUSTOMER_ADDRESS, CUSTOMER_STATUS, CUSTOMER_CONTACT, USERNAME, PASSWORD) values(?,?,?,?,?,?)",
                customer.getCustomerName(),
                customer.getCustomerAddress(),
                customer.getCustomerStatus(),
                customer.getCustomerContact(),
                customer.getUsername(),
                customer.getPassword())
        ).thenReturn(1);

        Customer result = cardSecurityService.signingUp(customer);

        assertEquals(customer, result);
        // Verify that jdbcTemplate update method is invoked with specific arguments
        verify(jdbcTemplate, times(1)).update(
                "insert into mybank_app_customer (CUSTOMER_NAME, CUSTOMER_ADDRESS, CUSTOMER_STATUS, CUSTOMER_CONTACT, USERNAME, PASSWORD) values(?,?,?,?,?,?)",
                customer.getCustomerName(),
                customer.getCustomerAddress(),
                customer.getCustomerStatus(),
                customer.getCustomerContact(),
                customer.getUsername(),
                customer.getPassword()
        );
    }


    @Test
    void testFindByUserName_ExistingUser() {
        Customer customer = new Customer();
        customer.setUsername("anu");

        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(customerList);
        Customer result = cardSecurityService.findByUserName("anu");
        assertEquals(customer, result);
        verify(jdbcTemplate, times(1)).query(anyString(), any(BeanPropertyRowMapper.class));
    }


    @Test
    void testFindByUserName_NonExistingUser() {

        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(new ArrayList<>());
        assertThrows(UsernameNotFoundException.class, () -> cardSecurityService.findByUserName("abc"));
    }

    @Test
    void testUpdateAttempts() {

        Customer customer = new Customer();
        customer.setCustomerName("Anu S");
        customer.setCustomerAddress("Kallamundkur");
        customer.setCustomerStatus("active");
        customer.setCustomerContact(9900764532L);
        customer.setUsername("anu");
        customer.setPassword("anu123");
        customer.setAttempts(2);

        cardSecurityService.updateAttempts(customer);
        assertEquals(resourceBundle.getString("attempts.updated"), "Attempts are Updated");

    }

    @Test
    void testUpdateStatus() {

        Customer customer = new Customer();
        customer.setCustomerName("Anu S");
        customer.setCustomerAddress("Kallamundkur");
        customer.setCustomerStatus("active");
        customer.setCustomerContact(9900764532L);
        customer.setUsername("anu");
        customer.setPassword("anu123");
        customer.setAttempts(2);

        cardSecurityService.updateStatus(customer);
        assertEquals(resourceBundle.getString("status.changed"), "Status Changed");

    }

    @Test
    void testGetUserName() {
        long accountNumber = 90876543212345L;

        Mockito.when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(String.class))).thenReturn("anu");

        String result = cardSecurityService.getUserName(accountNumber);

        assertEquals("anu", result);
    }

    @Test
    void testGetCustomerName() {
        String username = "anu";

        Mockito.when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(String.class))).thenReturn("Anu S");

        String result = cardSecurityService.getCustomerName(username);

        assertEquals("Anu S", result);
    }

    @Test
    void testLoadUserByUsername() {
        String username = "anu";

        Customer customer = new Customer();

        customer.setUsername(username);

        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);

        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class))).thenReturn(customerList);


        assertEquals(customer, cardSecurityService.loadUserByUsername(username));
    }


    @Test
    public void testPasswordMatch() {

        CardSecurityService myBankUsersServices = mock(CardSecurityService.class);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String username = "aru";
        String rawPassword = "abc123";
        String encodedPassword = passwordEncoder.encode(rawPassword);
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setPassword(encodedPassword);
        when(myBankUsersServices.loadUserByUsername(username))

                .thenReturn(customer);

        UserDetails userDetails = myBankUsersServices.loadUserByUsername(username);

        String enteredPassword = "abc123";

        // Verify the result

        assertTrue(passwordEncoder.matches(enteredPassword, userDetails.getPassword()));

    }


    @Test
    public void testIsEnabled() {
        assertTrue(userDetails.isEnabled());
    }

    @Test
    public void testIsAccountNonExpired() {
        assertTrue(userDetails.isAccountNonExpired());
    }

    @Test
    public void testIsAccountNonLocked() {
        assertTrue(userDetails.isAccountNonLocked());
    }

}
