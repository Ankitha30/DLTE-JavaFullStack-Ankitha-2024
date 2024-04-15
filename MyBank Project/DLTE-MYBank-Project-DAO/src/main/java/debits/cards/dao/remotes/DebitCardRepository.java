package debits.cards.dao.remotes;

import debits.cards.dao.entities.DebitCard;
import org.springframework.stereotype.Repository;

import java.sql.SQLSyntaxErrorException;
import java.util.List;

@Repository
public interface DebitCardRepository {
    List<DebitCard> listAllCards() throws SQLSyntaxErrorException;
    String updateDebitCardStatus(DebitCard debitCard);
}
