package com.thymeleaf.transaction.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TransactionSuccess  extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    CustomerService service;

    Logger logger= LoggerFactory.getLogger(TransactionSuccess.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Customer myBankOfficials= (Customer) authentication.getPrincipal();
        if(myBankOfficials.getStatus()!=0){
            if(myBankOfficials.getStatus()>1){
                myBankOfficials.setAttempts(1);
                service.updateAttempts(myBankOfficials);
            }
//            super.setDefaultTargetUrl("/credit/view");
            super.setDefaultTargetUrl("/dash");
        }
        else{
            logger.warn("Max attempts reached contact admin");
            super.setDefaultTargetUrl("/?error=contact admin");
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}