package com.thymeleaf.transaction.security;


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
public class CustomerService implements UserDetailsService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    Logger logger= LoggerFactory.getLogger(CustomerService.class);

    public Customer signUp(Customer customer) {
        jdbcTemplate.update("INSERT INTO customers (name, username, password, email, contact, role) VALUES (?, ?, ?, ?, ?, ?)", new Object[]{
                customer.getName(), customer.getUsername(),
                customer.getPassword(), customer.getEmail(),
                customer.getContact(),
                customer.getRole()
        });

        return customer;
    }

    public Customer findByUsername(String username) {
        Customer customer = jdbcTemplate.queryForObject("select * from customers where username=?",
                new Object[]{username}, new BeanPropertyRowMapper<>(Customer.class));
        return customer;
    }

    public void updateAttempts(Customer myBankOfficials){
        jdbcTemplate.update("update Customers set attempts=? where username=?",
                new Object[]{myBankOfficials.getAttempts(),myBankOfficials.getUsername()});
        logger.info("Attempts are updated");
    }

    public void updateStatus(Customer customer){
        jdbcTemplate.update("update customers set status=0 where username=?",
                new Object[]{customer.getUsername()});
        logger.info("Status has changed");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer users = findByUsername(username);
        if (users == null)
            throw new UsernameNotFoundException(username);
        return users;
    }
}
