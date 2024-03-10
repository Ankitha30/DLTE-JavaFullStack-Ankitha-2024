package org.example;

import static org.example.MyBank.loans;
import static org.example.Loan.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    MyBankMethods myBank;
    static List<Loan> list=new ArrayList<>();
    @Before
    public void setUp(){
        myBank =new  MyBankMethods();
    }
    @BeforeClass
    public static void initialize() {
        list.add(new Loan(1234, 30000.0, new Date("3/2/2021"), "open", "Anu", 7435511150L));
        list.add(new Loan(2345, 3400.0, new Date("3/4/2022"), "closed", "Ankitha", 9963772901L));
    }


    @Test
    public void testData(){
    assertEquals("Anu",list.get(0).getBorrowerName() );
    }

    @Test
    public void testIsEmptyList(){
        assertNotNull(list);
//        assertNull(list);
    }

    @Test
    public void testCheckOpenLoans() {
//        assertEquals("open",list.get(1).getLoanStatus());
        assertEquals("open",list.get(0).getLoanStatus());
    }

    @Test(timeout = 2000)
    public void testTimeout() throws InterruptedException {
        Thread.sleep(90);
        assertTrue(list.size()>0);
    }
    @Test
    public void testClosedLoans(){
        assertEquals("closed",list.get(1).getLoanStatus());
//        assertNotEquals("closed", list.get(0).getLoanStatus());

    }



}
