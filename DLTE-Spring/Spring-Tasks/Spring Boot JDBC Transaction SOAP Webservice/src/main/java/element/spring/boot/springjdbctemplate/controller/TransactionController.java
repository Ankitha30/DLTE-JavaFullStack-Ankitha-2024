package element.spring.boot.springjdbctemplate.controller;


import element.spring.boot.springjdbctemplate.model.Transaction;
import element.spring.boot.springjdbctemplate.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;
    Logger logger= LoggerFactory.getLogger(TransactionController.class);


    @PostMapping("/new")
    public Transaction saved(@RequestBody Transaction Transaction){
        Transaction transaction=new Transaction();
        try{
            transaction = transactionService.apiSave(Transaction);
        }
        catch (element.spring.boot.springjdbctemplate.exception.TransactionException TransactionException){
            logger.warn(TransactionException.toString());
        }
        return transaction;
    }

    @GetMapping("/receiver/{transactionTo}")
    public List<Transaction> gettingReceiver(@PathVariable("transactionTo") String transactionTo){
        return transactionService.apiFindByReceiver(transactionTo);
    }
    @GetMapping("/sender/{transactionBy}")
    public List<Transaction> gettingSender(@PathVariable("transactionBy") String transactionBy){
        return transactionService.apiFindBySender(transactionBy);
    }

    @GetMapping("/amount/{amount}")
    public List<Transaction> gettingAmount(@PathVariable("amount") Double amount){
        return transactionService.apiFindByAmount(amount);
    }


}
