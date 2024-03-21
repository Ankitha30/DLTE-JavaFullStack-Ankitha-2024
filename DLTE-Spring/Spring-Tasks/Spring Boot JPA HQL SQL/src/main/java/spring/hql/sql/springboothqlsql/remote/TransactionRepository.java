package spring.hql.sql.springboothqlsql.remote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring.hql.sql.springboothqlsql.model.Transaction;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

//HQL:  here tabale name is EntityName and column name is taken from that defined in entity
@Query("from Transaction where transactionAmount between :amount1 and :amount2")
List<Transaction> lookUpByAmount(Double amount1, Double amount2);

//Native Query:  Tablename is table name and column name is same as defined in the table
    @Query(value = "select * from transaction_details where transaction_type = ?1 and user_name= ?2",nativeQuery = true)
    List<Transaction> lookUpByUserType(String user,String type);






}
