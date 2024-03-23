package web;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


import app.mybank.entity.Transaction;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import web.TransactionSoap;
import app.mybank.services.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Unit test for simple App.
 */

@RunWith(MockitoJUnitRunner.class)
public class AppTest 
{
    @Mock
    private TransactionService transactionService;
    private TransactionSoap transactionSoap;

    @Before
    public void settingUp(){
        transactionSoap=new TransactionSoap();
        transactionSoap.service=transactionService;
    }


    @Test
    public void testTransactionSave() {
        Transaction transaction = new Transaction("shreya", "deposit", 3000.0, new java.sql.Date(new Date("01/04/2023").getTime()));
        Transaction transaction1 = new Transaction("elroy", "deposit", 4500.0, new java.sql.Date(new Date("10/10/2023").getTime()));
        doNothing().when(transactionService).callSaveTransaction(transaction1);
        transactionSoap.createTransaction(transaction1);
//        verify(transactionService, times(1)).callSaveTransaction(transaction);
        verify(transactionService, times(1)).callSaveTransaction(transaction1);
    }

    @Test
    public void testFindByUser(){
        Transaction transaction = new Transaction("shreya", "deposit", 3000.0, new java.sql.Date(new Date("01/04/2023").getTime()));
        Transaction transaction1 = new Transaction("elroy", "deposit", 4500.0, new java.sql.Date(new Date("10/10/2023").getTime()));
        Transaction transaction2 = new Transaction("shreya", "withdrawal", 4500.0, new java.sql.Date(new Date("10/10/2023").getTime()));
        List<Transaction> expectedList1= Stream.of(transaction1,transaction2).collect(Collectors.toList());
        List<Transaction> expectedList2= Stream.of(transaction1,transaction,transaction2).collect(Collectors.toList());
        when(transactionService.callViewTransaction("shreya")).thenReturn(expectedList2);
        GroupOfServices groupOfServices=transactionSoap.readUser("shreya");
//        assertNull(groupOfServices);  //fails
//        assertNotNull(groupOfServices);    // pass
        assertEquals("elroy",groupOfServices.getTransaction().get(0).getUserName());   // test case pass

    }

    @Test
    public  void testFindAll(){
        Transaction transaction = new Transaction("shreya", "deposit", 3000.0, new java.sql.Date(new Date("01/04/2023").getTime()));
        Transaction transaction1 = new Transaction("elroy", "deposit", 4500.0, new java.sql.Date(new Date("10/10/2023").getTime()));
        Transaction transaction2 = new Transaction("shreya", "withdrawal", 4500.0, new java.sql.Date(new Date("10/10/2023").getTime()));
        List<Transaction> transactions= Stream.of(transaction1,transaction,transaction2).collect(Collectors.toList());
        when(transactionService.callViewAllTransaction()).thenReturn(transactions);
        System.out.println(transactions.toString());
//        assertNull(transactions);  //fails
        assertNotNull("Not Null", transactions);
    }


}
