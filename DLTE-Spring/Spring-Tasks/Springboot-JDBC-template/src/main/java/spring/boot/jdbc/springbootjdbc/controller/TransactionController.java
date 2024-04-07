package spring.boot.jdbc.springbootjdbc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import spring.boot.jdbc.springbootjdbc.exception.TransactionException;
import spring.boot.jdbc.springbootjdbc.model.Transaction;
import spring.boot.jdbc.springbootjdbc.service.TransactionService;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;
    Logger logger= LoggerFactory.getLogger(TransactionController.class);

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/new")
    public Transaction saved(@RequestBody Transaction Transaction){
        Transaction transaction=new Transaction();
        try{
            transaction = transactionService.apiSave(Transaction);
        }
        catch (TransactionException TransactionException){
            logger.warn(TransactionException.toString());
        }
        return transaction;
    }

    @PreAuthorize("hasAuthority('customer')")
    @GetMapping("/receiver/{transactionTo}")
    public List<Transaction> gettingReceiver(@PathVariable("transactionTo") String transactionTo){
        return transactionService.apiFindByReceiver(transactionTo);
    }
    @PreAuthorize("hasAuthority('customer')")
    @GetMapping("/sender/{transactionBy}")
    public List<Transaction> gettingSender(@PathVariable("transactionBy") String transactionBy){
        return transactionService.apiFindBySender(transactionBy);
    }

    @PreAuthorize("hasAuthority('customer')")
    @GetMapping("/amount/{amount}")
    public List<Transaction> gettingAmount(@PathVariable("amount") Double amount){
        return transactionService.apiFindByAmount(amount);
    }


    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/delete/dates/{start}/{end}")
    public String removeTransactionBetweenDates(@PathVariable ("start") Date start, @PathVariable("end") Date end){
        try {
            return transactionService.apiRemoveTransactionByDates(start,end);
        }catch (Exception exception){
            logger.error("Error occurred : " + exception.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }


}
