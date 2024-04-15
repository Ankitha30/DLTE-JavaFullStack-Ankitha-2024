package debit.card.application.security;


import debits.cards.dao.entities.CardSecurity;
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
import java.util.ResourceBundle;

@Component
public class CardSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
//    @Autowired
//    CardSecurityService cardSecurityServices;

    @Autowired
    debits.cards.dao.services.CardSecurityService cardSecurityServices;

    Logger logger= LoggerFactory.getLogger(CardSuccessHandler.class);
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CardSecurity cardSecurity= (CardSecurity) authentication.getPrincipal();
        if(!cardSecurity.getCustomerStatus().equals(resourceBundle.getString("block"))){
            if (cardSecurity.getAttempts() > 1) {
                cardSecurity.setAttempts(1);
                cardSecurityServices.updateAttempts(cardSecurity);
            }
            super.setDefaultTargetUrl("/update/status");
        }
        else if(cardSecurity.getCustomerStatus().equals(resourceBundle.getString("block")) && cardSecurity.getAttempts()==3){
            logger.warn(resourceBundle.getString("attempts.exceed"));
            super.setDefaultTargetUrl("/login");
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
