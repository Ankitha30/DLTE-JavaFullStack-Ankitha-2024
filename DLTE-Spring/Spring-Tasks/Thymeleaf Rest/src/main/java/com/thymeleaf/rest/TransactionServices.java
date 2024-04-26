package com.thymeleaf.rest;

import com.thymeleaf.rest.exception.TransactionException;
import com.thymeleaf.rest.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


@Service
public class TransactionServices {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Transaction apiSave(Transaction transaction){
        int ack=jdbcTemplate.update("INSERT INTO mybank_transactions (transactionid, transactionby, transactiondate, transactionto, transactionamount, remarks) VALUES (?,?,?,?,?,?)",
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
}

