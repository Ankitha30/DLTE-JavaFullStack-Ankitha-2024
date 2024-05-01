package debits.cards;

import debits.cards.dao.entities.DebitCard;
import debits.cards.dao.services.DebitCardService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.SQLSyntaxErrorException;
import java.util.Date;

@SpringBootApplication
public class DlteMyBankProjectDaoApplication {

    public static void main(String[] args) throws SQLSyntaxErrorException {
        ConfigurableApplicationContext context=  SpringApplication.run(DlteMyBankProjectDaoApplication.class, args);
        DebitCard debitCard = new DebitCard(3692468135796675L,78782390431098L,123675,322,1234,new Date(2028,11,23),"blocked", 45000,100000);

        DebitCardService debitCardServices=context.getBean(DebitCardService.class);
        System.out.println(debitCardServices.updateDebitCardStatus(debitCard));
//        System.out.println(debitCardServices.listAllCards());

    }

}
