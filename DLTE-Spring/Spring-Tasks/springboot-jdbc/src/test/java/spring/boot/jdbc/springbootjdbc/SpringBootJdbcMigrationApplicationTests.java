package spring.boot.jdbc.springbootjdbc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anySet;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import spring.boot.jdbc.springbootjdbc.model.Transaction;
import spring.boot.jdbc.springbootjdbc.service.TransactionService;

import java.sql.SQLSyntaxErrorException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class SpringBootJdbcMigrationApplicationTests {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private TransactionService transactionService;

    @Test
    void testLoad() {
        Transaction transaction1= new Transaction(2367,new Date(2022,03,03),"Anvitha","Jaya",2000.0,"Family");
        Transaction transaction2= new Transaction(2142,new Date(2022,02,03),"Anvitha","Latha",8000.0,"Family");
        lenient().when(jdbcTemplate.update(anyString(),any(Object[].class))).thenReturn(1);
        Transaction actual = transactionService.apiSave(transaction1);
        assertEquals(transaction1.getTransactionBy(),actual.getTransactionBy());
    }
    @Test
    void testFindBySender(){
        Transaction transaction1= new Transaction(2367,new Date(2022,03,03),"Anvitha","Jaya",2000.0,"Family");
        Transaction transaction2= new Transaction(2142,new Date(2022,02,03),"Anvitha","Latha",8000.0,"Family");
        Transaction transaction3= new Transaction(2148,new Date(2022,02,03),"Anu","Latha",8000.0,"Family");
        List<Transaction> expectedlist = Stream.of(transaction1,transaction3).collect(Collectors.toList());
        when(jdbcTemplate.query(any(),any(Object[].class),any(TransactionService.TransactionMapper.class))).thenReturn(expectedlist);
        List<Transaction> actualList = transactionService.apiFindBySender("Anu");
//        System.out.println(expectedlist.size());
//        System.out.println(expectedlist.get(0).getTransactionBy());
//        System.out.println(expectedlist.get(1).getTransactionBy());
//        System.out.println(actualList.size());
//        System.out.println(actualList.get(0).getTransactionBy());
//        System.out.println(actualList.get(1).getTransactionBy());
        assertFalse(expectedlist.size()== actualList.size());
        assertTrue(expectedlist.size()== actualList.size());
        assertEquals(expectedlist.size(), actualList.size());
        assertNotEquals(expectedlist.size(), actualList.size());
        assertEquals(expectedlist.get(1).getTransactionBy(), actualList.get(0).getTransactionBy());
        assertNotSame(expectedlist.get(1).getTransactionBy(), actualList.get(0).getTransactionBy());
        assertSame(expectedlist.get(0).getTransactionBy(), actualList.get(0).getTransactionBy());

    }

    @Test
    void testFindByReceiver(){
        Transaction transaction1= new Transaction(2367,new Date(2022,03,03),"Anvitha","Jaya",2000.0,"Family");
        Transaction transaction2= new Transaction(2142,new Date(2022,02,03),"Anvitha","Latha",8000.0,"Family");
        Transaction transaction3= new Transaction(2148,new Date(2022,02,03),"Anu","Latha",8000.0,"Family");
        List<Transaction> expectedlist = Stream.of(transaction2,transaction3).collect(Collectors.toList());
        when(jdbcTemplate.query(anyString(),any(Object[].class),any(TransactionService.TransactionMapper.class))).thenReturn(expectedlist);
        List<Transaction> actualList = transactionService.apiFindByReceiver("Anu");
//        System.out.println(expectedlist.size());
//        System.out.println(expectedlist.get(0).getTransactionTo());
//        System.out.println(expectedlist.get(1).getTransactionTo());
//        System.out.println(actualList.get(0).getTransactionTo());
//        System.out.println(actualList.get(1).getTransactionTo());
        assertFalse(expectedlist.size()== actualList.size());
        assertTrue(expectedlist.size()== actualList.size());
        assertEquals(expectedlist.size(), actualList.size());
        assertEquals(expectedlist.get(1).getTransactionTo(), actualList.get(0).getTransactionTo());
        assertNotSame(expectedlist.get(1).getTransactionTo(), actualList.get(0).getTransactionTo());

    }


    @Test
    void testFindByAmount(){
        Transaction transaction1= new Transaction(2367,new Date(2022,03,03),"Anvitha","Jaya",2000.0,"Family");
        Transaction transaction2= new Transaction(2142,new Date(2022,02,03),"Anvitha","Latha",8000.0,"Family");
        Transaction transaction3= new Transaction(2148,new Date(2022,02,03),"Anu","Latha",8000.0,"Family");
        List<Transaction> expectedList = Stream.of(transaction2,transaction3).collect(Collectors.toList());
        when(jdbcTemplate.query(anyString(),any(Object[].class),any(TransactionService.TransactionMapper.class))).thenReturn(expectedList);
        List<Transaction> actualList = transactionService.apiFindByAmount(1000.0);
//        System.out.println(expectedList.size());
//        System.out.println(expectedList.get(0).getTransactionAmount());
//        System.out.println(expectedList.get(1).getTransactionAmount());
//        System.out.println(actualList.get(0).getTransactionAmount());
//        System.out.println(actualList.get(1).getTransactionAmount());
//        assertFalse(expectedList.size()== actualList.size());
        assertTrue(expectedList.size()== actualList.size());
        assertEquals(expectedList.size(), actualList.size());
        assertEquals(expectedList.get(1).getTransactionAmount(), actualList.get(0).getTransactionAmount());
        assertNotSame(expectedList.get(1).getTransactionAmount(), actualList.get(0).getTransactionAmount());
        assertEquals(expectedList.get(1).getTransactionAmount(), actualList.get(0).getTransactionAmount());

    }

}
