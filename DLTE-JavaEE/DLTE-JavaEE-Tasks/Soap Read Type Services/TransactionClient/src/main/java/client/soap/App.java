package client.soap;

import org.omg.IOP.TransactionService;
import web.Transaction;
import web.TransactionSoap;
import web.TransactionSoapService;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        TransactionSoapService transactionSoapService= new TransactionSoapService();
        TransactionSoap transactionSoap = transactionSoapService.getTransactionSoapPort();
        List<Transaction> transactions=transactionSoap.readAll("shreya","deposit").getTransaction();
//        System.out.println(transactions.toString());
        for(Transaction each:transactions){
            System.out.println(each.getUserName()+" "+each.getTransactionAmount());
        }

    }
}
