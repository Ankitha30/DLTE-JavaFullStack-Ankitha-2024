package debit.card.application.configs;


import debits.cards.dao.entities.Customer;
import debits.cards.dao.exceptions.DebitCardException;
import debits.cards.dao.remotes.DebitCardRepository;
import debits.cards.dao.services.CardSecurityService;
import links.debitcard.DebitCard;
import links.debitcard.ServiceStatus;
import links.debitcard.ViewDebitCardRequest;
import links.debitcard.ViewDebitCardResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;

import java.util.*;


@ComponentScan("debits.cards.dao.services")
@Endpoint
public class DebitCardPhase {
    private final String url = "http://debitcard.links";
    Logger logger = LoggerFactory.getLogger(DebitCardPhase.class);
    ResourceBundle resourceBundle = ResourceBundle.getBundle("cards");
    @Autowired
    private DebitCardRepository debitCardServices;

    @Autowired
    CardSecurityService service;

    @PayloadRoot(namespace = url, localPart = "viewDebitCardRequest")
    @ResponsePayload
    public ViewDebitCardResponse viewDebitCard(@RequestPayload ViewDebitCardRequest viewDebitCardRequest)  {
        ViewDebitCardResponse viewDebitCardResponse = new ViewDebitCardResponse();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        DebitCard debitCard =  new DebitCard();
        ServiceStatus serviceStatus = new ServiceStatus();
        try {
            Customer customer = service.findByUserName(username);

            debitCard.setCustomerId(customer.getCustomerId());
            List<links.debitcard.DebitCard> debitCardList = new ArrayList<>();
            List<debits.cards.dao.entities.DebitCard> debitCardsDao = debitCardServices.listAllCards(debitCard.getCustomerId());
            debitCardsDao.forEach(each -> {
                links.debitcard.DebitCard currentDebitCard = new links.debitcard.DebitCard();
                Date date = each.getDebitCardExpiry();
                XMLGregorianCalendar xmlCalendar = null;

                try {
                    xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(date.toString());
                } catch (DatatypeConfigurationException exception) {
                    logger.error(resourceBundle.getString("date.error"));
                    serviceStatus.setMessage(resourceBundle.getString(("soap.db.error")));
                }

                currentDebitCard.setDebitCardExpiry(xmlCalendar);
                BeanUtils.copyProperties(each, currentDebitCard);
                debitCardList.add(currentDebitCard);

            });

            serviceStatus.setStatus(HttpServletResponse.SC_OK); //200
            serviceStatus.setMessage(resourceBundle.getString("card.collected"));
            viewDebitCardResponse.getDebitCard().addAll(debitCardList);
        }  catch (DebitCardException exception) {
            serviceStatus.setStatus(HttpServletResponse.SC_OK); //204

            logger.error(resourceBundle.getString("error.one")+resourceBundle.getString("cards.data.null"));
            serviceStatus.setMessage(resourceBundle.getString("error.one")+resourceBundle.getString("cards.data.null"));
        }

        viewDebitCardResponse.setServiceStatus(serviceStatus);
        return viewDebitCardResponse;
    }


}
