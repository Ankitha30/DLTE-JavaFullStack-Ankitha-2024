package debit.card.application.security;

import debits.cards.dao.entities.Customer;
import debits.cards.dao.services.CardSecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;


@Component
public class CardFailure extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    CardSecurityService service;

    Logger logger = LoggerFactory.getLogger(CardFailure.class);
    ResourceBundle resourceBundle = ResourceBundle.getBundle("cards");


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        try {
            String username = request.getParameter("username");
            if (username != null && !username.isEmpty()) {
                Customer customer = service.findByUserName(username);
                if (customer != null) {
                    if (customer.getCustomerStatus().equalsIgnoreCase("active")) {
                        if (customer.getAttempts() < customer.getMaxAttempt()) {
                            customer.setAttempts(customer.getAttempts() + 1);
                            service.updateAttempts(customer);
                            logger.warn(resourceBundle.getString("invalid.data"));
                            exception = new LockedException("Invalid Password\n" + (4 - customer.getAttempts()) + " Attempts remaining");
                            String err = customer.getAttempts() + " " + exception.getMessage();
                            logger.warn(err);
                            super.setDefaultFailureUrl("/web/?error=" + err);
                        } else {
                            service.updateStatus(customer);
                            logger.warn(resourceBundle.getString("account.suspend"));
                            exception = new LockedException(resourceBundle.getString("account.suspend"));
                            setDefaultFailureUrl("/web/?error=" + exception.getMessage());
                        }
                    } else {
                        logger.warn(resourceBundle.getString("contact.admin"));
                        exception = new LockedException(resourceBundle.getString("contact.admin"));
                        super.setDefaultFailureUrl("/web/?error=" + exception.getMessage());
                    }
                } else {
                    super.setDefaultFailureUrl("/web/?errors=User not exists");
                }
            } else {
                // Handle case where username parameter is null or empty
                logger.warn(resourceBundle.getString("invalid.username"));
                exception = new LockedException(resourceBundle.getString("invalid.username"));
                super.setDefaultFailureUrl("/web/?error=" + exception.getMessage());
            }
        } catch (
                UsernameNotFoundException e) {
            logger.info(e.toString());
            logger.warn(resourceBundle.getString("account.suspended"));
            exception = new LockedException(resourceBundle.getString("user.not.found"));
            super.setDefaultFailureUrl("/web/?error=" + exception.getMessage());

        }
        super.

                onAuthenticationFailure(request, response, exception);
    }


}