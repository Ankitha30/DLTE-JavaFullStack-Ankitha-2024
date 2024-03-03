package transaction.history.remote;

import transaction.history.entity.CreditCard;

import java.util.List;

public interface CreditCardRepository {
    void save(CreditCard creditCard);
    List<CreditCard> findAll();
    CreditCard findById(Long creditCardNumber);
//    List<CreditCard> findAllByLimit(Integer limit);
    void update(CreditCard creditCard);
    void delete(CreditCard creditCard);
}
