package debit.card.application.security;

import debits.cards.dao.entities.CardSecurity;
import debits.cards.dao.services.CardSecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@Component
public class CardFailure extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    CardSecurityService cardSecurityService;

    Logger logger= LoggerFactory.getLogger(CardFailure.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String username = request.getParameter("username");
        CardSecurity cardSecurity = cardSecurityService.findByUserName(username);
        if(cardSecurity!=null){
            if (!cardSecurity.getCustomerStatus().equals("block")) {
                if(cardSecurity.getAttempts()< cardSecurity.getMaxAttempt()){
                    cardSecurity.setAttempts(cardSecurity.getAttempts()+1);
                    cardSecurityService.updateAttempts(cardSecurity);
                    logger.warn("Invalid credentials and attempts taken");
                    exception=new LockedException("Attempts are taken");
                }
                else{
                    cardSecurityService.updateStatus(cardSecurity);
                    exception=new LockedException("Max Attempts reached account is suspended");
                }
            }
            else{
                logger.warn("Account suspended contact admin to redeem");
            }
        }
        super.setDefaultFailureUrl("/login?error=true");
        super.onAuthenticationFailure(request, response, exception);
    }

}