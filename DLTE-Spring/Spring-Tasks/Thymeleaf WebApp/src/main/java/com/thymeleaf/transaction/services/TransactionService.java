package com.thymeleaf.transaction.services;

import com.thymeleaf.transaction.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.thymeleaf.transaction.exception.TransactionException;

import java.util.Date;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

     public Transaction apiSave(Transaction transaction){
         int ack = jdbcTemplate.update("INSERT INTO mybank_transactions (transactionid, transactionby, transactiondate, transactionto, transactionamount, remarks) VALUES (?,?,?,?,?,?)",
                 transaction.getTransactionId(),
                 transaction.getTransactionBy(),
                 transaction.getTransactionDate(),
                 transaction.getTransactionTo(),
                 transaction.getTransactionAmount(),
                 transaction.getRemarks());

         if(ack != 0)
             return transaction;
         else
             throw new TransactionException("Failed");

     }
     public List<Transaction> apiFindBySender(String sender){
         List<Transaction> transactionList = jdbcTemplate.query("select * from mybank_transactions where transactionby = ?",new Object[]{sender},new BeanPropertyRowMapper<>(Transaction.class));
         transactionList.forEach(System.out::println);
         return transactionList;
     }

     public List<Transaction> apiFindByReceiver(String receiver){
         List<Transaction> transactionList = jdbcTemplate.query("select * from mybank_transactions where transactionto = ?",new Object[]{receiver},new BeanPropertyRowMapper<>(Transaction.class));
         return transactionList;
     }
     public List<Transaction> apiFindByAmount(double amount){
         List<Transaction> transactionList = jdbcTemplate.query("select * from mybank_transactions where transactionamount = ?",new Object[]{amount},new BeanPropertyRowMapper<>(Transaction.class));
         return transactionList;
     }
    public String deleteTransaction(Date startDate, Date endDate){
        int ack= jdbcTemplate.update("delete from mybank_transactions where transactiondate between ? and ?",
                new Object[]{startDate,endDate}
        );
        if(ack!=0)
            return "Transaction deleted successfully";
        else
            return "Failed to delete";
    }


}
