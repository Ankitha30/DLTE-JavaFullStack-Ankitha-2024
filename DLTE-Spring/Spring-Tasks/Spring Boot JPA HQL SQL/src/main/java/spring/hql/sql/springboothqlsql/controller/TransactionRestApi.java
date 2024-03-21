package spring.hql.sql.springboothqlsql.controller;
/*
Considering Transaction entity of console project and performs following operation using JpaRepository with HQL , SQL and XML with RestController

New Transaction of POST mapping as XML request
findAllByUserAndType   GetMapping using Native SQL query
findAllByRangeOfTransactionAmount by using HQL
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.hql.sql.springboothqlsql.model.Transaction;
import spring.hql.sql.springboothqlsql.services.TransactionService;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionRestApi {
   @Autowired
    TransactionService transactionService;

   /*
   http://localhost:8082/transaction/

   XML part:

   <Transaction>
    <userName>Suma</userName>
    <transactionType>withdrawal</transactionType>
    <transactionAmount>100.00</transactionAmount>
    <transactionDate>2024-03-02</transactionDate>
</Transaction>


    */

    @PostMapping(value="/",consumes = "application/xml")
    public Transaction apiUpdate(@RequestBody Transaction transaction){
        return transactionService.callSave(transaction);
    }

    //  http://localhost:8082/transaction/findByUserType/deposit/Anu
    @GetMapping("/findByUserType/{name}/{type}")
    public List<Transaction> apiType(@PathVariable("name") String name,@PathVariable("type") String type){
        return transactionService.callFindByUserAndType(name,type);
    }



    //http://localhost:8082/transaction/amount/50.0/132.0
    @GetMapping("/amount/{amount1}/{amount2}")
    public List<Transaction> apiRange(@PathVariable("amount1") Double amount1,@PathVariable("amount2") Double amount2){
        return transactionService.callFindAmountRange(amount1,amount2);
    }




}
