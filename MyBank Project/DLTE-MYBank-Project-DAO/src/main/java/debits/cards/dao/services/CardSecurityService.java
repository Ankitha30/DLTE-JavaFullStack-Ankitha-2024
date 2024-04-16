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

@Service
public class CardSecurityService implements UserDetailsService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    Logger logger = LoggerFactory.getLogger(CardSecurityService.class);

    public CardSecurity signingUp(CardSecurity cardSecurity){
        jdbcTemplate.update("insert into mybank_app_customer (CUSTOMER_NAME, CUSTOMER_ADDRESS, CUSTOMER_STATUS, CUSTOMER_CONTACT, USERNAME, PASSWORD) values(?,?,?,?,?,?)",new Object[]{cardSecurity.getCustomerName(),cardSecurity.getCustomerAddress(),cardSecurity.getCustomerStatus(),cardSecurity.getCustomerContact(),cardSecurity.getUsername(),cardSecurity.getPassword()});
        logger.info("New customer registered: {}", cardSecurity.getUsername());
        return cardSecurity;
    }

    public CardSecurity findByUserName(String username){
        CardSecurity cardSecurity = jdbcTemplate.queryForObject("select * from mybank_app_customer where username = ?",new Object[]{username},new BeanPropertyRowMapper<>(CardSecurity.class));
        return cardSecurity;
    }

    public void updateAttempts(CardSecurity cardSecurity){
        jdbcTemplate.update("update mybank_app_customer set attempts = ? where username = ?",new Object[]{cardSecurity.getAttempts(),cardSecurity.getUsername()});
        logger.info("Attempts are Updated");
    }
    public void updateStatus(CardSecurity cardSecurity){
        jdbcTemplate.update("update mybank_app_customer set customer_status = 'block' where username = ?",new Object[]{cardSecurity.getUsername()});
        logger.info("Status Changed");
    }

    public String getCustomerName(int customerId) {
        String sql = "SELECT c.username " +
                "FROM MYBANK_APP_CUSTOMER c " +
                "JOIN MYBANK_APP_ACCOUNT a ON c.CUSTOMER_ID = a.CUSTOMER_ID " +
                "WHERE c.CUSTOMER_ID = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{customerId}, String.class);
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CardSecurity cardSecurity = findByUserName(username);
        if(cardSecurity==null)
            throw new UsernameNotFoundException(username);
        return cardSecurity;
    }
}
