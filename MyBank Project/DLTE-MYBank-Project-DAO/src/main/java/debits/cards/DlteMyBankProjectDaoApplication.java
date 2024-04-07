package debits.cards;

import debits.cards.dao.services.DebitCardService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.SQLSyntaxErrorException;

@SpringBootApplication
public class DlteMyBankProjectDaoApplication {

    public static void main(String[] args) throws SQLSyntaxErrorException {
        ConfigurableApplicationContext context=  SpringApplication.run(DlteMyBankProjectDaoApplication.class, args);

        DebitCardService debitCardServices=context.getBean(DebitCardService.class);
        System.out.println(debitCardServices.listAllCards());
    }

}
