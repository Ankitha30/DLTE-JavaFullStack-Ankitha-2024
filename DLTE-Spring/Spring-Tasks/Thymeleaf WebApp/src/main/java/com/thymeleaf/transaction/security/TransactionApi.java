package com.thymeleaf.transaction.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class TransactionApi {
    @Autowired
    CustomerService service;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public Customer save(@RequestBody Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return service.signUp(customer);
    }
}