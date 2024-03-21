package spring.hql.sql.springboothqlsql.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.hql.sql.springboothqlsql.model.Transaction;
import spring.hql.sql.springboothqlsql.remote.TransactionRepository;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    public Transaction callSave(Transaction transaction){
        return transactionRepository.save(transaction);
    }

    public List<Transaction> callFindByUserAndType(String user, String type) {
        return transactionRepository.lookUpByUserType(user, type);
    }
    public List<Transaction> callFindAmountRange(Double amount1,Double amount2) {
        return transactionRepository.lookUpByAmount(amount1,amount2);
    }
}
