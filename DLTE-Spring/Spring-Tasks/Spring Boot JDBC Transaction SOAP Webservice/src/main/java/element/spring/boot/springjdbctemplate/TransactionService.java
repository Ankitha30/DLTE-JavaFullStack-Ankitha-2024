package element.spring.boot.springjdbctemplate.service;


import element.spring.boot.springjdbctemplate.exception.TransactionException;
import element.spring.boot.springjdbctemplate.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;


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
           throw new TransactionException("Insertion failed");


    }


    public List<Transaction> apiFindByReceiver(String transactionTo){
        return jdbcTemplate.query("select * from mybank_transactions where transactionTo=?",
                new Object[]{transactionTo},
                new BeanPropertyRowMapper<>(Transaction.class));

    }
    public List<Transaction> apiFindBySender(String transactionBy){
        return jdbcTemplate.query("select * from mybank_transactions where transactionBy=?",
                new Object[]{transactionBy},
                new BeanPropertyRowMapper<>(Transaction.class));
    }

    public List<Transaction> apiFindByAmount(Double Amount){
        return jdbcTemplate.query("select * from mybank_transactions where transactionAmount=?",
                new Object[]{Amount},
                new BeanPropertyRowMapper<>(Transaction.class));

    }
    public Transaction apiUpdateTransaction(Transaction transaction){
        int acknowledge=jdbcTemplate.update("update mybank_transactions set remarks=? where transactionid=?",
                new Object[]{transaction.getRemarks(),transaction.getTransactionId()}
        );
        if(acknowledge!=0) return transaction;
        else  return null;
    }

    public String apiDeleteTransaction(XMLGregorianCalendar startDate, XMLGregorianCalendar endDate){
        int acknowledge= jdbcTemplate.update("delete from mybank_transactions where transactiondate between ? and ?",
                new Object[]{startDate,endDate}
        );
        if(acknowledge!=0) return "Transaction deleted";
        else return "Failed to delete transaction";
    }

//    public class TransactionMapper implements RowMapper<Transaction>{
//
//        @Override
//        public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
//            Transaction transaction=new Transaction();
//            transaction.setTransactionId(rs.getInt(1));
//            transaction.setTransactionDate(rs.getDate(2));
//            transaction.setTransactionBy(rs.getString(3));
//            transaction.setTransactionTo(rs.getString(4));
//            transaction.setTransactionAmount(rs.getDouble(5));
//            transaction.setRemarks(rs.getString(6));
//
//            return transaction;
//        }
//    }

}
