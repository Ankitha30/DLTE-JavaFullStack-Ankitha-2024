package debits.cards.dao.services;

import debits.cards.dao.entities.CardSecurity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

@Service
public class CardSecurityService implements UserDetailsService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    ResourceBundle resourceBundle=ResourceBundle.getBundle("application");

    Logger logger = LoggerFactory.getLogger(CardSecurityService.class);

    public CardSecurity signingUp(CardSecurity cardSecurity) {
        jdbcTemplate.update("insert into mybank_app_customer (CUSTOMER_NAME, CUSTOMER_ADDRESS, CUSTOMER_STATUS, CUSTOMER_CONTACT, USERNAME, PASSWORD) values(?,?,?,?,?,?)", new Object[]{cardSecurity.getCustomerName(), cardSecurity.getCustomerAddress(), cardSecurity.getCustomerStatus(), cardSecurity.getCustomerContact(), cardSecurity.getUsername(), cardSecurity.getPassword()});
        logger.info("New customer registered: {}", cardSecurity.getUsername());
        return cardSecurity;
    }

    public CardSecurity findByUserName(String username) {
        List<CardSecurity> customerList = jdbcTemplate.query("select * from mybank_app_customer", new BeanPropertyRowMapper<>(CardSecurity.class));
        CardSecurity customer = customerList.stream().filter(each->each.getUsername().equals(username)).findFirst().orElse(null);
        if(customer ==null)
            throw new UsernameNotFoundException(username);

        return customer;


    }

    public void updateAttempts(CardSecurity cardSecurity) {
        jdbcTemplate.update("update mybank_app_customer set attempts = ? where username = ?", new Object[]{cardSecurity.getAttempts(), cardSecurity.getUsername()});
        logger.info(resourceBundle.getString("attempts.updated"));
    }

    public void updateStatus(CardSecurity cardSecurity) {
        jdbcTemplate.update("update mybank_app_customer set customer_status = 'block' where username = ?", new Object[]{cardSecurity.getUsername()});
        logger.info(resourceBundle.getString("status.changed"));
    }

    public String getUserName(long accountNumber) {
        try {
            String sql = "SELECT c.username FROM mybank_app_customer c JOIN mybank_app_account a ON c.customer_id = a.customer_id  JOIN mybank_app_debitcard d ON a.account_number = d.account_number WHERE d.account_number =  ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{accountNumber}, String.class);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }

    public String getCustomerName(String user) {
        try {
            String sql = "SELECT c.CUSTOMER_NAME FROM mybank_app_customer c WHERE c.username =  ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{user}, String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CardSecurity cardSecurity = findByUserName(username);
        if (cardSecurity == null)
            throw new UsernameNotFoundException(username);
        return cardSecurity;
    }
}
