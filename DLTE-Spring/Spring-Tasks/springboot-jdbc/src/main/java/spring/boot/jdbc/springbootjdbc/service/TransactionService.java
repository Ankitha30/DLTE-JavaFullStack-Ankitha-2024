package spring.boot.jdbc.springbootjdbc.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;
import java.util.Optional;
import spring.boot.jdbc.springbootjdbc.exception.TransactionException;
import spring.boot.jdbc.springbootjdbc.model.Transaction;

/*
Build the Spring Boot Data Jdbc and perform the CRUD operation on transaction table of following:

Entity: Transaction Id, transaction date, transaction by(sender), transaction to(receiver), transaction amount, transaction for(remarks)

Operations: RestController with jdbcTemplate

new transaction
findBySender
findByReceiver
findByAmount
 */
@Service
public class TransactionService {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public Transaction apiSave(Transaction transaction){
//        int ack=jdbcTemplate.update("INSERT INTO mybank_transactions (transactionid, transactionby, transactiondate, transactionto, transactionamount, remarks) VALUES (?,?,?,?,?,?)",
//                transaction.getTransactionId(),
//                transaction.getTransactionBy(),
//                transaction.getTransactionDate(),
//                transaction.getTransactionTo(),
//                transaction.getTransactionAmount(),
//                transaction.getRemarks());
//
//        if(ack != 0)
//            return transaction;
//        else
//            throw new TransactionException("Insertion failed");

        jdbcTemplate.update("INSERT INTO mybank_transactions (transactionid, transactionby, transactiondate, transactionto, transactionamount, remarks) VALUES (?,?,?,?,?,?)",
                transaction.getTransactionId(),
                transaction.getTransactionBy(),
                transaction.getTransactionDate(),
                transaction.getTransactionTo(),
                transaction.getTransactionAmount(),
                transaction.getRemarks());
        return transaction;
    }


    public List<Transaction> apiFindByReceiver(String transactionTo){
        return jdbcTemplate.query("select * from mybank_transactions where transactionTo=?",
                new Object[]{transactionTo},
                new TransactionMapper());

    }
    public List<Transaction> apiFindBySender(String transactionBy){
        return jdbcTemplate.query("select * from mybank_transactions where transactionBy=?",
                new Object[]{transactionBy},
                new TransactionMapper());
    }

    public List<Transaction> apiFindByAmount(Double Amount){
        return jdbcTemplate.query("select * from mybank_transactions where transactionAmount=?",
                new Object[]{Amount},
                new TransactionMapper());
    }

    public class TransactionMapper implements RowMapper<Transaction>{

        @Override
        public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
            Transaction transaction=new Transaction();
            transaction.setTransactionId(rs.getInt(1));
            transaction.setTransactionDate(rs.getDate(2));
            transaction.setTransactionBy(rs.getString(3));
            transaction.setTransactionTo(rs.getString(4));
            transaction.setTransactionAmount(rs.getDouble(5));
            transaction.setRemarks(rs.getString(6));

            return transaction;
        }
    }

}
