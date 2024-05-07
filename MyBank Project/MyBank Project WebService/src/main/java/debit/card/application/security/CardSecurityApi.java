package debit.card.application.security;

import debits.cards.dao.entities.Customer;
import debits.cards.dao.services.CardSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank")
public class CardSecurityApi {

        @Autowired
        CardSecurityService cardSecurityServices;

        @Autowired
        PasswordEncoder passwordEncoder;

        @PostMapping("/register")
        public Customer save(@RequestBody Customer cardSecurity){
            cardSecurity.setPassword(passwordEncoder.encode(cardSecurity.getPassword()));
            return cardSecurityServices.signingUp(cardSecurity);
        }
}
