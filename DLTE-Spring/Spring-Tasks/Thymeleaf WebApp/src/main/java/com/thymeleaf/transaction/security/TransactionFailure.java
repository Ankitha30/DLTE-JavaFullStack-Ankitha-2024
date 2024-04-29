package com.thymeleaf.transaction.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class TransactionFailure  extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    CustomerService service;

    Logger logger= LoggerFactory.getLogger(TransactionFailure.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        try{
        String username = request.getParameter("username");

        Customer customer = service.findByUsername(username);
            System.out.println(customer.toString());
        if(customer!=null) {
            if (customer.getStatus() != 0) {
                if (customer.getAttempts() < customer.getMaxAttempt()) {
                    customer.setAttempts(customer.getAttempts() + 1);
                    service.updateAttempts(customer);
                    logger.warn("Invalid credentials and attempts taken");
                    exception = new LockedException((4 - customer.getAttempts()) + "Attempts are taken");
                    String err = customer.getAttempts() + " " + exception.getMessage();
                    logger.warn(err);
                    super.setDefaultFailureUrl("/?error=" + err);
                } else {
                    service.updateStatus(customer);
                    exception = new LockedException("Max Attempts reached account is suspended");
                    super.setDefaultFailureUrl("/?error=" + exception.getMessage());
                }
            }
            else {
                super.setDefaultFailureUrl("/?errors=User not exists");
            }
        }
       }catch (UsernameNotFoundException e){
           logger.info(e.toString());
           logger.warn("Account suspended");
           exception = new LockedException("Username not found");
           super.setDefaultFailureUrl("/?error=" + exception.getMessage());

       }
        super.onAuthenticationFailure(request, response, exception);
    }
}