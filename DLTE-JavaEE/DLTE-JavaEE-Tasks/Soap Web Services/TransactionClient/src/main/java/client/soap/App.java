package client.soap;
import web.Account;
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


          Account account=new Account();
        account.setUserName("Jayashri");
        account.setPassword("3456");
        account.setEmail("jaya@gmail.com");
        account.setPhoneNumber(9902100117L);
        transactionSoap.createAccount(account);
        System.out.println("Account created");

//--------------------------------------------------------------------


//        Boolean transactions=transactionSoap.findByUsername("shreya","2345");
//
//        if (transactions != null && transactions) {
//            System.out.println("Login success");
//        } else {
//            System.out.println("Login failed");
//        }
//
//---------------------------------------------------------------------


//        List<Transaction> transactions=transactionSoap.readAll("elroy","deposit").getTransaction();
//        System.out.println("User Name     Type      Amount      Date");
//        for (Transaction transaction : transactions) {
//
//            System.out.print(transaction.getUserName()+"       ");
//            System.out.print(transaction.getTransactionType()+"      ");
//            System.out.print( + transaction.getTransactionAmount()+ "     ");
//            System.out.println(transaction.getTransactionDate());
//
//      }

//----------------------------------------------------------------------------

//        List<Transaction> transactions=soap.readAllDateByUser("elroy","02/01/2023","04/03/2024").getTransactions();
//        System.out.println("User Name     Type      Amount      Date");
//        for (Transaction transaction : transactions) {
//            System.out.print(transaction.getUserName()+"       ");
//            System.out.print(transaction.getTransactionType()+"      ");
//            System.out.print( + transaction.getTransactionAmount()+ "     ");
//            System.out.println(transaction.getTransactionDate());
//
//      }

//--------------------------------------------------------


//        List<Transaction> transactions=soap.readAllByUser("elroy").getTransactions();
//        System.out.println("User Name     Type      Amount      Date");
//        for (Transaction transaction : transactions) {
//            System.out.print(transaction.getUserName()+"       ");
//            System.out.print(transaction.getTransactionType()+"      ");
//            System.out.print( + transaction.getTransactionAmount()+ "     ");
//            System.out.println(transaction.getTransactionDate());
//
//      }


    }
}
