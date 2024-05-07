package debits.cards.dao.remotes;


import debits.cards.dao.entities.DebitCard;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DebitCardRepository {
    List<DebitCard> listAllCards(int customerId) ;
    String updateDebitCardStatus(DebitCard debitCard);

}
