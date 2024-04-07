

package debit.card.application.configs;

import debits.cards.dao.entities.DebitCard;
import debits.cards.dao.exceptions.DebitCardException;
import debits.cards.dao.remotes.DebitCardRepository;
import debits.cards.dao.services.DebitCardService;
import links.debitcard.ServiceStatus;
import links.debitcard.ViewDebitCardRequest;
import links.debitcard.ViewDebitCardResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

@ComponentScan("debits.cards.dao.services")
@Endpoint
public class DebitCardPhase {
    Logger logger = LoggerFactory.getLogger(DebitCardPhase.class);
    ResourceBundle resourceBundle= ResourceBundle.getBundle("application");
    private final String url = "http://debitcard.links";
    @Autowired
    private DebitCardRepository debitCardServices;

    @PayloadRoot(namespace = url,localPart = "viewDebitCardRequest")
    @ResponsePayload
    public ViewDebitCardResponse viewDebitCardResponse(@RequestPayload ViewDebitCardRequest viewDebitCardRequest) throws SQLSyntaxErrorException {
        ViewDebitCardResponse viewDebitCardResponse = new ViewDebitCardResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        try {
            List<links.debitcard.DebitCard> debitCardList = new ArrayList<>();

            List<debits.cards.dao.entities.DebitCard> debitCardsDao = debitCardServices.listAllCards();
//            System.out.println(debitCardsDao);

            debitCardsDao.forEach(each -> {
                links.debitcard.DebitCard currentDebitCard = new links.debitcard.DebitCard();
                BeanUtils.copyProperties(each, currentDebitCard);
                debitCardList.add(currentDebitCard);

            });
//        Iterator<debits.cards.dao.entities.DebitCard> iterator = debitCardsDao.iterator();
//
//        while(iterator.hasNext()){
//            links.debitcard.DebitCard currentDebitCard =new links.debitcard.DebitCard();
//            BeanUtils.copyProperties(iterator.next(),currentDebitCard);
//            System.out.println("Conversion "+currentDebitCard.getInternationalLimit()+" "+currentDebitCard.getDomesticLimit());
//            debitCardList.add(currentDebitCard);
//        }
            serviceStatus.setStatus(HttpServletResponse.SC_OK); //200
            serviceStatus.setMessage(resourceBundle.getString("card.collected"));
//            System.out.println(serviceStatus.toString());
//            System.out.println(serviceStatus.getMessage());
//            System.out.println(serviceStatus.getStatus());
            viewDebitCardResponse.getDebitCard().addAll(debitCardList);
        }
        catch (SQLSyntaxErrorException e) {
            serviceStatus.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); //500
            logger.error(resourceBundle.getString("soap.sql.error") + e + HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
            serviceStatus.setMessage(resourceBundle.getString("soap.db.error"));
//            return  viewDebitCardResponse;
        }
        catch (DebitCardException e) {
            serviceStatus.setStatus(HttpServletResponse.SC_NO_CONTENT); //204
            logger.error(resourceBundle.getString("cards.data.null")+ e + HttpServletResponse.SC_NO_CONTENT);
            serviceStatus.setMessage(resourceBundle.getString("cards.data.null"));
//            return  viewDebitCardResponse;
        }
        viewDebitCardResponse.setServiceStatus(serviceStatus);
        return  viewDebitCardResponse;
    }
}
