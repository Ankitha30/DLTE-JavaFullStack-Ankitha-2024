package debits.cards.dao.remotes;

import debits.cards.dao.entities.Account;
import debits.cards.dao.entities.DebitCard;
import org.springframework.stereotype.Repository;

import java.sql.SQLSyntaxErrorException;
import java.util.List;

@Repository
public interface DebitCardRepository {
    List<DebitCard> listAllCards(String username) throws SQLSyntaxErrorException;
    String updateDebitCardStatus(DebitCard debitCard);
    List<Account> accountList(String username) throws SQLSyntaxErrorException;
}
