package org.example;

import static org.example.MyBank.loans;
import static org.example.Loan.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    MyBankMethods app = new  MyBankMethods();
    @BeforeClass
    public static void initialize() {
        loans.add(new Loan(1234, 30000.0, new Date("3/2/2021"), "open", "Anu", 7435511150L));
        loans.add(new Loan(2345, 3400.0, new Date("3/4/2022"), "closed", "Ankitha", 9963772901L));
    }
    @Test
    public void testData(){
     assertEquals("Loan{loanNumber=1234, loanAmount=30000.0, loanDate='Tue Mar 02 00:00:00 IST 2021', loanStatus='open', borrowerName='Anu', phoneNumber=7435511150}", loans.get(0));
    }

    @Test
    public void testCheckOpenLoans() {
        assertEquals("loanNumber=1234, loanAmount=30000.0, loanDate=new Date(\"3/2/2021\"), loanStatus=open, borrowerName=Anu, phoneNumber=7435511150",app.checkAvailableLoans());
    }

    @Test
    public void testClosedLoans(){
        assertEquals("Loan{loanNumber=1234, loanAmount=30000.0, loanDate=new Date(\"3/2/2021\"), loanStatus='open', borrowerName='Anu', phoneNumber=7435511150}",app.checkClosedLoans());

    }



}
