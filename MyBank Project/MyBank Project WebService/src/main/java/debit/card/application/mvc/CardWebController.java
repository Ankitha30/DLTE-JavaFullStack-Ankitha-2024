package debit.card.application.mvc;


import debit.card.application.rest.UpdateStatusController;
import debits.cards.dao.entities.CardSecurity;
import debits.cards.dao.services.CardSecurityService;
import debits.cards.dao.services.DebitCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web")
public class CardWebController {

    @Autowired
    DebitCardService debitCardService;

    @Autowired
    CardSecurityService service;
    Logger logger = LoggerFactory.getLogger(CardWebController.class);

    @RequestMapping(value="/dash", method = RequestMethod.GET)
    public String homePage(){
        return "dashboard";
    }

    @GetMapping("/")
    public String landing(){
        return "index";
    }

    @RequestMapping(value="/view", method = RequestMethod.GET)
    public String viewPage(){
        return "view";
    }


    @GetMapping("/debit")
    public String viewing(){
        return "viewCard";
    }


}
